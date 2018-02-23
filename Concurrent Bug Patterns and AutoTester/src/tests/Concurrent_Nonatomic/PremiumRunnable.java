package tests.Concurrent_Nonatomic;

public class PremiumRunnable implements Runnable {

	private Iterator i;
	private int DELAY = 1;

	public PremiumRunnable(Iterator i) {
		this.i = i;
	}

	@Override
	public void run() {
		try {
			synchronized (i) {
				Thread.sleep(DELAY);
				i.iteratePremium();
			}
		} catch (InterruptedException e) {
		}
		;
	}

}