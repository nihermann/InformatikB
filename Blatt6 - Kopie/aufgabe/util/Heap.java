package util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Heap<T extends Comparable> {

    T[] tree;
    Comparator comparator;

    /**
     * Constructor of Heap
     *
     * @param tree array of T to instantiate the tree with
     */
    public Heap(T[] tree){
        this.tree = tree;
    }

    /**
     * Constructor of Heap
     *
     * @param tree array of T to instantiate the tree with
     */
    public Heap(Object[] tree, Comparator comparator){
        this.tree = (T[]) tree;
        this.comparator = comparator;
    }



    /**
     * Swaps to elements in the tree by their position
     * @param i index of first(parent) node
     * @param j index of second(child) node
     */
    private final void swap(int i, int j){
        T copy = tree[i];
        tree[i] = tree[j];
        tree[j] = copy;
    }

    /**
     * @return minimum element so the current root
     */
    public T getRoot(){
        return tree[0];
    }

    /**
     * defines the current heapCondition for the T typing
     * default is set so the Heap is a minHeap (parent is smaller than their children)
     * @param parent parent node
     * @param child child node
     */
    private boolean heapCondition(T parent, T child){
        // does not really work because parent and child are already T so they extend comparable
        if (comparator != null){return comparator.compare(parent,child) > 0;}
        return parent.compareTo(child) > 0;
    }


    /**
     * @return boolean whether or not the current tree fulfills the Heap condition
     *
     */
    private boolean valid(){
        for(int i = 0; 2*i+2 < tree.length; i++){
            int parent = i;
            if (heapCondition(tree[parent], tree[2*i+1]) || heapCondition(tree[parent], tree[2*i+2]) ){
                return false;
            }
        }
        return true;
    }

    /**
     * Sorts the current tree by swapping each inconsistent parent/child combination where the Heap condition does not hold
     * until the complete tree is valid
     */
    private void sort(){
        while (!valid()){
            for(int i = 0; 2*i+2 < tree.length; i++){
                int parent = i;
                int[] children = new int[]{2*i+1,2*i+2};
                for(int child : children){
                    if (heapCondition(tree[parent], tree[child])) {
                        swap(parent,child);
                    }
                }
            }
        }
    }



    /**
     * deletes the smalles current element (so the Root of the tree) and sorts after
     */
    public void deleteRoot(){
        tree = Arrays.copyOfRange(tree,1,tree.length);
        sort();
    }

    /**
     * Inserts another node with the given element by copying the current tree. Sorts after to keep Heap conditions.
     * @param element element for the node to be added
     */
    public void insert(T element){
        tree = Arrays.copyOf(tree, tree.length+1); // make a new array by copying the array with an additional field
        tree[tree.length-1] = element; // make the last element (so the empty one) the given element
        sort(); // sort the tree
    }

    /**
     *
     * @return String representation of the current Heap
     */
    @Override
    public String toString() {
        return "Heap{" +
                "tree=" + Arrays.toString(tree) +
                '}';
    }

    public static void main(String[] args) {
    }
}
