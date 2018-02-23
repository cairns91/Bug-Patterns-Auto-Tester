package tests.sleepbug;

public class UpdateRunnable implements Runnable {

	private String surname;
	private boolean fit;
	private int age;
	private Bank e;
	

	public UpdateRunnable(Bank e, String n, boolean t, int a) {
		this.age = a;
		this.surname = n;
		this.fit = t;
		this.e = e; 
	}
		
	@Override
	public void run() {

		try {
			e.update(this.surname, this.fit, this.age);
			
			Thread.sleep(10);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
}
