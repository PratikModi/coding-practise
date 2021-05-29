package com.java.coding.interviews.practise.amazon;

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
 * Follow up:
 * Could you do get and put in O(1) time complexity?
 */
public class LRUCacheProblem {
    int capacity;
    Map<Integer,Node> map;
    Node head = new Node();
    Node tail = new Node();

    public LRUCacheProblem(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        head.next=tail;
        tail.previous=head;
    }

    public int get(int key){
        int result=-1;
        if(!map.containsKey(key))
            return result;
        else{
            Node node = map.get(key);
            result = node.value;
            remove(node);
            add(node);
        }
        return result;
    }

    public void put(int key, int value){
        Node node = map.get(key);
        if (node != null) {
            node.value=value;
            map.put(key,node);
            remove(node);
            add(node);
        }else{
            if(map.size()==capacity){
                map.remove(tail.previous.key);
                remove(tail.previous);
            }
            Node new_node = new Node(key,value);
            map.put(key,new_node);
            add(new_node);
        }

    }

    public void add(Node node){
        Node head_next= head.next;
        node.next=head_next;
        head_next.previous=node;
        head.next=node;
        node.previous=head;
    }

    public void remove(Node node){
        Node node_next = node.next;
        Node node_previous = node.previous;
        node_previous.next=node_next;
        node_next.previous=node_previous;
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
        this.next = null;
        this.previous = null;
    }

    public Node() {
        this.next = null;
        this.previous = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
