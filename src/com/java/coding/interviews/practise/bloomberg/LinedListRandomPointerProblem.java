package com.java.coding.interviews.practise.bloomberg;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
 * where each new node has its value set to the value of its corresponding original node.
 * Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and
 * copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y,
 * then for the corresponding two nodes x and y in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 */
public class LinedListRandomPointerProblem {

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        LinkedListNode first = new LinkedListNode(2);
        LinkedListNode second = new LinkedListNode(3);
        LinkedListNode third = new LinkedListNode(4);
        head.next=first;
        first.next=second;
        second.next=third;
        head.random=third;
        first.random=second;
        second.random=head;
        third.random=first;
        System.out.println(head);
        System.out.println(copyRandomList(head));
        System.out.println(copyRandomListWithConstantSpace(head));
    }

    public static LinkedListNode copyRandomList(LinkedListNode head){
        if(head==null)
            return null;
        Map<LinkedListNode,LinkedListNode> cloneMap = new HashMap<>();
        LinkedListNode current = head;
        while(current!=null){
            LinkedListNode clone = new LinkedListNode(current.value);
            cloneMap.put(current,clone);
            current=current.next;
        }
        current=head;
        while(current!=null){
            cloneMap.get(current).next = cloneMap.get(current.next);
            cloneMap.get(current).random = cloneMap.get(current.random);
            current=current.next;
        }
        return cloneMap.get(head);
    }

    public static LinkedListNode copyRandomListWithConstantSpace(LinkedListNode head){
        if(head==null)
            return null;
        LinkedListNode current = head;
        LinkedListNode next = null;
        while(current!=null){
            LinkedListNode clone = new LinkedListNode(current.value);
            next = current.next;
            current.next=clone;
            clone.next=next;
            current=next;
        }
        current=head;
        while(current!=null){
            if(current.random!=null){
                current.next.random = current.random.next;
                current=current.next.next;
            }
        }
        current=head;
        LinkedListNode dummy = new LinkedListNode(0);
        LinkedListNode cloneDummy = dummy;
        LinkedListNode copy=null;
        while(current!=null){
            next=current.next.next;
            copy = current.next;
            cloneDummy.next=copy;
            cloneDummy=copy;
            current.next=next;
            current=next;
        }
        return dummy.next;
    }


}

class LinkedListNode{
    int value;
    LinkedListNode next;
    LinkedListNode random;

    public LinkedListNode(int value) {
        this.value = value;
        this.next = null;
        this.random = null;
    }

    public String toString(){
        StringBuilder toString = new StringBuilder();
        LinkedListNode current = this;
        while(current!=null){
            toString.append(current.value).append("->");
            current=current.next;
        }
        return toString.toString();
    }


}
