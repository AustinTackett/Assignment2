public class InsertionSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int n = input.length;
        int[] arr = input.clone(); // Shallow copy, but int is immutable so functionally a deep copy

        int temp = 0;
        for (int i = 1; i < n; i++) {
            for(int j = i; j > 0 && arr[j-1] > arr[j]; j--) {
                temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
            }
        }

        return arr;
    }
}