package graph;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;


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
            assertTrue(temp.getnArch() == simpleGraph.getnNode() - 4);
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
            assertTrue(temp.getnArch() == emptyGraph.getnNode());
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void corretct_NodeDegreeW(){
        try {
            assertTrue(weightedGraph.nodeDegree("E") == 2);
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void corretct_NodeDegreeS(){
        try {
            assertTrue(simpleGraph.nodeDegree(7) == 4);
        } catch (GraphException ex) {
            ex.getMessage();
        }
    }

    @Test
    public void corretct_NodeDegreeE(){
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
    public void correctWeight(){
        try {
            weightedGraph.addArch("E", "F", 4d);
            assertTrue(weightedGraph.weight() == 119);
        } catch (GraphException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

}