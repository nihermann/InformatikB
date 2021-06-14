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
public class LockedQueue<E> {

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

	private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
	private final Lock wLock = rwLock.writeLock();
	private Condition cond = wLock.newCondition();

	/**
	 * @param capacity
	 *            number of objects which may be hold in this {@code Queue}.
	 */
	public LockedQueue(int capacity) {
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
		this.wLock.lock();
		try {
			while (this.full()) {
				// as long as the queue has no capacity we want to wait until storage is released.
				try {
					this.cond.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//throw new RuntimeException("queue is full");
			}
			objects[(first + size) % objects.length] = o;
			size++;

			// awake all awaiting fellows in case they are waiting on this supply.
			this.cond.signalAll();
		}finally{
			this.wLock.unlock();
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
		this.wLock.lock();
		try {
			while (this.empty()) {
				try {
					this.cond.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//throw new NoSuchElementException();
			}

			E o = (E) objects[first];
			first = (first + 1) % objects.length;
			size--;

			this.cond.signalAll();
			return o;
		}finally{
			this.wLock.unlock();
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
		if (this.empty()) {
			throw new NoSuchElementException();
		}
		return (E) objects[first];
	}

	/**
	 * 
	 * @return {@code true} if this {@code Queue} is empty
	 */
	public boolean empty() {
		return this.size == 0;
	}

	/**
	 * 
	 * @return {@code true} if this {@code Queue} is full
	 */
	public boolean full() {
		return this.size == objects.length;
	}

}
