public class MergeSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int n = input.length;
        int[] arr = input.clone();

        mergeSort(arr, 0, arr.length-1);
        return arr;
    }

    private void mergeSort(int[] arr, int startIndex, int endIndex) {
        if(startIndex >= endIndex) {
            return;
        }

        int middleIndex = startIndex + (endIndex - startIndex)/2;
        mergeSort(arr, startIndex, middleIndex);
        mergeSort(arr, middleIndex+1, endIndex);

        merge(arr, startIndex, middleIndex, endIndex);
    }

    private void merge(int[] arr, int startIndex, int middleIndex, int endIndex) {
        // https://www.geeksforgeeks.org/merge-sort/
        int leftLen = middleIndex-startIndex + 1;
        int rightLen = endIndex-middleIndex;

        int[] leftArr = new int[leftLen];
        for(int i = 0; i < leftLen; i++) {
            leftArr[i] = arr[startIndex + i];
        }
        int[] rightArr = new int[rightLen];
        for(int i = 0; i < rightLen; i++) {
            rightArr[i] = arr[(middleIndex+1) + i];
        }

        int i = 0;
        int j = 0;
        int mergeIndex = startIndex;
        while(i < leftLen && j < rightLen) {
            if(leftArr[i] <= rightArr[j]) {
                arr[mergeIndex] = leftArr[i];
                i++;
            } else {
                arr[mergeIndex] = rightArr[j];
                j++;
            }
            mergeIndex++;
        }

        while(i < leftLen) {
            arr[mergeIndex] = leftArr[i];
            i++;
            mergeIndex++;
        }


        while(j < rightLen) {
            arr[mergeIndex] = rightArr[j];
            j++;
            mergeIndex++;
        }
    }
}