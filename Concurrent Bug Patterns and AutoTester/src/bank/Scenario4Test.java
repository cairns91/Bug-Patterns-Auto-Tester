package bank;

public class Scenario4Test {

	public static void main(String[] args) {
		CurrentAccount a = new CurrentAccount("Account A (Current)", 0);
		CurrentAccount b = new CurrentAccount("Account B (Current)", 0);

		DepositRunnable dr = new DepositRunnable(a, 1000);
		WithdrawRunnable wr = new WithdrawRunnable(a, 800);
		TransferRunnable tr = new TransferRunnable(a, b, 100);
		
		(new Thread(dr)).start();
		(new Thread(wr)).start();
		(new Thread(tr)).start();
	}

}
