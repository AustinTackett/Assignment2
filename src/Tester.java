import java.util.Random;

public class Tester {
    private SortingAlgorithm sorter;
    private Random gen;
    public Tester(SortingAlgorithm sa) {
        this.sorter = sa;
        this.gen = new Random();
    }

    public double singleTest(int size) {
        // timing method https://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java
        int[] arr = randomArr(size);
        long startTime = System.nanoTime();
        sorter.sorty(arr);
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime)/1000000;
        return (double)durationMs;
    }

    public void test(int iterations, int size) {
        double totalTime = 0;
        for(int i = 0; i < iterations; i++) {
            totalTime += singleTest(size);
        }
        double avg = totalTime/iterations;
        System.out.println("Sorted " + size + " elements in " + avg + " ms (avg)");
    }

    public double kSingleTest(int size) {
        int[] arr = generateKSorted(size, 10);
        long startTime = System.nanoTime();
        sorter.sorty(arr);
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime)/1000000;
        return (double)durationMs;
    }

    public void kTest(int iterations, int size) {
        double totalTime = 0;
        for(int i = 0; i < iterations; i++) {
            totalTime += kSingleTest(size);
        }
        double avg = totalTime/iterations;
        System.out.println("Sorted " + size + " elements in " + avg + " ms (avg)");
    }

    private int[] randomArr(int size) {
        if (size <= 0) {
            return new int[0];
        }
        int[] arr = new int[size];
        for(int i = 0; i < size; i++) {
            arr[i] = gen.nextInt();
        }
        return arr;
    }
    private int[] generateKSorted(int size, int k) {
        int[] arr = randomArr(size);
        int n = arr.length;

        int temp = 0;
        int gap = k;
        for(int i = gap; i < n; ++i) {
            temp = arr[i];
            int j;

            for(j = i; j >= gap && temp < arr[j-gap]; j -= gap) {
                arr[j] = arr[j - gap];
            }
            arr[j] = temp;
        }
        return arr;
    }



}