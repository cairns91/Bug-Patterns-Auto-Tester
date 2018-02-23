package bank;

public class Scenario2Test {
	
	public static void main(String[] args) {
		CurrentAccount a = new CurrentAccount("Account A (Current)", 0);

		Thread user1 = new Thread(new DepositRunnable(a, 200));
		Thread user2 = new Thread(new CheckBalanceRunnable(a));
		user1.start();
		user2.start();
	}

}
