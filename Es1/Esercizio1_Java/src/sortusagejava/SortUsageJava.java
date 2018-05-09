package sortusagejava;

import sort.*;
import java.io.*;
import java.util.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SortUsageJava {

    private static final Charset ENCODING = StandardCharsets.UTF_8;

    public static void main(String[] args) {

        long startTime = System.nanoTime();

        ArrayList<Long> list = new ArrayList<>();
        String path = args[0];
        String par = args[1];
        try {
            loadFile(path, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            LongComparator longComp = new LongComparator();
            Sort<Long> order = new Sort<Long>();
            if (par.equalsIgnoreCase("insertion"))
                printInsertionSort(list, longComp, order);
            else if (par.equalsIgnoreCase("merge"))
                printMergeSort(list, longComp, order);
            else
                System.out.println(
                        "Error using main parameters: type the path of the file followed by insertion for Insertion sort, merge for merge sort !");
        } catch (SortException eS) {
            System.out.println("Errore");
        }

        long endTime = System.nanoTime();
        long totalTime = (endTime - startTime) / 1000000;
        System.out.println("\nTime to compute: " + totalTime + "ms");
    }

    private static void loadFile(String filepath, ArrayList<Long> sortlist) throws IOException {
        System.out.println("Loading data from file...\n");

        Path inputFilePath = Paths.get(filepath);
        try (BufferedReader fileInputReader = Files.newBufferedReader(inputFilePath, ENCODING)) {
            String line = null;
            while ((line = fileInputReader.readLine()) != null) {
                sortlist.add(Long.parseLong(line));
            }
        }
        System.out.println("Data loaded\n");
    }

    private static void printInsertionSort(ArrayList<Long> insList, LongComparator longComp, Sort<Long> order)
            throws SortException {
        System.out.println("This is the SortedList using algoritm Insertion Sort\n");
        order.insertionSort(insList, longComp);
        order.printArray(insList);
    }

    private static void printMergeSort(ArrayList<Long> mergList, LongComparator longComp, Sort<Long> order)
            throws SortException {
        System.out.println("\nThis is the SortedList using algoritm Merge Sort\n");
        order.mergeSort(mergList, 0, mergList.size() - 1, longComp);
        order.printArray(mergList);
    }

}
