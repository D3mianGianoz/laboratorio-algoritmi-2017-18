package sortusagejava;

import sort.*;
import java.io.*;
import java.util.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SumInVett {
    private static final Charset ENCODING = StandardCharsets.UTF_8;

    public static void main(String[] args) {
        if(args.length != 2)
            System.out.println("Usage: java sortusagejava/SumInVett '../../integers.csv' ../../sums.txt' ");
        String path = args[0];
        String path2 = args[1];
        ArrayList<Long> l = new ArrayList<Long>();
        ArrayList<Long> l2 = new ArrayList<Long>();
        try {
            loadFile(path, l);
            loadFile(path2, l2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Sort<Long> sort = new Sort<Long>();
            for (int i = 0; i < l2.size(); i++) {
                if (sort.isSumContained(l, l2.get(i)))
                    System.out.println("Numero: " + l2.get(i) + " somma TROVATA");
                else
                    System.out.println("Numero: " + l2.get(i) + " somma NON trovata");
            }
        } catch (SortException eS) {
            System.out.println("Errore");
        }
    }

    private static void loadFile(String filepath, ArrayList<Long> list) throws IOException {
        System.out.println("Loading data from file...\n");

        Path inputFilePath = Paths.get(filepath);
        try (BufferedReader fileInputReader = Files.newBufferedReader(inputFilePath, ENCODING)) {
            String line = null;
            while ((line = fileInputReader.readLine()) != null) {
                list.add(Long.parseLong(line));
            }
        }
        System.out.println("Data loaded\n");
    }
}