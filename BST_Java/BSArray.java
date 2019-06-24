import java.util.ArrayList;

public class BinarySearchArray<T extends Comparable<? super T>> {

    private ArrayList<T> sortedArray;

    public BinarySearchArray() {
        sortedArray = new ArrayList<>();
    }

    public BinarySearchArray(ArrayList<T> sortedArray) {
        this.sortedArray = sortedArray;
    }

    private int binarySearch(T element) {
        int start = 0, end = sortedArray.size(), mid = 0;
        while (start < end) {
            mid = (end + start) / 2;
            int cmp = sortedArray.get(mid).compareTo(element);
            if (cmp == 0) {
                start = end = mid;
            }
            else if (cmp < 0) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        return start;
    }

    public boolean insert(T element) {
        int index = binarySearch(element);
        //if at the end, then must add
        if(index >= sortedArray.size()){
            sortedArray.add(index, element);
            return true;
        }

        //if found duplicate element, don't add
        if(sortedArray.get(index).compareTo(element)==0){
            return false;
        }
        sortedArray.add(index, element);
        return true;
    }

    public T find(T element) {
        int index = binarySearch(element);
        //if at the end, then must don't exist
        if(index >= sortedArray.size()){
            return null;
        }
        //if found
        if(sortedArray.get(index).compareTo(element)==0){
            return sortedArray.get(index);
        }
        return null;
    }
    
    public int getSize() {
        return sortedArray.size();
    }

    public void printArray() {
        System.out.println(sortedArray.toString());
    }

}
