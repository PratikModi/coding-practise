package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 430. Flatten a Multilevel Doubly Linked List
 */
public class FlattenLinkedListProblem {

    static class LinkedListNode {
            LinkedListNode next;
            LinkedListNode prev;
            LinkedListNode child;
            int val;

            LinkedListNode(int val){
                this.val=val;
                this.next=null;
                this.prev=null;
                this.child=null;
            }

            public String toString(){
                List<Integer> toString = new ArrayList<>();
                LinkedListNode current = this;
                while(current!=null){
                    toString.add(current.val);
                    current=current.next;
                }
                return toString.toString();
            }
    }

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        head.next.prev = head;
        head.next.next = new LinkedListNode(3);
        head.next.next.prev = head.next;
        head.next.next.child = new LinkedListNode(7);
        head.next.next.child.next = new LinkedListNode(8);
        head.next.next.child.next.prev = head.next.next.child;
        System.out.println(flatten(head));
    }

    public static LinkedListNode flatten(LinkedListNode head) {
        if(head==null) return null;
        Stack<LinkedListNode> stack = new Stack<>();
        LinkedListNode current = head;

        while(current!=null){
            if(current.child!=null){
                if(current.next!=null){
                    stack.push(current.next);
                }
                current.next=current.child;
                current.child.prev=current;
                current.child=null;
            }
            if(current.next==null && !stack.isEmpty()){
                current.next = stack.pop();
                current.next.prev = current;
            }
            current=current.next;
        }
        return head;
    }
}