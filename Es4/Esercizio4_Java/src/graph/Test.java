package graph;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        Graph<String, Integer> g = new Graph<String, Integer>(true);
        try {
            g.addNode("CIAO");
            g.addNode("PROVA");
            g.addNode("COME");

            // g.addNode("CIAO"); //deve fallire
            /*
             * g.addArch("CIAO","COME",0); g.addArch("CIAO","PROVA",3);
             * g.addArch("PROVA","COME",9);
             */
            // g.addArch("Prova","TEST", 2); //deve fallire

            g.printGraph();
            System.out.println(g.Weight());
        } catch (GraphException e) {
            e.getMessage();
            e.printStackTrace();
        }

    }
}

// Hash map dentro hash map
