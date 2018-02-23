package bank;

public class Scenario5Test {

	public static void main(String[] args) {
		CurrentAccount a = new CurrentAccount("Account A (Current)", 0);

		DepositRunnable dr = new DepositRunnable(a, 1000);
		WithdrawRunnable wr = new WithdrawRunnable(a, 800);

		(new Thread(wr)).start();
		try {
			// Wait 2 seconds to start the second thread
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		(new Thread(dr)).start();
		new Thread(new CheckBalanceRunnable(a)).start();
	}

}
