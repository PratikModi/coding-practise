package com.java.coding.interviews.practise.bloomberg;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache.
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 */
public class LRUCacheProblem {

    Map<Integer,Node> cacheMap;
    Node head=new Node();
    Node tail=new Node();
    int capacity;

    public LRUCacheProblem(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>(capacity);
        this.head.next=tail;
        this.tail.previous=head;
    }

    public int get(int key) {
        int result = -1;
        Node node = cacheMap.get(key);
        if(node!=null){
            result = node.value;
            remove(node);
            add(node);
        }
        return result;
    }

    public void put(int key, int value) {
        Node node = cacheMap.get(key);
        if(cacheMap.containsKey(key)){
            remove(node);
            node.value = value;
            add(node);
        }else{
            if(cacheMap.size()==capacity){
                cacheMap.remove(tail.previous.key);
                remove(tail.previous);
            }
            Node new_node = new Node(key,value);
            cacheMap.put(key,new_node);
            add(new_node);
        }
    }

    private void add(Node node){
        Node head_next = head.next;
        node.next = head_next;
        head_next.previous = node;
        head.next = node;
        node.previous = head;
    }

    private void remove(Node node){
        Node next = node.next;
        Node previous = node.previous;
        previous.next = next;
        next.previous = previous;
    }
}

class Node{
    int key;
    int value;
    Node next;
    Node previous;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.next=null;
        this.previous=null;
    }

    public Node() {
        this.next=null;
        this.previous=null;
    }
}
