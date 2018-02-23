package bank;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Account {

	protected static final int TIMEOUT = 5;
	protected double balance;
	protected int accountNumber;
	protected String name;
	protected ReentrantLock balanceLock;
	protected Condition fundsAvailableCondition;

	public Account(String name, double balance) {
		this.balance = balance;
		this.name = name;
		//System.out.println("Initial balance of " + name + " is " + balance + ".");
		balanceLock = new ReentrantLock();
		fundsAvailableCondition = balanceLock.newCondition();
	}

	public int getAccountNumber() {
		return this.accountNumber;
	}

	public double getBalance() {
		//System.out.println("Balance of " + name + " is " + balance + ".");
		return this.balance;
	}

	public void deposit(double amount) {
		//System.out.println("Depositing " + amount + " in " + name + "...");
		balanceLock.lock();
		try {
			this.balance += amount;
			getBalance();
			fundsAvailableCondition.signalAll();
		} finally {
			balanceLock.unlock();
		}
	}

	public abstract boolean withdraw(double amount);

	public void transferMoney(double amount, Account recipient) {
		//System.out.println("Transferring " + amount + " from " + name + " to " + recipient.getName() + ":");
		balanceLock.lock();
		try {
			if (withdraw(amount))
				recipient.deposit(amount);
			getBalance();
			recipient.getBalance();
		} finally {
			balanceLock.unlock();
		}
	}

	public String getName() {
		return name;
	}
}
