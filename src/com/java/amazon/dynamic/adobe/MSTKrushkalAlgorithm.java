package com.java.amazon.dynamic.adobe;

import java.util.*;

/**
 * Find the minimum spanning tree of a connected, undirected graph with weighted edges.
 */
public class MSTKrushkalAlgorithm {
    public static void main(String[] args) {

    }

    public static List<Graph.Edge> findMST(Graph G){
        List<Graph.Edge> result = new ArrayList<>();
        Arrays.sort(G.edges);
        DisJointSet disJointSet = new DisJointSet();
        for(int i=0;i<G.V;i++){
            disJointSet.makeSet(i);
        }

        for(int i=0;i<G.V-1;i++){
            Graph.Edge edge = G.edges[i];

            long n1 = disJointSet.findSet(edge.src);
            long n2 = disJointSet.findSet(edge.dest);
            if(n1==n2)
                continue;
            else{
                disJointSet.union(n1,n2);
            }
            result.add(edge);
        }
        return result;
    }

}

class Graph{

    Edge[] edges;
    int V;
    int E;

    public Graph(int v, int e) {
        this.V=v;
        this.E=e;
        for(int i=0;i<E;i++){
            edges[i]=new Edge();
        }

    }

    class Edge implements Comparator<Edge>{
        int src;
        int dest;
        int weight;

        public Edge(int src, int dest,int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public Edge() {
        }

        @Override
        public int compare(Edge edge, Edge t1) {
            return edge.weight-t1.weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "src=" + src +
                    ", dest=" + dest +
                    ", weight=" + weight +
                    '}';
        }
    }


}

class DisJointSet{
    Map<Integer,Node> map = new HashMap<>();

    class Node{
        int data;
        Node parent;
        int rank;
    }

    public long findSet(int data){
        return map.get(data).data;
    }

    public Node findSet(Node node){
        Node parent = node.parent;
        if(parent==node)
            return parent;
        return findSet(node.parent);
    }

    public void makeSet(int data){
        Node node = new Node();
        node.data=data;
        node.parent=node;
        node.rank=0;
        map.put(data,node);
    }

    public boolean union(long data1, long data2){
        Node node1 = map.get(data1);
        Node node2 = map.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        if(parent1==parent2)
            return false;

        if(parent1.rank> parent2.rank){
            parent2.parent = parent1;
        }else if(parent1.rank< parent2.rank){
            parent1.parent = parent2;
        }else{
            parent2.parent=parent1;
            parent1.rank++;
        }
        return true;
    }

}