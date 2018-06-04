package graph;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author : Alberto Costamagna, Damiano Gianotti
 */
public class GraphTests
{
    WeightedGraph<String> weightedGraph;
    Graph<Integer, String> simpleGraph;
    Graph<Object, Object> emptyGraph;
    Graph<? extends Object, ? extends Object> nullGraph;

    @Before
    public void inzializeGraphs(){
        weightedGraph = new WeightedGraph<String>(false,new StringComparator());
        try {
            weightedGraph.addNode("A");
            weightedGraph.addNode("B");
            weightedGraph.addNode("C");
            weightedGraph.addNode("D");
            weightedGraph.addNode("E");
            weightedGraph.addNode("F");
            weightedGraph.addNode("K");

            weightedGraph.addArch("A","B",3d);
            weightedGraph.addArch("A","D",9d);
            weightedGraph.addArch("B","C",5d);
            weightedGraph.addArch("C","E",6d);
            weightedGraph.addArch("D","E",7d);
            weightedGraph.addArch("D","F",20d);
            weightedGraph.addArch("F","K",15d);
            weightedGraph.addArch("K","C",50d);
        } catch (Exception wException) {
            wException.getMessage();
            wException.printStackTrace();
        }

        simpleGraph = new Graph<Integer, String>(true);
        try {
            simpleGraph.addNode(1);
            simpleGraph.addNode(3);
            simpleGraph.addNode(7);
            simpleGraph.addNode(11);
            simpleGraph.addNode(15);
            simpleGraph.addNode(17);

            simpleGraph.addArch(1, 3, "Questo");
            simpleGraph.addArch(3, 11, "e'");
            simpleGraph.addArch(11, 7, "un");
            simpleGraph.addArch(7, 1, "possibile");
            simpleGraph.addArch(1, 7, "esempio");
            simpleGraph.addArch(7, 15, "di");
            simpleGraph.addArch(15, 17, "Grafo");
            simpleGraph.addArch(17, 11, "Diretto");
        } catch (Exception sException) {
            sException.getMessage();
            sException.printStackTrace();
        }

        emptyGraph = new Graph<Object, Object>(false);
    }

    @Test
    public void isnullGraph(){
        assertNull("This graph isn't null!" ,nullGraph);
    }

    @Test
    public void isEmptyW(){
        assertFalse(simpleGraph.isEmpty());
    }

    @Test
    public void isEmptyS(){
        assertFalse(simpleGraph.isEmpty());
    }

    @Test
    public void isEmptyE(){
        try {
            emptyGraph.addNode(true);
            assertFalse(emptyGraph.isEmpty());
        } catch (GraphException empty) {
            empty.getMessage();
        }
    }

    @Test
    public void correct_NumNodW(){
        assertTrue(weightedGraph.getnNode() == 7);
    }

    @Test
    public void correct_NumNodS(){
        assertTrue(simpleGraph.getnNode() == 6);
    }

    @Test
    public void correct_NumNodE(){
        assertTrue(emptyGraph.getnNode() == 0);
    }

    @Test
    public void correct_NumArchsW(){
        assertTrue(weightedGraph.getnArch() == 8);
    }

    @Test
    public void correct_NumArchsS(){
        assertTrue(simpleGraph.getnArch() == 8);
    }

    @Test
    public void correct_NumArchsE(){
        assertTrue(emptyGraph.getnArch() == 0);
    }

