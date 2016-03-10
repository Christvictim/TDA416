import java.lang.reflect.Array;
import java.util.Random;

/**
 * Created by Morpheus on 2016-03-09.
 */
public class ArrayTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            RandomArray arr1 = new RandomArray(20, 100);
            arr1.insertRandomValues();
            System.out.println(arr1.toString());
            MergeSort sort1 = new MergeSort(arr1.getArray());
            sort1.toString();
        }
    }
}
class RandomArray {
    private int[] arr;
    private Random rand = new Random();
    private int range;

    public RandomArray(int length, int range) {
        arr = new int[rand.nextInt(length)];
        this.range = range;
    }

    public void insertRandomValues() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(this.range);
        }
    }

    public int[] getArray() {
        return arr;
    }

    @Override
    public String toString() {
        String str = "";
        if (arr.length == 0) {
            return "{}";
        } else {
            str += "{";
            for (int i = 0; i < arr.length - 1; i++) {
                str += arr[i] + ", ";
            }
            str += arr[arr.length - 1] + "}";
        }
        return str;
    }
}
