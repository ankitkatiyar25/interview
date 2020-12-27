package graph;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class KruskalMST {

    DisjointSet ds = new DisjointSet();

    public void findMST(Graph<Integer> graph) {
        Map<Long, Vertex<Integer>> allVertex = graph.getAllVertex();
        List<Edge<Integer>> edges = graph.getAllEdges();
        Collections.sort(edges, (o1, o2) -> {return (int) (o1.getWeight() - o2.getWeight());});

        for(long id : allVertex.keySet()) {
            ds.makeSet((int)id);
        }

        for(Edge<Integer> edge : edges) {
            long v1 = ds.findSet(edge.getFromVertex().getId()).val;
            long v2 = ds.findSet(edge.getToVertex().getId()).val;
            if(v1 == v2) {
                continue;
            }
            ds.union(v1,v2);
            System.out.println(edge.getFromVertex().getId()+"->" + edge.getToVertex().getId());
        }
    }

}

class TestKMST {
    public static void main(String[] args) throws InterruptedException {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(0,1,4);
        graph.addEdge(0,2,1);
        graph.addEdge(1,2,2);
        graph.addEdge(1,4,3);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4,7);
        graph.addEdge(4, 5,8);
        graph.addEdge(3, 5,9);

        KruskalMST kruskalMST = new KruskalMST();
        kruskalMST.findMST(graph);
    }
}