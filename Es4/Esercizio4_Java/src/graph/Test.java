package graph;

import java.io.IOException;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        Graph<String, Double> g = new Graph<String, Double>(false,new StringComparator(), new DoubleComparator());
        try {
            g.addNode("A");
            g.addNode("B");
            g.addNode("C");
            g.addNode("D");
            g.addNode("E");

            g.addArch("A","B",3d);
            g.addArch("B","C",2d);
            g.addArch("C","D",8d);
            g.addArch("D","A",6d);
            g.addArch("D","B",5d);
            g.addArch("A","C",10d);
            g.addArch("D","E",7d);
            //g.addArch("C","E","bella zio"); //Test per Weight

            g.printGraph();
         //   HashMap hm = g.prim("A");

            /*
            g.printGraph();
            g.printGraphDef();  // Cosa ne pensi ?
            
            System.out.println("Nodo A e B sono adiacenti? "+g.sonoAdiacenti("A","B"));
            System.out.println("Nodo A e E sono adiacenti? "+g.sonoAdiacenti("A","E"));

            int nArchi = g.archiIncidenti("B");
            System.out.println("N Archi B: "+nArchi);

            System.out.println(g.Weight());

            g.removeNode("A");
            System.out.println("\nDOPO LA RIMOZIONE DEL NODO A\n");
            g.printGraphDef();

            g.removeArch("D","B",5d);
            System.out.println("\nDOPO LA RIMOZIONE DELL' ARCO D -> B\n");
            g.printGraphDef();

            nArchi = g.archiIncidenti("B");
            System.out.println("N Archi entranti in B: "+nArchi);
            */

        } catch (GraphException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
