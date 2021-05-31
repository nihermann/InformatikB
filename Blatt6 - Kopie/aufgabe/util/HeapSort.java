package util;

import java.util.Arrays;
import java.util.Comparator;

public class HeapSort {

   public static void main(String[] args) {
      testInsert();
      testDeleteFirst();
      testComparator();
      testGetRoot();
      heapSort( new Heap<Integer>(), new Integer[]{1,2,3,4,5,6,7,8,9});
   }

   public static void testInsert(){
      Heap<Integer> heap = new Heap<Integer>();
      heap.insert(4);
      heap.insert(3);
      heap.insert(2);
      heap.insert(6);
      heap.insert(0);

   }

   public static void testComparator(){
      Comparator stringComparator = Comparator.comparingInt(String::length);
      Heap<String> heap = new Heap<String>(stringComparator);
      heap.insert("22");
      heap.insert("333");
      heap.insert("4444");
      heap.insert("55555");
      heap.insert("666666");
      heap.insert("1");
      assert heap.getRoot() == "1": "The comparator function did not work properly.";

   }

   public static void testDeleteFirst(){
      Heap<Integer> heap = new Heap<Integer>(new Integer[]{2,3,5,4});
      Heap<Integer> heap2 = new Heap<Integer>(new Integer[]{3,4,5});
      heap.deleteFirst();
      assert heap.equals(heap2): "The Root is not properly deleted.";
   }

   public static void testGetRoot(){
      Heap<Integer> heap = new Heap<Integer>(new Integer[]{2,3,5,4});
      assert 2 == heap.getRoot(): "The root is not properly returned.";
   }


   /**
    * Copies the array <code>elements</code> and sorts it using the given heap
    * <code>h</code>. Then returns the sorted copy.
    * 
    * @param h
    *           Heap which is used to sort <code>elements</code>
    * @param elements
    *           Elements which should be sorted.
    * @return a sorted copy of <code>elements</code>
    */
   public static <E> E[] heapSort(Heap<E> h, E[] elements) {

      E[] copy = Arrays.copyOf(elements, elements.length);

      Arrays.fill(copy, null);

      for (E e : elements) {
         h.insert(e);
      }

      int i = 0;
      while (!h.empty()) {
         copy[i++] = h.deleteFirst();
      }

      return copy;
   }

}
