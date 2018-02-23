package tests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import evaluator.Evaluator;
import evaluator.EvaluatorType;

public class NoLockTest implements Test{
	
	private boolean run = true;
	private double accountBalance;
	private int times1;
	private int times2;
	private Evaluator<Double> eval;
	private double startingBalance;
	
	public static final double minStartingBalanceValue = 0;
	public static final double maxStartingBalanceValue = 1000;
	
	public NoLockTest(double startingBalance) {
		times1 = 0;
		times2 = 0;
		accountBalance = startingBalance;
		this.startingBalance = startingBalance;
		this.eval = new Evaluator<Double>(EvaluatorType.ASSERT_NOT_EQUALS);
	}

	private Runnable freeLoader = new Runnable() {
		@Override
		public void run() {
			while (run) {
				synchronized(this){		//calculating every Run for this thread
					times1++;
				}
					accountBalance++;	//NON-Synchronized Operation
			}
		}
	};
	
	private Runnable topUpLoader = new Runnable() {
		@Override
		public void run() {
			while (run) {
				synchronized(this){		//calculating every Run for this thread
					times2++;
				}
				synchronized(this){
					accountBalance++;	//Synchronized Operation
				}
			}
		}
	};

	@Override
	public void execute() {
        ExecutorService ThreadGroups = Executors.newFixedThreadPool(10);
        ThreadGroups.execute(freeLoader);
        ThreadGroups.execute(topUpLoader);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        
        run = false;
        
        try {
            Thread.sleep(3);
        } catch (InterruptedException ex) {
        }
        
         eval.setExpectedValue((times1+times2 + this.startingBalance));
         eval.setActualValue(accountBalance);
	}

	@Override
	public Evaluator<Double> getEvaluation() {
		return this.eval;
	}
}