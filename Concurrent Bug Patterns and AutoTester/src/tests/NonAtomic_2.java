package tests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import evaluator.Evaluator;
import evaluator.EvaluatorType;

public class NonAtomic_2 implements Test {

	private int balance1;
	public static final int minBalanceOneValue = 0;
	public static final int maxBalanceOneValue = 100;
	
	private int balance2;
	public static final int minBalanceTwoValue = 0;
	public static final int maxBalanceTwoValue = 100;
	
	private int totalBalance;
	private int incrementAmount;
	public static final int minIncrementValue = 1;
	public static final int maxIncrementValue = 25;
	
	private boolean run = true;
	private Evaluator<Integer> eval;

	public NonAtomic_2(int balance1, int balance2, int incrementAmount) {
	    eval = new Evaluator<>(EvaluatorType.ASSERT_NOT_EQUALS);
	    this.balance1 = balance1;
	    this.balance2 = balance2;
	    this.totalBalance = balance1 + balance2;
	    this.incrementAmount = incrementAmount;
	}
	
	private Runnable balanceAcc1 = new Runnable() {
		@Override
		public void run() {
			while (run) {
				balance1 += incrementAmount;
				totalBalance += incrementAmount;
				//System.out.println("Account 1 Total: " + balance1);
			}
		}
	};
	
	private Runnable balanceAcc2 = new Runnable() {
		@Override
		public void run() {
			while (run) {
				balance2 += incrementAmount;
				totalBalance += incrementAmount;
				//System.out.println("Account 2 Total: " + balance2);
			}
		}
	};

    @Override
    public void execute() {
        ExecutorService ThreadGroups = Executors.newFixedThreadPool(1000);
        ThreadGroups.execute(balanceAcc1);
        ThreadGroups.execute(balanceAcc2);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            //System.out.println("Error");
        }
        run = false;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            //System.out.println("Error");
        }
        
        eval.setActualValue(balance1 + balance2);
        eval.setExpectedValue(totalBalance);
    }


    @Override
    public Evaluator<Integer> getEvaluation() {
        return this.eval;
    }
    
    /*non atomic 2

		System.out.println();
		System.out.println("Account 1 Balance: " + balance1);
		System.out.println("Account 2 Balance: " + balance2);
		System.out.println();
		System.out.println("Account 1 & 2 Balance: " + (balance1 + balance2));
		System.out.println();
		System.out.println("Total Balance: " + totalBalance);
		System.out.println();
		
		System.out.println("Expected output: Total Balance == Account 1 & 2 Balance," + "\n"
				+ "if the Total Balance is < than Account 1 & 2 Balance, then the nonatomic bug is found ");
     * 
     */
     
}