package bank;

public class DepositRunnable implements Runnable {

	private static final int DELAY = 1;
	private Account account;
	private double amount;
	
	public DepositRunnable(Account account, double amount) {
		this.account = account;
		this.amount = amount;
	}

	@Override
	public void run() {
		try {
			account.deposit(amount);
			Thread.sleep(DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}