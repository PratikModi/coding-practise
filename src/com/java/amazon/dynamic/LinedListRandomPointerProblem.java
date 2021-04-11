package com.java.amazon.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pratik1 on 20-04-2020.
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
        System.out.println(copyLinkedListWithRandomPointers(head));
        System.out.println(copyLinkedListWithRandomPointersConstantSpace(head));
    }

    public static LinkedListNode copyLinkedListWithRandomPointers(LinkedListNode head){
        if(head==null){
            return null;
        }
        Map<LinkedListNode,LinkedListNode> cloneNodeMap = new HashMap<>();
        LinkedListNode current = head;
        //Create Map Key==> Node Value ==>It's Cloned Node
        while(current!=null){
            LinkedListNode clone = new LinkedListNode(current.value);
            cloneNodeMap.put(current,clone);
            current=current.next;
        }
        current=head;
        //Set pointers to next + random nodes
        while(current!=null){
            cloneNodeMap.get(current).next = cloneNodeMap.get(current.next);
            cloneNodeMap.get(current).random = cloneNodeMap.get(current.random);
            current=current.next;
        }
        return cloneNodeMap.get(head);
    }

    public static LinkedListNode copyLinkedListWithRandomPointersConstantSpace(LinkedListNode head){
        if(head==null){
            return null;
        }
        LinkedListNode current = head;
        LinkedListNode next =null;
        while(current!=null){
            next = current.next;
            LinkedListNode clone = new LinkedListNode(current.value);
            current.next = clone;
            clone.next = next;
            current = next;
        }
        current=head;
        while (current!=null){
            if(current.random!=null){
                current.next.random = current.random.next;
                current = current.next.next;
            }
        }
        current=head;
        LinkedListNode dummy = new LinkedListNode(0);
        LinkedListNode cloneDummy = dummy;
        LinkedListNode copy = null;
        while(current!=null){
            next = current.next.next;
            copy = current.next;
            cloneDummy.next=copy;
            cloneDummy=copy;
            current.next = next;
            current=next;
        }
        return dummy.next;
    }


}
