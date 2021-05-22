package com.java.amazon.dynamic.airbnb;

import java.util.*;

/**
    Given a reference of a node in a connected undirected graph.
    Return a deep copy (clone) of the graph.
    Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

    class Node {
        public int val;
        public List<Node> neighbors;
    }

 [[2,4],[1,3],[2,4],[1,3]]
**/
public class CloneGraphProblem {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        List<Node> v = new ArrayList<>();
        v.add(node2);
        v.add(node4);
        node1.neighbors = v;
        v = new ArrayList<>();
        v.add(node1);
        v.add(node3);
        node2.neighbors = v;
        v = new ArrayList<>();
        v.add(node2);
        v.add(node4);
        node3.neighbors = v;
        v = new ArrayList<>();
        v.add(node3);
        v.add(node1);
        node4.neighbors = v;
        System.out.println(node1);
        System.out.println("=========================");
        System.out.println(cloneGraph(node1));
    }

    public static Node cloneGraph(Node node){
        if(node==null)
            return node;
        Queue<Node> Q = new LinkedList<>();
        Map<Node,Node> M = new HashMap();
        Q.add(node);
        M.put(node,new Node(node.val));

        while(!Q.isEmpty()){
            Node temp = Q.poll();
            Node clone = M.get(temp);
            if(temp.neighbors!=null && temp.neighbors.size()>0){
                for(Node N : temp.neighbors){
                    Node newNode= M.get(N);
                    if(newNode==null) {
                        newNode = new Node(N.val);
                        Q.add(N);
                        M.put(N, newNode);
                    }
                    clone.neighbors.add(newNode);
                }
            }
        }
        return M.get(node);
    }
}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node(int val) {
        this.val = val;
        this.neighbors = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                //", neighbors=" + neighbors +
                '}';
    }
}