package GenericHeap;

import java.util.Arrays;
import java.util.Comparator;
import static util.HeapSort.heapSort;

public class Heap<T> {

    private T[] arr;
    private Comparator<T> comp;
    private int size = 0;
    
    public Heap(T[] array, Comparator<T> comp){
        this.arr = array;
        this.comp = comp;
    }
    public Heap(T[] array){ this(array, null); }

    private void rebuild(){
        this.arr = heapSort(this, this.arr);

    }

    public void insert(T e) {

    }

    public boolean empty() {
        return this.size != 0;
    }

    public T deleteFirst() {
        T first = this.arr[0];
        this.arr = Arrays.copyOfRange(this.arr, 1, arr.length);
        heapSort(this, this.arr);
        return first;
    }
}
