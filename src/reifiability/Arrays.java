package reifiability;

public class Arrays {

    public static void main(String[] args) {
        Integer[] ints = new Integer[] { 1, 2, 3 };
        Number[] nums = ints;
        nums[0] = 3.14; //< Exception in thread "main" java.lang.ArrayStoreException: java.lang.Double
    }
}
