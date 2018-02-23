package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import evaluator.Evaluator;
import evaluator.EvaluatorType;

public class NotifyBugTest implements Test {

	public static final int maxStringLength = 10;
	private Evaluator<List<String>> eval;
	public ArrayList<String> compStrings = new ArrayList<>();
	public ArrayList<String> strings = new ArrayList<>();
	public String String1;
	public String String2;
	private Lock lock = new ReentrantLock();
	private Condition notifySentCondition = lock.newCondition();

	public NotifyBugTest(String s1, String s2) {
		String1 = s1;
		String2 = s2;

		compStrings.add(String1);
		compStrings.add(String2);
		this.eval = new Evaluator<List<String>>(EvaluatorType.ASSERT_NOT_EQUALS);
	}

	private Runnable notifyRunnable = new Runnable() {
		public void run() {
			lock.lock();
			strings.add(String1);
			try {
				//notifySentCondition.signal();
			} catch (Exception e) {
			} finally {
				lock.unlock();
			}
		}
	};

	private Runnable notifiedRunnable = new Runnable() {

		public void run() {
			
			lock.lock();
			final long TIMEOUT = 1;
			try {
				boolean notifySent = notifySentCondition.await(TIMEOUT, TimeUnit.SECONDS);
				if (notifySent) {
					strings.add(String2);
					System.out.println("Notify received");
				}
			} catch (InterruptedException e) {
			} finally {
				lock.unlock();
			}
		}
	};

	@Override
	public void execute() {

		Thread notifyThread = new Thread(notifyRunnable);
		Thread notifiedThread = new Thread(notifiedRunnable);
		notifyThread.start();
		notifiedThread.start();
		
		try {
			while (notifyThread.isAlive() || notifiedThread.isAlive()) {
				//Just so that we can stay in execute until both threads have stopped
			}
			throw new InterruptedException();
		} catch (InterruptedException e) {
		} finally {
			eval.setExpectedValue(compStrings);
			eval.setActualValue(strings);
		}
	
	}

	@Override
	public Evaluator<List<String>> getEvaluation() {
		return this.eval;
	}

}
