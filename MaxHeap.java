
import java.util.*;
public class MaxHeap<E extends Comparable<E>> extends ArrayList<E>   {
   public ArrayList<E> heapList;
   public int size;
   public int maxRoot;
   public E root;
    // construct an empty Heap using ArrayList
    // with root at index 0.
    // don't use binary tree for implementing the heap.
    public MaxHeap(){
        this.heapList = new ArrayList<>();
        this.size = 0;
        this.maxRoot = 0;
        this.root = null;
        //heapList.set(0, root);
    }
    // returns max value
    public E findMax() {
        if (size == 0){
            throw new NoSuchElementException("The heap is empty");
        }
        return heapList.get(0);
    }
    
    // adds a new value to the heap at the end of the Heap and 
    // adjusts values up to the root to ensure Max heap property is satisfied.
    // parent of node at i is given by the formula (i-1)/2
    // throw appropriate exception
    public void addHeap(E val) {

        // Will add element at the end of ArrayList and size will increase
        if (size == 0){
            heapList.add(val);
            size++;
        }
        else{
            size++;
            heapList.add(val);
            upwardsHeap(heapList, size - 1);

        }
    }

    public void printArray(){
        for (E val: heapList){
            System.out.println(val + " ");
        }
    }

    // Will recursively loop until the the added value is in its
    // correct position.
    public void upwardsHeap(ArrayList<E> list, int i){
        int parentVal = (i - 1) / 2;
        if(list.get(i).compareTo(list.get(parentVal)) > 0){
            E temp = list.get(i);
            list.set(i, list.get(parentVal));
            list.set(parentVal, temp);
            upwardsHeap(list, parentVal);
        }
    }

    //returns the max value at the root of the heap by swapping the last value 
    // and percolating the value down from the root to preserve max heap property
    // children of node at i are given by the formula 2i+1,2i+2, to not exceed the
    // bounds of the Heap index, namely, 0 ... size()-1.
    // throw appropriate exception
    public E removeHeap() {
        if (size == 0){
            throw new NoSuchElementException("The heap is empty");
        }
        else{
            E lastValue = heapList.get(size-1);
            heapList.set(0, lastValue);
            size--;

            downwardsHeap(heapList,0);

        }
        return heapList.get(0);
    }

    // Will recursively loop until the new max value is at the root.
    // Will get the last value and swap it with the root and then
    // heapify in order to correct any values.
    public void downwardsHeap(ArrayList<E> list, int i){
        int lastVal = i;
        int leftVal = 2 * i + 1;
        int rightVal = 2 * i + 2;

        if (leftVal < size && list.get(leftVal).compareTo(list.get(lastVal)) < 0){
            lastVal = leftVal;
        }
        if (rightVal < size && list.get(rightVal).compareTo(list.get(lastVal)) < 0){
            lastVal = rightVal;
        }
        if (lastVal != i){
            E temp = list.get(i);
            list.set(i, list.get(lastVal));
            list.set(lastVal, temp);

            downwardsHeap(list, lastVal);
        }
    }
    
    // takes a list of items E and builds the heap and then prints 
    // decreasing values of E with calls to removeHeap().  
    public void heapSort(List<E> list){
        // Will build the heap first and then remove the current max value
        // with calls to downwardsHeap() which will recursively loop
        // until the new max is at the root.
        buildHeap(list);
        for (int i = size; size > 0; size--){
            E temp = list.get(i);
            list.set(i, list.get(1));
            list.set(1, temp);
            removeHeap();
        }
        printArray();
    }
    
    // merges the other maxheap with this maxheap to produce a new maxHeap.  
    public void heapMerge(MaxHeap<E> other){
       this.buildHeap(other);
    }
    
    //takes a list of items E and builds the heap by calls to addHeap(..)
    public void buildHeap(List<E> list) {
        int startingIndex = (size-1);

        for (int i = startingIndex; i >= 0; i--){
            addHeap(list.get(i));
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList();
        list.add(5);
        list.add(10);
        list.add(7);
        list.add(2);
        MaxHeap heap = new MaxHeap();
        heap.addHeap(3);
        heap.addHeap(4);
        heap.addHeap(5);
        heap.addHeap(9);
        heap.addHeap(2);
        heap.printArray();
        //System.out.println(heap.findMax());
        System.out.println(heap.removeHeap());
        System.out.println();
        heap.printArray();
        //heap.buildHeap(list);


    }
    
}
