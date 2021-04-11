package com.java.amazon.dynamic.uber;

import java.util.*;

/**
 * Serialize and Deserialize N-ary Tree
 *
 * Solution
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * For example, you may serialize the following 3-ary tree
 */
public class NArySerializeDeserializeTree {
    private static final String DELIMETER = ",";
    public static void main(String[] args) {

    }

    private static String serialize(Node root){
        if(root==null)
            return null;
        StringBuilder sb = new StringBuilder();
        serializeUtil(root,sb);
        return sb.toString();
    }

    private static void serializeUtil(Node root, StringBuilder sb){
        if(root==null)
            return;
        sb.append(String.valueOf(root.value));
        sb.append(DELIMETER);
        sb.append(String.valueOf(root.children.size()));
        sb.append(DELIMETER);
        for(Node current:root.children){
            serializeUtil(current,sb);
        }
    }

    private static Node deserialize(String data){
        if(data==null || data.isEmpty())
            return null;
        Queue<String> Q = new LinkedList<>(Arrays.asList(data.split(DELIMETER)));
        return deserializeUtil(Q);
    }

    private static Node deserializeUtil(Queue<String> data){
        int value = Integer.parseInt(data.remove());
        int child = Integer.parseInt(data.remove());
        if(child==0){
            return new Node(value,new ArrayList<>());
        }
        List<Node> children = new ArrayList<>();
        for(int i=0;i<child;i++){
            children.add(deserializeUtil(data));
        }
        return new Node(value,children);
    }
}

class Node{
    int value;
    List<Node> children;

    public Node(int value, List<Node> children) {
        this.value = value;
        this.children = children;
    }
}
