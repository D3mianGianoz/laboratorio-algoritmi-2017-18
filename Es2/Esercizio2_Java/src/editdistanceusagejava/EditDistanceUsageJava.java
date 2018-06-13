package editdistanceusagejava;

import editdistance.EditDistance;

import java.awt.List;
import java.io.*;
import java.util.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Costamagna Alberto e Gianotti Damiano
 */
public class EditDistanceUsageJava {

    private static final Charset ENCODING = StandardCharsets.UTF_8;

    public static void main(String[] args) {

        if (args.length != 2)
            System.out.println("Usage: java EditDistanceUsage 'dictionary.txt' 'correctme.txt'");

        long startTime = System.nanoTime();

        String dictionary = args[0];
        String Jlennon = args[1];

        ArrayList<String> dictList = new ArrayList<String>();
        ArrayList<String> jlenList = new ArrayList<String>();

        try {
            loadFile(dictionary, dictList);
            loadFile(Jlennon, jlenList, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        spellChecker(dictList, jlenList);

        long endTime = System.nanoTime();
        long totalTime = (endTime - startTime) / 1000000;
        System.out.println("\nTime to compute: " + totalTime + "ms");
    }

    private static void loadFile(String filepath, ArrayList<String> wordlist) throws IOException {
        System.out.println("Loading data from file" + filepath + "...\n");

        Path inputFilePath = Paths.get(filepath);
        try (BufferedReader fileInputReader = Files.newBufferedReader(inputFilePath, ENCODING)) {
            String line = null;
            while ((line = fileInputReader.readLine()) != null) {
                wordlist.add(line);
            }
        }
        System.out.println("Data loaded\n");
    }

    private static void loadFile(String filepath, ArrayList<String> wordlist, boolean split) throws IOException {

        System.out.println("Loading data from file" + filepath + "...\n");

        Path inputFilePath = Paths.get(filepath);
        try (Scanner quoteScanner = new Scanner(inputFilePath)) {
            quoteScanner.useDelimiter("[^A-Za-z^]+");
            while (quoteScanner.hasNext()) {
                String token = quoteScanner.next();
                wordlist.add(token.toLowerCase());
            }
        }
        System.out.println("Data loaded\n");
    }

    /*
     * lista di parole con edit distance minima; Se edit > min --> non fare nulla; se
     * edit = min --> aggiungi alla lista; se edit < min --> scarta la vecchia lista
     * e iniziane una nuova
     */
    private static void spellChecker(ArrayList<String> dictionary, ArrayList<String> quote) {

        ArrayList<String> result = new ArrayList<String>();
        String word, vocable;
        int min, edit;
        for (int i = 0, k = 0; i < quote.size(); i++, result.clear(), k = 0) {
            word = quote.get(i);
            vocable = dictionary.get(0);
            min = edit = EditDistance.edit_distance_dyn(word, vocable);

            for (k = 1; k < dictionary.size() && min != 0; k++) {

                vocable = dictionary.get(k);
                edit = EditDistance.edit_distance_dyn(word, vocable);

                if (edit < min) {
                    result.clear();
                    result.add(vocable);
                    min = edit;
                } else if (edit == min)
                    result.add(vocable);
            }

            System.out.print("La parola\t" + word + "\t" + "\tha edit distance " + min);

            if (min == 0 || result.isEmpty())
                System.out.print(" ed e' corretta!\n");
            else {
                System.out.print(" ed e' sbagliata!\n\nEcco la lista di vocaboli suggeriti\n\n");
                result.forEach(System.out::println);
                System.out.println();
            }
        }
    }
}