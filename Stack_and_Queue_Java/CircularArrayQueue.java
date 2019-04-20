import java.util.NoSuchElementException;

/**
 * This Class is CircularArrayQueue, which serve as a user defined data structure Queue
 * it provides methos like dequeue, enqueue, peek, clear, etc.
 * it implements QueueADT interface
 */
public class CircularArrayQueue implements QueueADT {
    private static final int DEFAULT_SIZE = 5;
    // Example for declaring magic numbers
    private static final int GROWTH_FACTOR = 2;
    // Example for declaring magic numbers
    //initializing
    private int[] circularArray;
    private int length;
    private int head;
    //tail be negative 1 as nothing is inside.
    private int tail = -1;

    /**
     * Constructor of the class, default size is 5
     * @return void
     */
    public CircularArrayQueue() {
        //non-param constructors
        this.circularArray = new int[DEFAULT_SIZE];
    }

    /**
     * Constructor of the class with user input capacity
     * @param capacity
     */
    public CircularArrayQueue(int capacity) {
        this.circularArray = new int[capacity];
    }

    /**
     * Adding/enqueueing an element to the queue
     * @param elem integer to add
     * @return void
     */
    public void add(int elem) {
        //check if to resize
        if(length == this.circularArray.length){
            this.resize();
        }
        //adding
        this.tail = (tail+1>this.circularArray.length-1) ? 0 : tail+1;
        this.circularArray[this.tail] = elem;
        //add the length as well
        this.length++;
    }

    /**
     * resizing an array to double its capacity and copy the original value
     * helper method
     * @return void
     */
    private void resize() {
        int[] newArr = new int[circularArray.length * GROWTH_FACTOR];
        //copying value to the new arr
        for (int i=head; i<circularArray.length;i++) {
            newArr[i] = circularArray[i];
        }
        tail = circularArray.length-1;
        //copying the rest
        for (int i=0; i<head;i++) {
            tail++;
            newArr[tail] = circularArray[i];
        }
        this.circularArray = newArr;
    }

    /**
     * Check if the queue is empty
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        //using length to check
        if(length == 0){
            return true;
        }
        return false;
    }

    /**
     * Peeking the top element of a queue
     * @return the first element of a queue
     * @throws NoSuchElementException if the queue is empty
     */
    public int peek() throws NoSuchElementException {
        //throw an exception if empty
        if(this.isEmpty()){
            throw new NoSuchElementException();
        }
        return this.circularArray[head];
    }

    /**
     * dequeuing from the queue and remove the first
     * @return the first element of the queue
     * @throws NoSuchElementException is the queue is empty
     */
    public int remove() throws NoSuchElementException {
        //throw exception if empty
        if(this.isEmpty()){
            throw new NoSuchElementException();
        }
        int removed = this.circularArray[head];
        //reposition head;
        this.head = (head+1>this.circularArray.length-1) ? 0 : this.head+1;
        length--;
        return removed;
    }

    /**
     * clear the queue: clear all its instance variables
     * @return void
     */
    public void clear() {
        length = 0;
        head = 0;
        //same as from constructor
        tail = -1;
    }

    /**
     * get the size of the queue
     * @return the size of the queue
     */
    public int size() {
        return length;
    }
}
