import java.util.ArrayList;
public class MergeSorts<T extends Comparable<? super T>> {

    /**
     * This is the helper method for merge
     * @param list to sort
     * @param start where to start to merge
     * @param end where to end the merge
     * @param mid where to split the array to merge
     */
    private void merge(ArrayList<T> list, int start, int end, int mid) {
        int leftPos = start;
        int rightPos = mid + 1;
        //store the temp value
        ArrayList<T> temp = new ArrayList<>();
        //comparing two lists and put the smaller one in temp.
        while (leftPos <= mid && rightPos <= end) {
            if (list.get(leftPos).compareTo(list.get(rightPos)) <= 0) {
                temp.add(list.get(leftPos));
                leftPos++;
            } else {
                temp.add(list.get(rightPos));
                rightPos++;
            }
        }
        //checking if there are any value left
        while (leftPos <= mid) {
            temp.add(list.get(leftPos));
            leftPos++;
        }
        //checking if there are any value left
        while (rightPos <= end) {
            temp.add(list.get(rightPos));
            rightPos++;
        }
        //modify the value on original list
        for (int i = 0; i < end - start + 1; i++) {
            list.set(start + i, temp.get(i));
        }
    }

    /**
     * This is the module for merge sort
     * @param list to sort
     * @param start where to start to sort
     * @param end where to end the sort
     */
    public void MergeSort(ArrayList<T> list, int start, int end) {
        //using recursion
        if(start<end){
            int half = 2;
            int mid = (start+end)/half;
            MergeSort(list, start, mid);
            MergeSort(list, mid+1, end);
            //merge the final
            merge(list, start, end, mid);
        }
    }
}
