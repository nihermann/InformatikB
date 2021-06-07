package iterator;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;


public class TestIterator {
    private MyList<Integer> getList(){
        MyList<Integer> list = new MyList<Integer>();
        for (int i = 10; 0<i; i--){
            list.add(i);
        }
        return list;
    }

    @Test
    public void testIterating(){
        MyList<Integer> l = getList();
        Iterator<Integer> iter = l.iterator();
        int[] elems = new int[10];

        int i = 0;
        while(iter.hasNext()){
            elems[i++] = iter.next();
        }
        assertArrayEquals(new int[]{1,2,3,4,5,6,7,8,9,10}, elems);
    }

    @Test(expected = NoSuchElementException.class)
    public void testNoSuchElementException(){
        MyList<Integer> l = new MyList<>();
        l.add(2);
        Iterator<Integer> iter = l.iterator();
        iter.next();
        iter.next();

    }

    @Test(expected = ConcurrentModificationException.class)
    public void testConcurrentModificationException(){
        MyList<Integer> l = getList();

        for (Integer integer : l) {
            l.add(3);
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testTwoIteratorsConcurrentModificationException(){
        MyList<Integer> l = getList();
        Iterator<Integer> iter = l.iterator();
        Iterator<Integer> iter2 = l.iterator();
        iter.next();
        iter2.next();

        // deletion in first iter causes iter2 to fail fast.
        iter.remove();
        iter2.next();



    }

    @Test
    public void testRemove(){
        MyList<Integer> l = getList();
        Iterator<Integer> iter = l.iterator();

        int[] elems = new int[5];
        int i = 0;
        while(iter.hasNext()){
            int elem = iter.next();
            if(elem % 2 != 0){
                iter.remove();
                continue;
            }
            elems[i++] = elem;
        }
        assertArrayEquals(new int[]{2,4,6,8,10}, elems);
    }

    @Test(expected = IllegalStateException.class)
    public void testDoubleDeletion(){
        MyList<Integer> l = getList();
        Iterator<Integer> iter = l.iterator();
        iter.next();
        iter.remove();
        iter.remove();
    }
}
