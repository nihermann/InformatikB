package utilList;
/**
 * A simple linked list. One may go through this list by {@link #advance()} until
 * the last position ({@link #endpos()}) is reached. One also may
 * {@link #delete()} and {@link #add(Object)} elements. After advancing it is
 * possible to go back to the beginning by {@link #reset()}.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 *
 */
public class MyList<T> implements Cloneable{

    /**
     * Reference on the first Entry of this List
     */
    private Entry<T> begin;
    /**
     * References before the actual Entry of this List
     */
    private Entry<T> pos;

    /**
     * Create a new empty List.
     */
    public MyList() {
        pos = begin = new Entry<T>();
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
     * @throws RuntimeExcpetion
     *            if the last Entry of this List already has been reached.
     */
    public void advance() {
        if (endpos()) {
            throw new RuntimeException("Already at the end of this List");
        }
        pos = pos.next;
    }

    /**
     * Returns the actual element of this List.
     *
     * @return the actual element
     *
     * @throws RuntimeException
     *            if the last Entry of this List already has been reached.
     */
    public T elem() {
        if (endpos()) {
            throw new RuntimeException("Already at the end of this List");
        }
        return (T) pos.next.o;
    }

    /**
     * Inserts <code>o</code> in this List. It will be placed before the actual
     * element. After insertion the inserted element will become the actual
     * element.
     *
     * @param x
     *           the element to be inserted
     *
     * This List should not extend List so indirect overshadowing of methods is applicable (specifying the Type in the
     * signature of a method does not result in overriding thus the compiler does not know which method to choose from)
     */
    public void add(T x) {
        Entry newone = new Entry(x, pos.next);

        pos.next = newone;
    }

    /**
     * Deletes the current element of this List. The element after the current
     * element will become the new actual element.
     *
     * @throws RuntimeExcpetion
     *            if the last Entry of this List already has been reached.
     */
    public void delete() {
        if (endpos()) {
            throw new RuntimeException("Already at the end of this List");
        }
        pos.next = pos.next.next;
    }

    /**
     * Returns a copy of this current class instance by instantiating a new MyList instance and adding Element
     * from the called Object
     *
     * @throws RuntimeExcpetion
     *            if the last Entry of this List already has been reached.
     */
    @Override
    public MyList<T> clone(){
        try {
            MyList<T> copy = (MyList<T>) super.clone();
            copy.begin = this.begin;
            return copy;
        }catch (CloneNotSupportedException e){
            System.out.println("The class does not support cloning.");
            return null;
        }
    }

    @Override
    public String toString() {
        String repr = "List : [ ";

        while(!this.endpos()){
            repr += this.elem().toString();
            repr = repr+" ,";
            this.advance();
        }
        this.reset();
        return  repr.substring(0, repr.length() - 1)+" ]";
    }

    public boolean equals(Object o){
        if(o.getClass() == this.getClass()){
            while(!this.endpos()){
                if (!this.elem().equals(((MyList<?>) o).elem())){
                    return false;
                }
                this.advance();
                ((MyList<?>) o).advance();
            }
            this.reset();
            ((MyList<?>) o).reset();
            return true;
        }else {
            return false;
        }

    }
}
