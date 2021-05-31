package utilList;

/**
 * An Entry holds an object T <code>o</code> and a reference <code>next</code> to
 * the next Entry such that a linked List of Entry elements is generated.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class GenericEntry<T> {
    T o;
    GenericEntry<T> next;

    GenericEntry(){this(null, null);}

    GenericEntry(T o){
        this(o, null);
    }

    GenericEntry(T o, GenericEntry<T> e){
        this.o = o;
        this.next = e;
    }
}
