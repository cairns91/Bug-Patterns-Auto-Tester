package driver;

import tester.TestAutomater;

public class Driver {

	public static void main(String args[]){
		TestAutomater testAutomater = new TestAutomater();
	    testAutomater.generateTests();
	    testAutomater.executeAll();
	    testAutomater.evaluateTests();
	};
}
