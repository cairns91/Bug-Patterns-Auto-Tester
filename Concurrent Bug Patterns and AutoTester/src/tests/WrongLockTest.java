package tests;

import bank.CurrentAccount;
import bank.DepositRunnable;
import bank.WithdrawRunnable;
import evaluator.Evaluator;
import evaluator.EvaluatorType;

public class WrongLockTest implements Test  {

    public static final int maxMoneyInValue = 12;
    public static final int minMoneyInValue = 10;
    public static final int maxMoneyOutValue = 4;
    public static final int minMoneyOutValue = 0;
    public static final int maxNameLength = 20;
    
    private String name;
    private double moneyInValue;
    private double moneyOutValue;
    
    private Evaluator<Double> eval;
    
    public WrongLockTest(String name, double moneyInValue, double moneyOutValue) {
    	
        this.name = name;
        this.moneyInValue = moneyInValue;
        this.moneyOutValue = moneyOutValue;
        this.eval = new Evaluator<Double>(EvaluatorType.ASSERT_NOT_EQUALS);
    }
    
    @Override
    public void execute() {
    	CurrentAccount ts1 = new CurrentAccount(this.name, 0.0);
        
        double expectedBalance = 0;
        
            expectedBalance = ts1.getBalance() + moneyInValue - moneyOutValue;

            DepositRunnable dr = new DepositRunnable(ts1, moneyInValue);
            WithdrawRunnable wr = new WithdrawRunnable(ts1, moneyOutValue);
            
            Thread depositThread = new Thread(dr);
            Thread withdrawThread = new Thread(wr);
        
        while (depositThread.isAlive() || withdrawThread.isAlive()) {
            
        }
        
        eval.setExpectedValue(expectedBalance);
        eval.setActualValue(ts1.getBalance());
        
    }

    @Override
    public Evaluator<Double> getEvaluation() {
    	//If Actual value != Expected value then we have detected the wrong lock bug pattern
        return this.eval;
    }

}
