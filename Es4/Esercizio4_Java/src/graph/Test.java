package graph;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        Graph<String, Integer> g = new Graph<String, Integer>(true,new IntegerComparator());
        try {
            g.addNode("A");
            g.addNode("B");
            g.addNode("C");
            g.addNode("D");
            g.addNode("E");

            g.addArch("A","B",3);
            g.addArch("B","C",2);
            g.addArch("C","D",8);
            g.addArch("D","A",6);
            g.addArch("D","B",5);
            g.addArch("A","C",10);
            g.addArch("C","E",60);

            g.printGraph();

            System.out.println("Nodo a e b sono adiacenti? "+g.sonoAdiacenti("A","B"));
            System.out.println("Nodo a e b sono adiacenti? "+g.sonoAdiacenti("A","E"));

            int nArchi = g.archiIncidenti("B");
            System.out.println("N Archi B: "+nArchi);


            System.out.println("\nDOPO LA RIMOZIONE DEL NODO\n");
            g.removeNode("A");
            g.printGraph();

            System.out.println("\nDOPO LA RIMOZIONE DELL' ARCO\n");
            g.removeArch("D","B",5);
            g.printGraph();

            nArchi = g.archiIncidenti("B");
            System.out.println("N Archi B: "+nArchi);
            // g.addNode("CIAO"); //deve fallire
            /*
             * g.addArch("CIAO","COME",0); g.addArch("CIAO","PROVA",3);
             * g.addArch("PROVA","COME",9);
             */
            // g.addArch("Prova","TEST", 2); //deve fallire

            
            System.out.println(g.Weight());
        } catch (GraphException e) {
            e.getMessage();
            e.printStackTrace();
        }

    }
}

// Hash map dentro hash map
