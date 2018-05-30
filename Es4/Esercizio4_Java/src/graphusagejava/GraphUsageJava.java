package graphusagejava;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import graph.*;
import priorityqueue.*;

public class GraphUsageJava
{

    private static final Charset ENCODING = StandardCharsets.UTF_8;

    public static void main(String[] args)
    {
        WeightedGraph<String> gp = new WeightedGraph<String>(false, new StringComparator());
        try{
            loadFile(args[0],gp);
            System.out.println("nNodi gp: "+gp.getnNode()+ " nArchi: "+gp.getnArch()+" weight: "+gp.weight()+"\n");
            WeightedGraph<String> primRis = gp.prim("poggiofiorito");
            System.out.println("nNodi prim: "+primRis.getnNode()+ " nArchi: "+primRis.getnArch()+" weight: "+primRis.weight());
        }catch(Exception ex){  
            ex.getMessage();
            ex.printStackTrace();
        }
        
    }


    private static void loadFile(String filepath,WeightedGraph<String> gp) throws IOException, NumberFormatException, GraphException {
        System.out.println("Loading data from file..."+ filepath +"\n");

        Path inputFilePath = Paths.get(filepath);
        try (BufferedReader fileInputReader = Files.newBufferedReader(inputFilePath, ENCODING)) {
            String line = null;
            while ((line = fileInputReader.readLine()) != null) {
               String[] lSplit = line.split(",");
               gp.addNode(lSplit[0]);
               gp.addNode(lSplit[1]);
               gp.addArch(lSplit[0],lSplit[1],Double.parseDouble(lSplit[2]));
            }
        }
        System.out.println("Data loaded\n");
    }
    
}