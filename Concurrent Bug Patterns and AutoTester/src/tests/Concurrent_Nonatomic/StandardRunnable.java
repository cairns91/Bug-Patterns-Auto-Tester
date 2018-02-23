package tests.Concurrent_Nonatomic;

public class StandardRunnable implements Runnable {

	private Iterator i;
	private int DELAY = 1;

	public StandardRunnable(Iterator i) {
		this.i = i;
	}

	@Override
	public void run() {
		try {
			synchronized (i) {
				Thread.sleep(DELAY);
				i.iterateStandard();
			}
		} catch (InterruptedException e) {
		}
		;
	}

	
}