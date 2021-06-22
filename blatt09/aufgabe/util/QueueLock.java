package util;

import java.util.NoSuchElementException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * An implementation of a Queue with a limited capacity.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 * @param <E>
 */
public class QueueLock<E> {

	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final Lock rLock = readWriteLock.readLock();
	private final Lock wLock = readWriteLock.writeLock();
	private final Condition condition = wLock.newCondition();
	/**
	 * Holds the objects stored by this {@code Queue}.
	 */
	private Object[] objects;
	/**
	 * index of the first instance stored by this {@code Queue}.
	 */
	private int first;
	/**
	 * number of elements contained in this {@code Queue}
	 */
	private int size;

	/**
	 * @param capacity
	 *            number of objects which may be hold in this {@code Queue}.
	 */
	public QueueLock(int capacity) {
		this.objects = new Object[capacity];
		this.first = 0;
		this.size = 0;
	}

	/**
	 * Inserts {@code o} at the first free position of this {@code Queue}
	 * 
	 * @param o
	 *            object to be inserted
	 * 
	 * @throws RuntimeException
	 *             if this {@code Queue} is already full
	 */
	public void enq(E o) {
		wLock.lock();
		try {
			while (this.full()) {
				condition.await();
			}
			objects[(first + size) % objects.length] = o;
			size++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			condition.signalAll();
			wLock.unlock();
		}

	}

	/**
	 * Removes the object at the first position of this {@code Queue}.
	 * 
	 * @return the removed object
	 * @throws NoSuchElementException
	 *             if this {@code Queue} is already empty
	 */
	public E deq() {

		wLock.lock();
		try {
			while (this.empty()) {
				try{
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			E o = (E) objects[first];
			first = (first + 1) % objects.length;
			size--;
			return o;
		} finally {
			condition.signalAll();
			wLock.unlock();
		}
	}

	/**
	 * Returns the object at the first position of this {@code Queue}
	 * 
	 * @return the first element of this {@code Queue}
	 * @throws NoSuchElementException
	 *             if this {@code Queue} is already empty
	 */
	public E front() {
		rLock.lock();
		try {
			if (this.empty()) {
				throw new NoSuchElementException();
			}
			return (E) objects[first];
		}finally {
			rLock.unlock();
		}
	}

	/**
	 * 
	 * @return {@code true} if this {@code Queue} is empty
	 */
	public boolean empty() {
		rLock.lock();
		try {
			return this.size == 0;

		}finally {
			rLock.unlock();
		}
	}

	/**
	 * 
	 * @return {@code true} if this {@code Queue} is full
	 */
	public boolean full() {
		rLock.lock();
		try {
			return this.size == objects.length;
		}finally {
			rLock.unlock();
		}
	}

}
