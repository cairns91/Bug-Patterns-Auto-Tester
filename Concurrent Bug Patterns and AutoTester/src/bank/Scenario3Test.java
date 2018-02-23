package bank;

public class Scenario3Test {

	public static void main(String[] args) {
		CurrentAccount a = new CurrentAccount("Account A (Current)", 0);

		DepositRunnable dr = new DepositRunnable(a, 1000);
		WithdrawRunnable wr = new WithdrawRunnable(a, 800);
		
		(new Thread(dr)).start();
		(new Thread(wr)).start();
	}
	
}
