package bank;
import java.util.concurrent.TimeUnit;

public class SavingsAccount extends Account {

	private double fee;

	public SavingsAccount(String name, double initialBalance) {
		super(name, initialBalance);
		this.fee = 0.5;
	}

	@Override
	public boolean withdraw(double amount) {
		boolean waiting = true;
		balanceLock.lock();
		try {
			while (balance - (amount + fee) < 0) {
				if (!waiting)
					Thread.currentThread().interrupt();
				waiting = fundsAvailableCondition.await(TIMEOUT, TimeUnit.SECONDS);
			}
			this.balance -= (amount + fee);
			getBalance();
			return true;

		} catch (InterruptedException e) {

			return false;
		} finally {
			balanceLock.unlock();
		}
	}

}
