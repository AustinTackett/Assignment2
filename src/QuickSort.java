public class QuickSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int n = input.length;
        int[] arr = input.clone(); // Shallow copy, but int is immutable so functionally a deep copy

        quickSort(arr, 0, arr.length-1); // Separate method call with separate parameters for recursion
        return arr;
    }

    private static void quickSort(int[] arr, int startIndex, int endIndex) {
        if(startIndex >= endIndex) {
            return;
        }
        int pivot = medianOfThree(arr, startIndex, endIndex);
        int partitionIndex = partition(arr, pivot, startIndex, endIndex);

        quickSort(arr, startIndex, partitionIndex);
        quickSort(arr, partitionIndex+1, endIndex);
    }

    private static int partition(int[] arr, int pivot, int startIndex, int endIndex) {
        int i = startIndex;
        int j = endIndex;
        int temp = 0;

        while(i < j) {
            while(arr[i] < pivot) {
                i++;
            }
            while(pivot < arr[j]) {
                j--;
            }
            if(i >= j) {
                break;
            } else {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        return j;
    }

    private static int medianOfThree(int[] arr, int left, int right) {
        // https://stackoverflow.com/questions/7559608/median-of-three-values-strategy

        // if left > center, and < right OR left is < center, and > right, left is median
        if ((arr[left] > arr[(left + right)/2]) ^ (arr[left] > arr[right]))  {
            return arr[left];
            // if center < left, and > right OR center is < right, and > left, center is median
        } else if ((arr[(left + right)/2] < arr[left]) ^ (arr[(left + right)/2] < arr[right])) {
            return arr[(left + right)/2];
            // else right is median
        } else {
            return arr[right];
        }
    }
}