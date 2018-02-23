package tester;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tests.DeadThreadTest;
import tests.NoLockTest;
import tests.NonAtomic;
import tests.NonAtomic_2;
import tests.NotifyBugTest;
import tests.Test;
import tests.WrongLockTest;

public class TestAutomater {

    //Holds all of our generated tests
    Map<String, Collection<Test>> tests;
    public TestAutomater() {
        tests = new HashMap<String, Collection<Test>>();
    }
    
    /**
     * Used to generate the tests that will be used
     */
    public void generateTests() {
    	int number = 1;
    	System.out.println("GENERATING TESTS");
    	System.out.println("========================================");
        this.tests.put("WrongLockTests", this.generateWrongLockTest(number));
        if (! this.tests.get("WrongLockTests").isEmpty()) {
            System.out.println("Generated: Wrong Lock Resource Tests"); 
        }
        this.tests.put("NoLockTest", this.generateNoLockTest(number));
        if (! this.tests.get("NoLockTest").isEmpty()) {
            System.out.println("Generated: No Lock Resource Tests"); 
        }
        this.tests.put("NonAtomicOneTest", this.generateNonAtomicOneTests(number));
        if (! this.tests.get("NonAtomicOneTest").isEmpty()) {
        	System.out.println("Generated: Non Atomic Operation Tests - 1");
        }
        this.tests.put("NonAtomicTwoTest", this.generateNonAtomicTwoTests(number));
        if (! this.tests.get("NonAtomicTwoTest").isEmpty()) {
        	System.out.println("Generated: Non Atomic Operation Tests - 2");
        }
        this.tests.put("NotifyBugTest", this.generateNotifyBugTest(number));
        if (! this.tests.get("NotifyBugTest").isEmpty()) {
        	System.out.println("Generated: Notify Pattern Tests");
        }
        this.tests.put("DeadThreadTest", this.generateDeadThreadTest(number));
        if (! this.tests.get("DeadThreadTest").isEmpty()) {
    		System.out.println("Generated: Sleep Pattern Tests");
        }
    		System.out.println("========================================");
    }
   
    private Collection<Test> generateNonAtomicOneTests(int numberToGenerate) {
        Collection<Test> nonAtomicOneTests = new ArrayList<>();
        
        for (int i=0; i<numberToGenerate; i++) {
            nonAtomicOneTests.add(new NonAtomic());
        }
        
        return nonAtomicOneTests;
    }
    
    private Collection<Test> generateNonAtomicTwoTests(int numberToGenerate) {
        Collection<Test> nonAtomicTwoTests = new ArrayList<>();
        
        for (int i=0; i<numberToGenerate; i++) {
            int balance1 = RandomValueGenerator.generateInt(NonAtomic_2.minBalanceOneValue, NonAtomic_2.maxBalanceOneValue);
            int balance2 = RandomValueGenerator.generateInt(NonAtomic_2.minBalanceTwoValue, NonAtomic_2.maxBalanceTwoValue);
            int incrementAmount = RandomValueGenerator.generateInt(NonAtomic_2.minIncrementValue, NonAtomic_2.maxIncrementValue);
            nonAtomicTwoTests.add(new NonAtomic_2(balance1, balance2, incrementAmount));
        }
        
        return nonAtomicTwoTests;
    }

    private Collection<Test>  generateNoLockTest(int numberToGenerate) {
        Collection<Test> noLockTests = new ArrayList<>();
        
        for (int i=0; i<numberToGenerate; i++) {
            double startingBalance = RandomValueGenerator.generateDouble(NoLockTest.minStartingBalanceValue, NoLockTest.maxStartingBalanceValue);
            noLockTests.add(new NoLockTest(startingBalance));
        }
        
        return noLockTests;
    }
    
    private Collection<Test>  generateWrongLockTest(int numberToGenerate) {
        Collection<Test> wrongLockTests = new ArrayList<Test>();
        
        for (int i=0; i<numberToGenerate; i++) {
            String name = RandomValueGenerator.generateLowercaseString(WrongLockTest.maxNameLength);
            double inAmount = RandomValueGenerator.generateDouble(WrongLockTest.minMoneyInValue, WrongLockTest.maxMoneyInValue);
            double outAmount = RandomValueGenerator.generateDouble(WrongLockTest.minMoneyOutValue, WrongLockTest.maxMoneyOutValue);
            wrongLockTests.add(new WrongLockTest(name, inAmount, outAmount));
        }
        
        return wrongLockTests;
    }
    
    private Collection<Test>  generateNotifyBugTest(int numberToGenerate) {
        Collection<Test> NotifyBugTests = new ArrayList<Test>();
        
        for (int i=0; i<numberToGenerate; i++) {
            String string1 = RandomValueGenerator.generateLowercaseString(NotifyBugTest.maxStringLength);
            String string2 = RandomValueGenerator.generateLowercaseString(NotifyBugTest.maxStringLength);
            
            NotifyBugTests.add(new NotifyBugTest(string1, string2));
        }
        
        return NotifyBugTests;
    }
    
    private Collection<Test>  generateDeadThreadTest(int numberToGenerate) {
        Collection<Test> DeadThreadTests = new ArrayList<Test>();
        
        for (int i=0; i<numberToGenerate; i++) {
            String name = RandomValueGenerator.generateLowercaseString(DeadThreadTest.maxNameLength);
            int initialAge = RandomValueGenerator.generateInt(DeadThreadTest.minInitialAge, DeadThreadTest.maxInitialAge);
            int updatedAge = RandomValueGenerator.generateInt(DeadThreadTest.minUpdatedAge, DeadThreadTest.maxUpdatedAge);
            DeadThreadTests.add(new DeadThreadTest(name, initialAge, updatedAge));
        }
        
        return DeadThreadTests;
    }
    
    /**
     * Used to execute all of the tests this currently holds
     */
    public void executeAll() {
    	System.out.println(">> EXECUTING TESTS ");
        for (String currTestName: this.tests.keySet()) {
        	if (this.tests.get(currTestName).isEmpty()) {
                continue;
            }
            for (Test currTest: this.tests.get(currTestName)) {
                currTest.execute();
            }
            
            System.out.println(">> Executing: "+currTestName);
        }
        System.out.println(">> Execution Completed");
        System.out.println("========================================");
    }
    
    public void evaluateTests() {
        System.out.println(">> Evaluating Results");
        for (String currTestName: this.tests.keySet()) {
            if (this.tests.get(currTestName).isEmpty()) {
                continue;
            }
            int numberOfTestsPassed = 0;
            for (Test currTest: this.tests.get(currTestName)) {
                if (currTest.getEvaluation().assertIntendedResult()) {
                    numberOfTestsPassed++;
                }
            }
            
            System.out.println("============================");
            System.out.println(currTestName + " tests");
            System.out.println("Number of tests passed: " + numberOfTestsPassed);
            System.out.println("Total number of tests: " + this.tests.get(currTestName).size());
            double percentagePassed = (double)numberOfTestsPassed/(double)this.tests.get(currTestName).size() * 100;
            System.out.println("Percentage passed: " + percentagePassed + "%");
        }        
    }
}
