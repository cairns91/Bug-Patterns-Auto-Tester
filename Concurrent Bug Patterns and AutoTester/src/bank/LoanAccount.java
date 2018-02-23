package bank;

public class LoanAccount extends Account {

	private CurrentAccount parentAccount;

	public LoanAccount(String name, double loanAmount, CurrentAccount parent) {
		super(name, -loanAmount);
		this.parentAccount = parent;
		parentAccount.deposit(loanAmount);
	}

	@Override
	public boolean withdraw(double amount) {
		System.out.println("Can't withdraw " + amount + ". Withdrawals not available for loan account.");
		return false;
	}

	public void deposit(double amount) {
		balanceLock.lock();
		try {
			this.balance += amount;
			if (balance >= 0) {
				System.out.println("Loan paid off. You have " + balance + " credit.");
			}
			if (balance > 0) {
				parentAccount.deposit(balance);
				balance = 0;
				getBalance();
			}
		} finally {
			balanceLock.unlock();
		}
	}

}
