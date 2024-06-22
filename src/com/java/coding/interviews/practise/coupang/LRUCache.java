package com.java.coding.interviews.practise.coupang;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    int capacity;
    Node head= new Node();
    Node tail = new Node();
    Map<Integer,Node> nodeMap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.nodeMap = new HashMap<>(capacity);
        head.next=tail;
        tail.previous=head;
    }
    public int get(int key){
        int result=-1;
        if(!nodeMap.containsKey(key))
            return result;
        Node node = nodeMap.get(key);
        result = node.value;
        remove(node);
        add(node);
        return result;
    }

    public void put(int key, int value){
        Node node = nodeMap.get(key);
        if(node!=null){
            node.value=value;
            nodeMap.put(key,node);
            remove(node);
            add(node);
        }else{
            if(nodeMap.size()==capacity){
                nodeMap.remove(tail.previous.key);
                remove(tail.previous);
            }
            Node new_node = new Node(key,value);
            nodeMap.put(key,new_node);
            add(new_node);
        }
    }

    private void add(Node node){
        Node head_next = head.next;
        node.next=head_next;
        head_next.previous=node;
        head.next=node;
        node.previous=head;
    }

    private void remove(Node node){
        Node next = node.next;
        Node previous = node.previous;
        previous.next=next;
        next.previous=previous;
    }
}

class Node{
    Node next;
    Node previous;
    int value;
    int key;

    Node(int key, int value){
        this.key=key;
        this.value=value;
        
    }

    public Node() {
        this.next=null;
        this.previous=null;
    }
}
