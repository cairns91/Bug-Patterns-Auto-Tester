package tests.Concurrent_Nonatomic;

public class Account_Two_Runnable implements Runnable {

	private balance_Add a;
	private int DELAY = 1;

	public Account_Two_Runnable(balance_Add a) {
		this.a = a;
	}

	@Override
	public void run() {
		try {
			synchronized (a) {
				Thread.sleep(DELAY);
				a.Account_Two();
			}
		} catch (InterruptedException e) {
		}
		;
	}

}