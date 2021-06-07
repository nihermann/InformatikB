package iterator;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TestMyListIterator {
    public static void main(String[] args) {
        testIteratorNext();
        testIteratorRemove();
        testIteratorRemoveEvery();
        testFailFast();
        testTwoIterator();
        testOverIteration();
        testRemoveWithoutNext();
    }

    public static MyList<Integer> createList(){
        MyList<Integer> myList = new MyList<Integer>();
        myList.add(5);
        myList.add(4);
        myList.add(3);
        myList.add(2);
        myList.add(1);
        return myList;
    }

    public static void testIteratorNext(){
        MyList<Integer> myList = createList();

        Integer[] comparison = new Integer[]{1,2,3,4,5};
        Iterator<Integer> iterator = myList.iterator();
        compare(iterator,comparison,"Iterating using next from the iterator does not work properly.");
    }

    public static void testIteratorRemove(){
        MyList<Integer> myList = createList();
        Iterator<Integer> iterator = myList.iterator();
        iterator.next();
        iterator.remove();
        Integer[] comparison = new Integer[]{2,3,4,5};
        compare(iterator,comparison, "Removing a single element by using the iterator does not work properly.");
    }

    public static void testIteratorRemoveEvery(){
        MyList<Integer> myList = createList();
        Iterator<Integer> iterator = myList.iterator();
        while (iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
        assert myList.empty(): "Removing every element from the list using the iterator did not work properly.";
    }

    public static void compare(Iterator<Integer> iterator, Integer[] comparison, String msg){
        for(Integer comparing: comparison){
            assert comparing == iterator.next(): msg;
        }
    }

    public static void testFailFast(){
        MyList<Integer> myList = createList();
        Iterator<Integer> iterator = myList.iterator();
        try {
            iterator.next();
            myList.delete();
            iterator.next();
            assert false: "The fail fast exception was not thrown after the iterable was changed.";
        }catch (ConcurrentModificationException e){}
    }

    public static void testTwoIterator(){
        MyList<Integer> myList = createList();
        Iterator<Integer> iterator = myList.iterator();
        Iterator<Integer> iterator2 = myList.iterator();
        try {
            iterator.next();
            iterator2.next();
            iterator.next();
            iterator.remove();
            iterator2.next();
            assert false: "The fail fast exception was not thrown after the iterable was changed.";
        }catch (ConcurrentModificationException e){}
    }

    public static void testOverIteration(){
        MyList<Integer> myList = createList();
        Iterator<Integer> iterator = myList.iterator();
        try {
            while (iterator.hasNext()){
                iterator.next();
            }
            iterator.next();
            assert false: "No exception was thrown after iterating beyond the List.";
        }catch (NoSuchElementException e){}
    }

    public static void testRemoveWithoutNext(){
        MyList<Integer> myList = createList();
        Iterator<Integer> iterator = myList.iterator();
        try {
            iterator.remove();
            assert false: "No exception was thrown after removing from the List before calling next().";
        }catch (IllegalStateException e){}
    }
}
