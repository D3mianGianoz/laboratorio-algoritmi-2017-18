package graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        //Graph<String, Double> g = new Graph<String, Double>(false);
        Graph<String, Object>  nullGraph = new Graph<String, Object>(false);
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
            g.addArch("E","A",7d);
            g.addArch("C","E",12d);
            g.addArch("B","E",1d);
            
            //nullGraph.addArch("C","E","bella zio"); //Test che deve fallire
            
            g.printGraphDef();
            nullGraph.printGraphDef();
        
            System.out.println(g.Weight()+"\n");
            GrafoPesato<String> hm = g.prim("A");
            hm.printGraphDef();
            System.out.println(hm.Weight()+"\n");
            System.out.println("Grado nodo A: "+g.gradoNodo("A"));

            ArrayList<Arch<String,Double>> list = g.archiIncidenti("A");
            for(Arch<String, Double> temp : list)
                System.out.println("From: "+ temp.getFrom()+" To: "+ temp.getTo()+ " label: "+ temp.getLabel());

            System.out.println("\nNodo A e B sono adiacenti? "+g.sonoAdiacenti("A","B"));
            System.out.println("Nodo A e E sono adiacenti? "+g.sonoAdiacenti("A","E"));

            g.removeNode("A");
            System.out.println("\nDOPO LA RIMOZIONE DEL NODO A\n");
            g.printGraphDef();

            g.removeArch("E","B",1d);
            System.out.println("\nDOPO LA RIMOZIONE DELL' ARCO D -> B\n");
            g.printGraphDef();

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
