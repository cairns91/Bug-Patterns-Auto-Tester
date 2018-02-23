package tests;

import evaluator.Evaluator;

public interface Test {

    /**
     * Used to execute the given test
     */
    public void execute();
    
    /**
     * Get the evaluation report from running this test
     * @return
     */
    public Evaluator<?> getEvaluation();
}
