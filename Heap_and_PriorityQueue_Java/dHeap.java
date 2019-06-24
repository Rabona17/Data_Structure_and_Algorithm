import java.util.*;
public class dHeap<T extends Comparable<? super T>>
        implements dHeapInterface<T> {

    private final int DEFAULT_SIZE = 5;
    private T[] heap; //heap array
    private int d; //branching factor
    private int nelems;
    private boolean isMaxHeap;
    //boolean to indicate whether heap is max or min

    public dHeap() {
        //initializing
        int bianry = 2;
        d = bianry;
        heap = (T[]) new Comparable[DEFAULT_SIZE];
        isMaxHeap = true;
    }

    public dHeap(int heapSize) {
        int binary = 2;
        d = binary;
        //setting up the array
        heap = (T[]) new Comparable[heapSize];
        isMaxHeap = true;
    }

    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap)
            throws IllegalArgumentException {
        //if input is invalid
        if(d<1) {
            throw new IllegalArgumentException();
        }
        this.d = d;
        heap = (T[]) new Comparable[heapSize];
        this.isMaxHeap = isMaxHeap;
    }

    private void trickledown(int index){
        //keeping track of children nodes
        int childIndex = this.d * index+ 1;
        T value = this.heap[index];
        while(childIndex < this.size()){
            int maxminIndex = index;
            T maxminValue = value;
            //finding the max value of its children node
            for(int i = 0; i<d && i + childIndex < this.nelems; i++){
                //for maxheap
                if(isMaxHeap) {
                    if (heap[i + childIndex].compareTo(maxminValue) > 0) {
                        maxminIndex = i + childIndex;
                        maxminValue = heap[i + childIndex];
                    }

                }
                //for minheap
                else {
                    if(heap[i + childIndex].compareTo(maxminValue)<0){
                        maxminIndex = i + childIndex;
                        maxminValue = heap[i+childIndex];
                    }
                }
            }
            //swapping or return
            if(maxminIndex == index){

                return;
            }
            //remember to test if max index is zero
            else {
                //seapping
                T temp = heap[maxminIndex];
                heap[maxminIndex] = heap[index];
                heap[index] = temp;
                index = maxminIndex;
                childIndex = this.d * index +1;
            }
        }
    }

    private void bubbleup(int index){
        while(index>=0){
            if (isMaxHeap
                && heap[index].compareTo(heap[parent(index)]) <= 0) {
                return;
            }
            else if((!isMaxHeap)
                && heap[index].compareTo(heap[parent(index)]) >= 0){
                return;
            }
            else {
                T temp = heap[index];
                heap[index] = heap[parent(index)];
                heap[parent(index)] = temp;
                index = parent(index);
            }
        }
    }

    private int parent(int index){
        return (index - 1) / this.d<0?0:(index - 1) / this.d;
    }

    private void resize(){
        int double_size = 2;
        T[] newHeap = (T[]) new Comparable[heap.length * double_size];
        //copying
        for(int i = 0; i < heap.length;i++){
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    @Override
    public int size() {
        return nelems;
    }

    @Override
    public void add(T data) throws NullPointerException {
        //isfull?
        if(data==null){
            throw new NullPointerException();
        }
        if(nelems >= heap.length){
            resize();
        }
        heap[nelems] = data;
        nelems++;
        bubbleup(nelems-1);
    }

    @Override
    public T remove() throws NoSuchElementException {
        if(nelems == 0){
            throw new NoSuchElementException();
        }
        //swapping
        T temp = heap[nelems -1];
        heap[nelems-1] = heap[0];
        heap[0] = temp;
        nelems--;
        //trickling down
        trickledown(0);
        return heap[nelems];
    }

    @Override
    public void clear() {
        heap = (T[]) new Comparable[heap.length];
        nelems = 0;
    }

    @Override
    public T element() {
        //nothing?
        if (nelems==0){
            return null;
        }
        return heap[0];
    }

    public LinkedList findGreaterThanK(T k){
        LinkedList<T> returned = new LinkedList<>();
        T[] copyed = Arrays.copyOfRange(this.heap, 0,this.heap.length);
        //helper heap
        dHeap<T> copedHeap = new dHeap<>(d, heap.length, true);
        //deep copying
        copedHeap.heap = copyed;
        copedHeap.nelems = this.nelems;
        //finding
        while(copedHeap.element()!=null
            && copedHeap.element().compareTo(k)>0){
            returned.add(copedHeap.remove());
        }
        return returned;
    }

    public int findSum(int[] a, int num1, int num2){
        int num1Th = a[0];
        int num2Th;
        int curr = a[0];
        dHeap<Integer> helper = new dHeap<>(2, a.length, false);
        //copying
        for(int i = 0;i<a.length; i++){
            helper.add(a[i]);
        }
        //for finding till num2
        for(int i = 1;i<=num2;i++){
            curr = helper.remove();
            if(i == num1){
                num1Th = curr;
            }
        }
        //if num1 is num2
        if(num1==num2){
           num2Th = num1Th;
        }
        else {
            num2Th = curr;
        }
        return num1Th+num2Th;

    }

}
