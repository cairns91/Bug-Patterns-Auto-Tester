package tests.sleepbug;

public class MoveOnRunnable implements Runnable {
	
	private Bank e;
	
	public MoveOnRunnable(Bank e) {
		this.e = e;
	}

	@Override
	public void run() {

		try {
			Thread.sleep(10);
			e.moveOn();

			Thread.sleep(10);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
}
