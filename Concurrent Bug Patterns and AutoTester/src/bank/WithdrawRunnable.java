package bank;


public class WithdrawRunnable implements Runnable {
	
	protected Account account;
	protected double amount;
	
	public WithdrawRunnable(Account account, double amount) {
		this.account = account;
		this.amount = amount;
	}

	@Override
	public void run() {
		try {
			account.withdraw(amount);
			Thread.sleep(1);
		}
		catch (InterruptedException exception) {}
	}

}
