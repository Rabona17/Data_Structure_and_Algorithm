import java.util.ArrayList;
public class QuickSorts<T extends Comparable<? super T>> {
/**
     * This is the helper method to swap two element in an arraylist
     * @param numbers the list
     * @param pos1 first element to swap
     * @param pos2 second element to swap
     */
    private void swap(ArrayList<T> numbers, int pos1, int pos2){
        //temp obj
        T temp = numbers.get(pos1);
        numbers.set(pos1, numbers.get(pos2));
        numbers.set(pos2, temp);
    }

    /**
     * This is the helper method to make partition
     * @param numbers list to perform partition on
     * @param begin where to start
     * @param end where to end
     * @return the location of the pivot
     */
    private int Partition(ArrayList<T> numbers, int begin, int end) {
        int half = 2;
        int midpoint = begin + (end - begin) / half;
        T pivot = numbers.get(midpoint);
        // Pick middle element as pivot
        while (! (begin > end)) {
            // Decrement h while pivot < hth element in the list
            while (pivot.compareTo(numbers.get(end))<0) {
                end-=1;
            }
            //else make incremebnt
            while (numbers.get(begin).compareTo(pivot)<0) {
                begin+=1;
            }
            //if they meet, the partition is done
            if (begin >= end)
                break;
            //else, continue to swap
            else {
                swap(numbers, begin, end);
                ++begin;
                --end;
            }
        }

        return end;
    }

    /**
     * This is the module for merge sort
     * @param list to sort
     * @param start where to start to sort
     * @param end where to end the sort
     */
    public void QuickSort(ArrayList<T> list, int start, int end) {
        int mid;//tracking the mid value
        if(end - start <= 0){
            return;
        }
        //normal cases
        else {
            mid = Partition(list, start, end);
            QuickSort(list, start, mid);
            QuickSort(list, mid+1, end);
        }
    }
}
