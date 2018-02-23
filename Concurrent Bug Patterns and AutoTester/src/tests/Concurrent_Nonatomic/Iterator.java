package tests.Concurrent_Nonatomic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Iterator {

	protected Lock accountLock;
	private static int openedTotal = 0;
	private static int standardAcc = 0;
	private static int premiumAcc = 0;

	public Iterator() {
		accountLock = new ReentrantLock();
	}

	public void iterateStandard() {

		accountLock.lock();
		try {
			openedTotal++;
			standardAcc++;
			System.out.println("Account #" + standardAcc + " Standard account Total: " + standardAcc);
		} finally {
			accountLock.unlock();
		}
	}

	public void iteratePremium() {

		accountLock.lock();
		try {
			openedTotal++;
			premiumAcc++;
			System.out.println("Account #" + standardAcc + " Standard account Total: " + standardAcc);
		} finally {
			accountLock.unlock();
		}
	}
}
