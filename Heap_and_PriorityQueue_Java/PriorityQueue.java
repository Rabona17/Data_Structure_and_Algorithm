import java.util.NoSuchElementException;

public class MyPriorityQueue<T extends Comparable<? super T>> {

    public dHeap<T> container;
    public MyPriorityQueue(int initialSize) {
        container = new dHeap<>(initialSize);
    }

    public boolean offer(T element) throws NullPointerException {
        container.add(element);
        return true;
    }

    public T poll() {
        if(container.size()==0){
            return null;
        }
        return container.remove();
    }

    public void clear() {
        container.clear();
    }

    public T peek() {
        //if zero space?
        if(container.size()==0){
            return null;
        }
        return container.element();
    }
}
