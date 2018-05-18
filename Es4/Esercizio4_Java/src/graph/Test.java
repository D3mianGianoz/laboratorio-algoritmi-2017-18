package graph;

public class Test
{
    public static void main(String[] args)
    {
        Graph<String,Integer> g = new Graph<String,Integer>(true);

        g.addNode("CIAO");
        g.addNode("PROVA");
        g.addNode("COME");
        g.addArch("CIAO","COME",1);
        g.addArch("CIAO","PROVA",3);
        g.addArch("PROVA","COME",9);

        g.printGraph();

    }
}



// Hash map dentro hash map

