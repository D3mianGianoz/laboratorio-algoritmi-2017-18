package graph;

import java.io.IOException;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        //Graph<String, Double> g = new Graph<String, Double>(false,new StringComparator(), new DoubleComparator());
        //Graph<String, Object> g = new Graph<String, Object>(false);
        GrafoPesato<String>g = new GrafoPesato<String>(false,new StringComparator());
        try {
            g.addNode("A");
            g.addNode("B");
            g.addNode("C");
            g.addNode("D");
            g.addNode("E");

            g.addArch("A","B",3d);
            g.addArch("B","C",10d);
            g.addArch("C","D",2d);
            g.addArch("D","A",5d);
            g.addArch("C","E",12d);
            g.addArch("B","E",1d);
            
            //nILGraph.addArch("C","E","bella zio"); //Test che deve fallire
            
            g.printGraphDef();
            //nILGraph.printGraphDef();
            System.out.println(g.Weight());
            GrafoPesato<String> hm = g.prim("A");
            hm.printGraphDef();

            /*
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

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
