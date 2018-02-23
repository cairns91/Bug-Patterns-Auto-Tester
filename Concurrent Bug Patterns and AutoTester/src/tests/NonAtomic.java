package tests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import evaluator.Evaluator;
import evaluator.EvaluatorType;

public class NonAtomic implements Test {
	private int openedTotal = 0;
	private int standardAcc = 0;
	private int premiumAcc = 0;
	private boolean run = true;

	private Evaluator<Integer> eval;
	public NonAtomic() {
		this.eval = new Evaluator<Integer>(EvaluatorType.ASSERT_NOT_EQUALS);
	}
	
	private Runnable standardAccount = new Runnable() {
		@Override
		public void run() {
			while (run) {
				openedTotal++;
				standardAcc++;
				//System.out.println("Account #" + standardAcc + " Standard account Total: " + standardAcc);
			}
		}
	};
	private Runnable premiumAccount = new Runnable() {
		@Override
		public void run() {
			while (run) {
				openedTotal++;
				premiumAcc++;
				//System.out.println("Account #" + premiumAcc + " Premium Account Total: " + premiumAcc);
			}
		}
	};
	public static void main(String[] args) {
		NonAtomic a = new NonAtomic();
		a.execute();
		System.out.println();
		System.out.println("Standard Account Total = " + a.standardAcc);
		System.out.println("Premium Account Total =  " + a.premiumAcc);
		System.out.println();
		System.out.println("Total Accounts Opened = " + (a.standardAcc + a.premiumAcc));
		System.out.println();
		System.out.println("The total amount of registered openings " + a.openedTotal);
		System.out.println();
		System.out.println("Expected output: Total Accounts opened == Total amount of registered openings,"+ "\n"+
		"if the registered openings is < than the total opened then the nonatomic bug is found ");
	}
    @Override
    public void execute() {
        ExecutorService ThreadGroups = Executors.newFixedThreadPool(1000);
        ThreadGroups.execute(standardAccount);
        ThreadGroups.execute(premiumAccount);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Error");
        }
        run = false;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Error");
        }
        
        eval.setExpectedValue(this.openedTotal);
        eval.setActualValue(this.premiumAcc + this.standardAcc);
    }
    @Override
    public Evaluator<Integer> getEvaluation() {
        return this.eval;
    }
}