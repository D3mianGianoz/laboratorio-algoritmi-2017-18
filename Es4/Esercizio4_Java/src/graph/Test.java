package graph;

public class Test
{
    public static void main(String[] args)
    {
        Graph<Integer> g = new  Graph<Integer>();

        g.addNode(4);
        g.addNode(5);
        g.addArch(4,5);
        g.addNodeArch(8, 4);
        g.printGraph();
    }
}