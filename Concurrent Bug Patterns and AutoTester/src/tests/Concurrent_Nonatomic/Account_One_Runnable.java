package tests.Concurrent_Nonatomic;

public class Account_One_Runnable implements Runnable {

	private balance_Add a;
	private int DELAY = 1;

	public Account_One_Runnable(balance_Add a) {
		this.a = a;
	}

	@Override
	public void run() {
		try {
			synchronized (a) {
				Thread.sleep(DELAY);
				a.Account_One();
			}
		} catch (InterruptedException e) {
		}
		;
	}

}