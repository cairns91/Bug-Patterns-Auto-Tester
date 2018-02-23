package tests.Concurrent_Nonatomic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class balance_Add {

	protected Lock accountLock;
	private int balance1;
	private int balance2;
	private int totalBalance;
	private int incrementAmount = 10;

	public balance_Add() {
		accountLock = new ReentrantLock();
	}

	public void Account_One() {

		accountLock.lock();
		try {
			balance1 += incrementAmount;
			totalBalance += incrementAmount;
			System.out.println("Account 1 Total: " + balance1);
		} finally {
			accountLock.unlock();
		}
	}

	public void Account_Two() {

		accountLock.lock();
		try {
			balance2 += incrementAmount;
			totalBalance += incrementAmount;
			System.out.println("Account 2 Total: " + balance2);
		} finally {
			accountLock.unlock();
		}
	}
}
