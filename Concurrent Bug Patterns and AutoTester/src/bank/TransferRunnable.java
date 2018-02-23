package bank;

public class TransferRunnable implements Runnable {

	private double transferAmount;
	private Account recipient;
	private Account payee;
	

	TransferRunnable(Account payee, Account recipient, double transferAmount)
	{
		this.payee = payee;	
		this.recipient = recipient;
		this.transferAmount = transferAmount;
				
	}
		
	@Override
	public void run() {

		try {
			
			payee.transferMoney(transferAmount, recipient);
			//sleep for 10 milliseconds to await interruptions 
			Thread.sleep(10);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
}