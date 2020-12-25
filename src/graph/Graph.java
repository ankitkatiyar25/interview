package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> {

    private boolean isDirected;
    private List<Edge<T>> allEdges;
    private Map<Long, Vertex<T>> allVertex;

    public Graph () {
        this(false);
    }

    public Graph (boolean isDirected) {
        this.isDirected = isDirected;
        this.allEdges =  new ArrayList<>();
        this.allVertex = new HashMap<>();
    }

    public void addEdge(long id1, long id2, boolean isDirected, long weight) {
        Vertex<T> v1 = createOrGet(id1);
        Vertex<T> v2 = createOrGet(id2);

        Edge<T> edge = new Edge<>(v1,v2, isDirected, weight);
        allEdges.add(edge);

        v1.addAdj(edge, v2);
        if(!isDirected) {
            v2.addAdj(edge, v1);
        } else {
            v2.increaseInDegree();
        }
    }

    public boolean isDirected() {
        return isDirected;
    }

    public List<Edge<T>> getAllEdges() {
        return allEdges;
    }

    public Map<Long, Vertex<T>> getAllVertex() {
        return allVertex;
    }
    
    private Vertex<T> createOrGet(long id) {

        if(!allVertex.containsKey(id)) {
            Vertex<T> v1 = new Vertex<>(id);
            allVertex.put(id, v1);
            return v1;
        }

        return allVertex.get(id);
    }
}


class Vertex<T> {
    private long id;
    private T data;
    private List<Edge<T>> edges;
    private List<Vertex<T>> adjVertex;
    private int inDegree;

    public Vertex(long id1) {
        this.id = id;
        this.edges = new ArrayList<>();
        this.adjVertex =  new ArrayList<>();
    }

    public void addAdj(Edge edge, Vertex vertex) {
        this.edges.add(edge);
        this.adjVertex.add(vertex);
    }

    public void setData(T data) {
        this.data = data;
    }

    public void increaseInDegree() {
        this.increaseInDegree(1);
    }

    public void increaseInDegree(int increment) {
        this.inDegree += increment;
    }
}

class Edge<T> {
    private boolean isDirected;
    private long weight;
    private Vertex<T> fromVertex;
    private Vertex<T> toVertex;

    public Edge (Vertex v1, Vertex v2) {
        this (v1, v2, false, 1);
    }

    public Edge(Vertex v1, Vertex v2, boolean isDirected, long weight) {
        this.fromVertex = v1;
        this.toVertex = v2;
        this.isDirected = isDirected;
        this.weight = weight;
    }
}