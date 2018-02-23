package bank;

public class CheckBalanceRunnable implements Runnable {

	private static final int DELAY = 1;
	private Account account;
	
	public CheckBalanceRunnable(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		try {
			account.getBalance();
			Thread.sleep(DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

