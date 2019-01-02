package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Account {
	private int sum;
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final Lock readLock = readWriteLock.readLock();
	private final Lock writeLock = readWriteLock.writeLock();

	public Account(int sum) {
		super();
		this.sum = sum;
	}

	public int getSum() {
		try {
			readLock.lock();
			System.out.println("returning value:" + sum);
			return sum;
		} finally {
			readLock.unlock();
		}
	}

	public void depositeSum(int sum) {
		try {
			writeLock.lock();
			System.out.println("added value:" + sum + " in " + this.sum);
			this.sum += sum;
			getSum();
		} finally {
			writeLock.unlock();
		}
	}

	public void withdrawSum(int sum) {
		try {
			writeLock.lock();
			if (this.sum - sum >= 0) {
				System.out.println("Subtracted value:" + sum + " from " + this.sum);
				this.sum -= sum;
				getSum();
			}
			else {
				System.out.println("Cannot subtract value:" + sum + " from " + this.sum);
			}
		} finally {
			writeLock.unlock();
		}
	}

}