    @Test
    public void RemoveNodeW(){
        try {
            WeightedGraph<String> temp =  new WeightedGraph<String>(weightedGraph);
            temp.removeNode("E");
            temp.removeNode("K");
            assertTrue(temp.getnNode() == weightedGraph.getnNode() - 2);
            assertTrue(temp.getnArch() == weightedGraph.getnArch() - 4);
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void RemoveNodeS(){
        try {
            Graph<Integer, String> temp =  new Graph<Integer, String>(simpleGraph); 
            temp.removeNode(11);
            temp.removeNode(3);
            assertTrue(temp.getnNode() == simpleGraph.getnNode() - 2);
            assertTrue(temp.getnArch() == simpleGraph.getnArch() - 4);
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void RemoveNodeE(){
        try {
            Graph<Object, Object> temp =  new Graph<Object, Object>(emptyGraph);
            temp.addNode(true); 
            temp.removeNode(true);
            assertTrue(temp.getnNode() == emptyGraph.getnNode());
            assertTrue(temp.getnArch() == emptyGraph.getnArch());
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void RemoveArchW(){
        try {
            WeightedGraph<String> temp =  new WeightedGraph<String>(weightedGraph);
            temp.removeArch("A", "D", 9d);
            temp.removeArch("E", "C", 6d);
            assertTrue(temp.getnArch() == weightedGraph.getnArch() - 2);
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void RemoveArchS(){
        try {
            Graph<Integer, String> temp =  new Graph<Integer, String>(simpleGraph); 
            temp.removeArch(1, 3, "Questo");
            temp.removeArch(11, 7, "un");
            assertTrue(temp.getnArch() == simpleGraph.getnArch() - 2);
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void RemoveArchE(){
        try {
            Graph<Object, Object> temp =  new Graph<Object, Object>(emptyGraph);
            temp.addNode(true); 
            temp.addNode(false);
            temp.addArch(true, false, 'c');
            temp.removeArch(false, true, 'c');
            assertTrue(temp.getnArch() == emptyGraph.getnArch());
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void correct_NodeDegreeW(){
        try {
            assertTrue(weightedGraph.nodeDegree("E") == 2);
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void correct_NodeDegreeS(){
        try {
            assertTrue(simpleGraph.nodeDegree(7) == 4);
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void correct_NodeDegreeE(){
        try {
            emptyGraph.addNode(true);
            assertTrue(emptyGraph.nodeDegree(true) == 0);
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void adiancentNodeW(){
        try {
            assertTrue(weightedGraph.areAdiacent("E", "D"));
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void adiancentNodeS(){
        try {
            assertTrue(simpleGraph.areAdiacent(1, 7));
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void adiancentNodeE(){
        try {
            emptyGraph.addNode(false);
            emptyGraph.addNode(true);
            emptyGraph.addArch(true, false, 'c');
            assertTrue(emptyGraph.areAdiacent(true, false));
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void incidentArchW(){
        try {
            ArrayList<Arch<String, Double>> expectedList = new ArrayList<Arch<String, Double>>();
            expectedList.add(new Arch<String, Double>("E", "C", 6d));
            expectedList.add(new Arch<String, Double>("E", "D", 7d));
            ArrayList<Arch<String,Double>> resultList = weightedGraph.incidentArchs("E");
            int i = 0;
            boolean res = false;
            for (Arch<String, Double> arch : expectedList) {
                if (arch.equals(resultList.get(i)))
                    res = true;
            }
            assertTrue(res);
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void incidentArchS(){ 
        try {
            ArrayList<Arch<Integer, String>> expectedList = new ArrayList<Arch<Integer, String>>();
            expectedList.add(new Arch<Integer, String>(1, 7, "esempio"));
            expectedList.add(new Arch<Integer, String>(11, 7, "un"));
            ArrayList<Arch<Integer, String>> resultList = simpleGraph.incidentArchs(7);
            int i = 0;
            boolean res = false;
            for (Arch<Integer, String> arch : expectedList) {
                if (arch.equals(resultList.get(i)))
                    res = true;
            }
            assertTrue(res);
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void incidentArchE(){ 
        try {
            emptyGraph.addNode(3f);
            emptyGraph.addNode(15.4f);
            emptyGraph.addArch(3f, 15.4f, false);
            ArrayList<Arch<Float, Boolean>> expectedList = new ArrayList<Arch<Float, Boolean>>();
            expectedList.add(new Arch<Float,Boolean>(3f, 15.4f, true));
            ArrayList<Arch<Object, Object>> resultList = emptyGraph.incidentArchs(15.4f);
            int i = 0;
            boolean res = false;
            for (Arch<Float, Boolean> arch : expectedList) {
                if (arch.equals(resultList.get(i)))
                    res = true;
            }
            assertFalse(res);
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void Prim(){
        try {
            WeightedGraph<String> expectGraph = new WeightedGraph<String>(weightedGraph);
            expectGraph.removeArch("A", "D", 9d);
            expectGraph.removeArch("C", "K", 50d);
            WeightedGraph<String> resGraph = weightedGraph.prim("A");
            assertTrue(expectGraph.equals(resGraph));
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @Test
    public void correctWeight(){
        try {
            weightedGraph.addArch("E", "F", 4d);
            assertTrue(weightedGraph.weight() == 119);
        } catch (GraphException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void full(ArrayList<Arch<String, Double>> list){

    }
}