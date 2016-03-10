import java.util.Arrays;

/**
 * Created by Morpheus on 2016-03-09.
 */
public class MergeSort {
    private int[] original;
    private int[] helper;

    public MergeSort(int[] arr) {
        mergeSort(arr);
    }

    public void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int idx = arr.length / 2;
            int[] leftArr = Arrays.copyOfRange(arr, 0, idx);
            int[] rightArr = Arrays.copyOfRange(arr, idx + 1, arr.length);
            mergeSort(leftArr);
            mergeSort(rightArr);
            merge(leftArr, rightArr);
        }
    }

    private void merge(int[] left, int[] right) {


    }

    public String toString() {
        String str = "";
        if (original.length == 0) {
            return "{}";
        } else {
            str += "{";
            for (int i = 0; i < original.length - 1; i++) {
                str += original[i] + ", ";
            }
            str += original[original.length - 1] + "}";
        }
        return str;
    }
}
