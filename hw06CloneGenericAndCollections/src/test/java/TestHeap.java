import GenericHeap.Heap;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestHeap {
    @Test
    public void testEmpty(){
        Heap<Integer> h = new Heap<>(new Integer[]{});
        assertTrue(h.empty());

        h.insert(2);
        assertFalse(h.empty());
    }

    @Test
    public void testInsert(){
        Heap<Integer> h = new Heap<>(new Integer[]{4});
        assertEquals((int) h.deleteFirst(), 4);

        h.insert(4);
        h.insert(2);
        h.insert(7);
        assertEquals((int) h.deleteFirst(), 2);
    }

    @Test
    public void testDeletion(){
        Heap<Integer> h = new Heap<>(new Integer[]{4});
        assertEquals((int) h.deleteFirst(), 4);

        h.insert(4);
        h.insert(2);
        h.insert(7);
        assertEquals((int) h.deleteFirst(), 2);
    }

    @Test
    public void testPostChangeValidity(){

    }

    public static int[] test(int[] arr, int e){
        int curr = arr.length/2;

        for (int i = curr; i > 0; i /= 2) {
            int relation = e - arr[curr];
            if (relation == 0){
                break;
            }
            if (relation > 0){
                curr += i;
            } else{
                curr -= i;
                curr = Math.max(curr, 0);
            }
        }
        int[] newArr = Arrays.copyOf(arr, arr.length + 1);
        newArr[curr] = e;
        for (int i = curr; i<arr.length; i++){
            newArr[i+1] = arr[i];
        }
        return newArr;
    }

//    public static void main(String[] args){
//        int[] arr = new int[]{2,4,6,8,10};
//        int e = 2;
//
//    }
}
