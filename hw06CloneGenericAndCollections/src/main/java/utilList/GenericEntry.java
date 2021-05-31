package utilList;

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
