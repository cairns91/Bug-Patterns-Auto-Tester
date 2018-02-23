package evaluator;

/**
 * This is used to check if the expected and actual value are what was intended.
 * It has a generic type so that it can be used to compare two states and it has 
 * an EvaluatorType parameter so that it can determine whether the actual & expected value
 * should be equal or whether they should be different
 *
 * @param <T>
 */
public class Evaluator<T> {
    
    private T expected;
    private T actual;
    private final EvaluatorType evalType;
    
    public Evaluator(EvaluatorType evalType) {
        this.evalType = evalType;
    }

    public void setExpectedValue(T expected) {
        this.expected = expected;
    }
    
    public void setActualValue(T actual) {
        this.actual = actual;
    }
    
    public boolean assertIntendedResult() {
        if (this.expected.equals(actual)) {
            return (this.evalType.equals(EvaluatorType.ASSERT_EQUALS));
        }
        else {
            return (this.evalType.equals(EvaluatorType.ASSERT_NOT_EQUALS));
        }
    }
}
