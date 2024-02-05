public class SelectionSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int n = input.length;
        int[] arr = input.clone(); // Shallow copy, but int is immutable so functionally a deep copy

        int temp = 0;
        int smallestIndex = 0;
        for(int i = 0; i < n; i++) {
            smallestIndex = i;
            for(int j = i+1; j < n; j++) {
                if (arr[smallestIndex] > arr[j]) {
                    smallestIndex = j;
                }
            }
            temp = arr[smallestIndex];
            arr[smallestIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
}