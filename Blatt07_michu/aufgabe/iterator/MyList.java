package iterator;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple linked list. One may go through this list by {@link #advance()}
 * until the last position ({@link #endpos()}) is reached. One also may
 * {@link #delete()} and {@link #add(Object)} elements. After advancing it is
 * possible to go back to the beginning by {@link #reset()}.
 * 
 * @author Lars Huning
 * 
 */
public class MyList<E> implements Cloneable, Iterable<E> {

	/**
	 * Reference on the first Entry of this List
	 */
	private MyEntry<E> begin;
	/**
	 * References before the current Entry of this List
	 */
	private MyEntry<E> pos;

	private int modCount = 0;

	/**
	 * Create a new empty List.
	 */
	public MyList() {
		pos = begin = new MyEntry<E>();
	}

	public int getModCount() {
		return modCount;
	}

	/**
	 * Determines if this List is empty or not.
	 * 
	 * @return <code>true</code>, if there are no elements in this List
	 */
	public boolean empty() {
		return begin.next == null;
	}

	/**
	 * Determines if it is possible to {@link #advance()} in this List. Returns
	 * <code>true</code> if the last position of this List has been reached. An
	 * {@link #empty()} List will alway deliver <code>true</code>
	 * 
	 * @return <code>true</code> if the last Entry in this List already has been
	 *         reached.
	 */
	public boolean endpos() { // true, wenn am Ende
		return pos.next == null;
	}

	/**
	 * Returns to the beginning of this List.
	 */
	public void reset() {
		pos = begin;
	}

	/**
	 * Advances one step in this List.
	 *
	 * @throws NoSuchElementException
	 *            if the last Entry of this List already has been reached.
	 */
	public void advance() {
		if (endpos()) {
			throw new NoSuchElementException("Already at the end of this List");
		}
		pos = pos.next;
	}

	/**
	 * Increases the current modification count
	 */
	private void increaseModCount() {
		if (this.modCount == Integer.MAX_VALUE) {
			this.modCount = 0;
		}
		this.modCount += 1;
	}



	/**
	 * Returns the current element of this List.
	 * 
	 * @return the current element
	 * 
	 * @throws RuntimeException
	 *            if the last Entry of this List already has been reached.
	 */
	public E elem() {
		if (endpos()) {
			throw new NoSuchElementException("Already at the end of this List");
		}
		return pos.next.o;
	}

	/**
	 * Inserts <code>o</code> in this List. It will be placed before the current
	 * element. After insertion the inserted element will become the current
	 * element.
	 * 
	 * @param x
	 *           the element to be inserted
	 */
	public void add(E x) {
		increaseModCount();
		MyEntry<E> newone = new MyEntry<E>(x, pos.next);

		pos.next = newone;
	}

	/**
	 * Deletes the current element of this List. The element after the current
	 * element will become the new current element.
	 * 
	 * @throws NoSuchElementException
	 *            if the last Entry of this List already has been reached.
	 */
	public void delete() {
		increaseModCount();
		if (endpos()) {
			throw new NoSuchElementException("Already at the end of this List");
		}
		pos.next = pos.next.next;
	}

	/**
	 * Clones this MyList. Will create a new independent MyList which current
	 * position lies at the beginning of this MyList. This clone operation also
	 * fulfills the optional requirements defined by the {@link Object#clone()}
	 * operation. NOTE: Inserted elements will not be cloned, due to the fact,
	 * that the {@link Object#clone()} is <code>protected</code>.
	 * 
	 * @see Object#clone()
	 */
	public MyList<E> clone() {
		try {

			MyList<E> clone = (MyList<E>) super.clone();
			clone.begin = this.begin.clone();
			clone.pos = clone.begin;

			return clone;
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((begin == null) ? 0 : begin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyList other = (MyList) obj;
		if (!begin.equals(other.begin))
			return false;
		return true;
	}

	/**
	 *
	 * @return an iterator of the current list instance with the begin as the iteration start
	 */
	@Override
	public Iterator<E> iterator(){

		System.out.println("This is the beginning: " +this.begin.next.o);
		return new MyListIterator<E>(this,this.begin);
	}

	private class MyListIterator<E> implements Iterator<E>{

		private MyEntry<E> previous;
		private MyEntry<E> current;
		private MyEntry<E> next;


		private MyList<E> list;
		private int modCountAtCreation;


		/**
		 * Constructor for a List Iterator
		 *
		 * @param list which is supposed to be iterated over
		 * @param first element at which to begin iteratoin
		 */
		public MyListIterator(MyList<E> list, MyEntry<E> first){
			this.list = list;

			this.previous = null;
			this.current = first;
			this.next = first.next;

			this.modCountAtCreation = list.getModCount();
		}


		/**
		 *
		 * @return wheter or not the current lsit has a next attribute
		 */
		@Override
		public boolean hasNext() {
			return next != null;
		}

		/**
		 *
		 * @return the current entry after going to the next entry in the list
		 */
		@Override
		public E next() {
			if(modCountAtCreation != list.getModCount()){
				throw new ConcurrentModificationException();
			}else if(!hasNext()){
				throw new NoSuchElementException();
			}
			this.previous = current;
			this.current = next;
			this.next = next.next;

			return current.o;
		}


		@Override
		public void remove() {
			if(this.previous == null){
				throw new IllegalStateException();
			}else if(modCountAtCreation != list.getModCount()){
				throw new ConcurrentModificationException();
			}

			previous.next = next;
			current = previous;
			previous = null;
		}

	}

}
