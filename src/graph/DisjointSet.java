package graph;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {

    Map<Integer, Node> map = new HashMap<>();

    class Node {
        int val;
        int rank;
        Node parent;

        public Node(int val) {
            this.val = val;
            this.rank = 0;
            this.parent = this;
        }
    }

    public void makeSet(int i) {

            Node n = new Node(i);
            map.put(i, n);

    }

    public Node findSet(int i) {

        if(! map.containsKey(i)) return null;

        Node n = map.get(i);
        if(n.parent.equals(n)) return n;

        n.parent = findSet(n.parent.val);

        return n.parent;

    }

    public void union (int i, int j) {
        Node ni = findSet(i);
        Node nj = findSet(j);
        if(ni != nj) {
            if (ni.rank > nj.rank) {
                nj.parent = ni;
            } else if (ni.rank < nj.rank) {
                ni.parent = nj;
            } else {
                nj.parent = ni;
                ni.rank = ni.rank + 1;
            }
        }
    }

    public static void main(String args[]) {
        DisjointSet ds = new DisjointSet();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);

        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);

        System.out.println(ds.findSet(1).val);
        System.out.println(ds.findSet(2).val);
        System.out.println(ds.findSet(3).val);
        System.out.println(ds.findSet(4).val);
        System.out.println(ds.findSet(5).val);
        System.out.println(ds.findSet(6).val);
        System.out.println(ds.findSet(7).val);
    }
}
