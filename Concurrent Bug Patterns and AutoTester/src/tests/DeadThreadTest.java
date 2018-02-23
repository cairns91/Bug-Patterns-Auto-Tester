package tests;

import evaluator.Evaluator;
import evaluator.EvaluatorType;
import tests.sleepbug.Bank;

public class DeadThreadTest implements Test {
    
    private Evaluator<Boolean> eval;
    public static int minNameLength = 5;
    public static int maxNameLength = 10;
    protected String accountHolderName;
    public static int minInitialAge = 5;
    public static int maxInitialAge = 20;
    protected int initialAge;
    public static int minUpdatedAge = 21;
    public static int maxUpdatedAge = 50;
    protected int updatedAge;
    public DeadThreadTest(String name, int initialAge, int updatedAge) {
        this.eval = new Evaluator<Boolean>(EvaluatorType.ASSERT_NOT_EQUALS);
        this.accountHolderName = name;
        this.initialAge = initialAge;
        this.updatedAge = updatedAge;
    }

    @Override
    public void execute() {

        ThreadGroup g1 = new ThreadGroup (Thread.currentThread().getThreadGroup(), "Mine");
        ThreadGroup g2 = new ThreadGroup (g1, "subGroup");
        
        Bank med = new Bank(this.accountHolderName, true, this.initialAge);
        
        Thread a = new Thread(g1, "T2") {
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Something went wrong");
                }
                med.moveOn();
            }
        };
        Thread b = new Thread(g2, "T2") {
            public void run() {
                med.update(accountHolderName, true, updatedAge);
            }
        };
        
        a.start();
        b.start();
        
        eval.setExpectedValue(true);
        eval.setActualValue(med.getSavingAccount() && med.getCurrentAccount());
        
    }

    @Override
    public Evaluator<Boolean> getEvaluation() {
        return this.eval;
    }
}

