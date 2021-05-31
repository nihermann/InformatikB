package util;

import java.util.Arrays;
import java.util.Comparator;

public class HeapSort {


   public static void main(String[] args) {
//      testInsert();
//      testDeleteRoot();
//      testDeleteFirst();
//      testGetRoot();
      testComparator();
//      Integer[] result = heapSort(new Heap<Integer>(),new Integer[]{2,3,5,4});
   }

   public static void testInsert(){
      Heap<Integer> heap = new Heap<Integer>(new Integer[]{2,3,5});
      heap.insert(4);
      Heap<Integer> heap2 = new Heap<Integer>(new Integer[]{2,3,5,4});
      assert heap.equals(heap2): "The two heaps do not match each other.";
   }

   public static void testComparator(){
      Comparator stringComparator = Comparator.comparingInt(String::length);
      Heap<String> heap = new Heap<String>(stringComparator);
      heap.insert("Bastian");
      heap.insert("Christ");
      heap.insert("em");
      heap.insert("Beatrix");
      heap.insert("Lol");
      heap.insert("Christian anderson von schmitz");
      heap.insert("viki");
      heap.insert("susi");



      System.out.println("heap :"+ heap);

   }

   public static void testDeleteRoot(){
      Heap<Integer> heap = new Heap<Integer>(new Integer[]{2,3,5,4});
      Heap<Integer> heap2 = new Heap<Integer>(new Integer[]{3,5,4});
//      heap.deleteRoot();
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
