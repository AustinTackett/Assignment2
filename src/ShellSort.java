public class ShellSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int n = input.length;
        int[] arr = input.clone(); // Shallow copy, but int is immutable so functionally a deep copy

        int temp = 0;
        for(int gap = n / 2; gap > 0; gap /= 2) {
            for(int i = gap; i < n; ++i) {
                temp = arr[i];
                int j;

                for(j = i; j >= gap && temp < arr[j-gap]; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
        return arr;
    }
}