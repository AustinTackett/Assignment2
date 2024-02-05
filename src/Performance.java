import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileOutputStream;

public class Performance {
    public static void main(String args[]) {
        ///////////////////////////////////////////////////////////////////////////////////
        // true => write to file (this will erase previous performance.txt), false => print to console
        boolean WRITE = false;
        // true => 10 k-sorted testing, false => random sorting
        boolean KSORTED = true;
        ///////////////////////////////////////////////////////////////////////////////////

        SortingAlgorithm[] sortingArr = {new BubbleSort(), new InsertionSort(),
                                         new SelectionSort(), new ShellSort(),
                                         new QuickSort(), new MergeSort()};
        String[] nameArr = {"Bubble Sort", "Insertion Sort",
                            "Selection Sort", "Shell Sort",
                            "QuickSort", "MergeSort"};
        int[] sizeArr = {100, 500, 1000, 2000, 5000, 10000, 20000, 75000, 150000};

        //https://stackoverflow.com/questions/16237546/writing-to-console-and-text-file
        String fileName = "";
        if (KSORTED) {
            fileName = "kSortedPerformance.txt";
        } else {
            fileName = "performance.txt";
        }

        if (WRITE) {
            try (PrintStream fs = new PrintStream(new FileOutputStream(fileName))) {
                System.setOut(fs);
                performanceReport(sortingArr, nameArr, sizeArr, KSORTED);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            performanceReport(sortingArr, nameArr, sizeArr, KSORTED);
        }
    }
    private static void performanceReport(SortingAlgorithm[] sortingArr, String[] nameArr, int[] sizeArr, boolean kSorted) {
            int i = 0;
            String name = "";
            for(SortingAlgorithm sorter : sortingArr) {
                Tester tester = new Tester(sorter);
                name = nameArr[i];

                System.out.println("Sorting algorithm – " + name);

                for(int size : sizeArr) {
                    if (kSorted) {
                        tester.kTest(20, size);
                    } else {
                        tester.test(20, size);
                    }

                }
                System.out.print("\n");
                i++;
            }
    }
}
