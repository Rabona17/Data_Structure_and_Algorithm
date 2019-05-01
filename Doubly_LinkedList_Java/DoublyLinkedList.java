/**
 * This file contains a user defined data structure
 * DoublyLinkedList. Here two dummy nodes are used to avoid
 * complex code structure.
 */

import java.util.AbstractList;

/**
 * Doubly-Linked List Implementation
 *
 */
public class DoublyLinkedList<T> extends AbstractList<T> {
    private int nelems;//keeping track of number of elements
    private Node head;
    private Node tail;

    /**
     * Inner class for our node
     * Node for chaining together to create a linked list
     */
    protected class Node {
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         * @param element the value of the node
         */
        private Node(T element) {
            data = element;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            data = element;
            next = nextNode;
            prev = prevNode;
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            return data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            return next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {

            return prev;
        }

        /**
         * Remove this node from the list. 
         * Update previous and next nodes
         */
        public void remove() {
            prev.next = next;
            next.prev = prev;
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        Node firstDummy = new Node(null);
        Node tailDummy = new Node(null);
        firstDummy.setNext(tailDummy);
        tailDummy.setPrev(firstDummy);
        head = firstDummy;
        tail = tailDummy;
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        //TODO: Implementation for throwing exceptions followed by
        //implementation of adding the new data
        if(element == null){
            throw new NullPointerException();
        }
        Node toAdd = new Node(element, this.tail, this.tail.getPrev());
        this.tail.getPrev().setNext(toAdd);
        this.tail.setPrev(toAdd);
        this.nelems++;
        return true;
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     * @param element data to be added
     * @param index where data to be added
     * @throws NullPointerException if data received is null
     * @throws IndexOutOfBoundsException if index if out of bound
     *
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        //TODO: Implementation for throwing exceptions followed by 
        if(element == null){
            throw new NullPointerException();
        }
        //Invalid
        if(index<0){
            throw new IndexOutOfBoundsException();
        }
        Node toAdd = new Node(element);
        Node curr = this.head;
        //MOVING to index position
        while (index != 0){
            curr = curr.getNext();
            if(curr == this.tail){
                //invalid input
                throw new IndexOutOfBoundsException();
            }
            index --;
        }
        //manipulation on changing the reference
        curr.getNext().setPrev(toAdd);
        toAdd.setNext(curr.getNext());
        curr.setNext(toAdd);
        toAdd.setPrev(curr);
        this.nelems++;
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        this.tail.setPrev(this.head);
        this.head.setNext(this.tail);
        this.nelems = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * @param  element to check
     * @return true if contains, else false
     */
    @Override
    public boolean contains(Object element) {
        T data = (T)element;
        //TODO: Fill in implementation
        Node curr = this.head.getNext();
        //iterating through the linkedlist
        while (curr != tail){
            //found
            if(curr.getElement().equals(data)){
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index where to get the data
     * @return the data at index
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        //TODO: Fill in implementation to get the node at index
        Node curr = this.head.getNext();
        //invalid input
        if(index<0 || this.size()<index+1){
            throw new IndexOutOfBoundsException();
        }
        while(index>0){
            curr = curr.getNext();
//            if(curr == tail){
//                throw new IndexOutOfBoundsException();
//            }
            index --;
        }
        return curr.getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * @param index where to get the node
     * @return node at index
     */
    private Node getNth(int index) {
        Node curr = this.head.getNext();
        //invalid input
        if(index<0 || this.size()<index+1){
            throw new IndexOutOfBoundsException();
        }
        //finding
        while(index>0){
            curr = curr.getNext();
//            if(curr == tail){
//                throw new IndexOutOfBoundsException();
//            }
            index --;
        }
        return curr;
    }

    /**
     * Determine if the list empty
     *
     * @return true if the array is empty, false if else
     */
    @Override
    public boolean isEmpty() {
        //TODO: implement isEmpty
        return nelems == 0;
    }

    /**
     * Remove the element from position index in the list
     * @param index where to remove
     * @return the data in the removed node
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        //TODO: Fill in implementation
        Node toRm = getNth(index);
        //setting reference
        toRm.getPrev().setNext(toRm.getNext());
        toRm.getNext().setPrev(toRm.getPrev());
        this.nelems--;
        return toRm.getElement();
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * @param index the value at index to set
     *        element to add
     * @return the value set
     * @throws IndexOutOfBoundsException if indexoutpfbound
     * @throws NullPointerException if input is null
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        //TODO: Fill in implmentation
        //
        if(element == null){
            throw new NullPointerException();
        }
        //setting the value
        Node get = getNth(index);
        T data = get.getElement();
        get.setElement(element);
        return data;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return the size
     */
    @Override
    public int size() {
        return nelems;
    }
}
