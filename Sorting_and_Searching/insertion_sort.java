import java.util.ArrayList;
public class Insertion_Sorts<T extends Comparable<? super T>> {

    /**
     * This is the module for insertion sort
     * @param list to sort
     * @param start where to start to sort
     * @param end where to end the sort
     */
    public void InsertionSort(ArrayList<T> list, int start, int end) {
        //staring checking each element
        for(int i = start+1; i<=end;i++){
            int j = i ;
            //checking forward for further swap
            while(j>start && list.get(j).compareTo(list.get(j-1))<0){
                T temp = list.get(j);
                list.set(j, list.get(j-1));
                list.set(j-1, temp);
                j--;
            }
        }
    }
}
