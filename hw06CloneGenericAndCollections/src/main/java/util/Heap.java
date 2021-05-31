package util;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Heap<T> {

    /**
     * saves all entries of the heap.
     */
    private T[] tree;
    /**
     * when specified, used to compare the heap entries.
     */
    private Comparator<T> comparator;


    /**
     * Constructor of Heap with Comparator
     *
     * @param tree array of T2 to instantiate the tree with
     * @param comparator comparator instance
     */
    public Heap(T[] tree, Comparator<T> comparator){
        this.tree = (T[])new Object[0];
        for(T leaf:tree){
            insert(leaf);
        }
        this.comparator = comparator;
    }

    /**
     * Constructor of Heap
     *
     * @param tree array of T to instantiate the tree with
     */
    public Heap(T[] tree){
        this(tree,null);
    }

    /**
     * Constructor of Heap with Comparator
     *
     * @param comparator comparator instance
     */
    public Heap(Comparator<T> comparator){
        this((T[])new Object[0],comparator);

    }

    /**
     * Constructor of Heap
     */
    public Heap(){
        this((T[])new Object[0],null);
    }


    /**
     * Swaps to elements in the tree by their position
     * @param i index of first(parent) node
     * @param j index of second(child) node
     */
    private void swap(int i, int j){
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
    private boolean heapConditionComparable(T parent, T child){
        // suppress unchecked cast compile-time warning which is thrown when casting a raw type to a
        // parameterized type without type checking
        try{
            @SuppressWarnings("unchecked")
            Comparable<? super T> parentComparable = (Comparable<? super T>) parent;
            return parentComparable.compareTo(child) <= 0;
        }catch (ClassCastException e){
            throw new ClassCastException("No comparator was given and the elements of the Heap do not implement the Comparable interface.");
        }
    }

    /**
     * defines the current heapCondition for the T typing
     * default is set so the Heap is a minHeap (parent is smaller than their children)
     * @param parent parent node
     * @param child child node
     */
    private boolean heapCondition(T parent, T child){
        if(comparator == null){ //when no comparator was given assume all the elements in the array to be comparable
            return heapConditionComparable(parent,child);
        }
        return comparator.compare(parent,child) <= 0;
    }

    /**
     * Inserts another node with the given element by copying the current tree. Sorts after to keep Heap conditions.
     * @param element element for the node to be added
     */
    public void insert(T element){
        tree = Arrays.copyOf(tree, tree.length+1); // make a new array by copying the array with an additional field

        int child = tree.length-1; //get the last so newest child
        int parent = tree.length % 2 == 0 ? (child-1)/2:(child-2)/2;
        if(tree.length == 1){ child = parent = 0; }
        tree[child] = element; // make the last element (so the empty one) the given element


        while (!heapCondition(tree[parent],tree[child])){
            swap(parent, child); // swap the parent and child to establish the heap condition
            if((child-2)/2 > 0 ){
                child = parent;
                parent = child % 2 == 0 ? (child-2)/2:(child-1)/2;
            }
        }
    }


    /**
     * @param pos
     * @return true if the passed node is a leaf node
     */
    private boolean isLeaf(int pos)
    {
        if (pos >= (tree.length / 2) && pos <= tree.length) {
            return true;
        }
        return false;
    }

    /**
     * Makes minimal changes such that the heap is valid.
     * @param parent
     */
    private void minHeapify(int parent)
    {
        // If the node is a non-leaf node and greater
        // than any of its child
        if (!isLeaf(parent)) {
            int leftChild = 2 *parent;
            int rightChild = 2 *parent+1;
            if (!heapCondition(tree[parent],tree[leftChild])
                    ||!heapCondition(tree[parent],tree[rightChild])) {

                // Swap with the smallest child and heapify
                // the left child
                if (heapCondition(tree[leftChild],tree[rightChild])) {
                    swap(parent, leftChild);
                    minHeapify(leftChild);
                } else { //right child
                    swap(parent, rightChild);
                    minHeapify(rightChild);
                }
            }
        }
    }




    /**
     * @return and remove the minimum element from the heap.
     */
    public T deleteFirst() {
        T root = tree[0];
        tree[0] = tree[tree.length-1];
        tree = Arrays.copyOf(tree,tree.length-1);
        minHeapify(0);
        return root;
    }


    /**
     * @return String representation of the current Heap
     */
    @Override
    public String toString() {
        return "Heap{" +
                "tree=" + Arrays.toString(tree) +
                '}';
    }

    /**
     * @return String representation of the current Heap
     */
    public boolean empty() {
        return tree.length == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Heap<?> heap = (Heap<?>) o;
        return Arrays.equals(tree, heap.tree) && Objects.equals(comparator, heap.comparator);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(comparator);
        result = 31 * result + Arrays.hashCode(tree);
        return result;
    }
}
