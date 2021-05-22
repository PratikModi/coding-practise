package com.java.amazon.dynamic;

import java.util.Stack;

/**
 * Created by Pratik1 on 20-04-2020.
 */
public class LinkedListSwapInKGroupProblem {

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        LinkedListNode second = new LinkedListNode(2);
        LinkedListNode third = new LinkedListNode(3);
        LinkedListNode fourth = new LinkedListNode(4);
        LinkedListNode fifth = new LinkedListNode(5);
        //LinkedListNode sixth = new LinkedListNode(6);
        head.next=second;
        second.next=third;
        third.next=fourth;
        fourth.next=fifth;
        //fifth.next=sixth;
        /*System.out.println(head);*/
        //System.out.println(reverseInKGroup(head,3));
        System.out.println(head);
        //System.out.println(reverseInKGroupUsingStack(head,3));
        System.out.println(reverseInKGroupUsingReversePointer(head,3));
    }

    public static LinkedListNode reverseInKGroup(LinkedListNode head, int k){
        if(head==null)
            return null;
        LinkedListNode root = new LinkedListNode(0);
        root.next = head;
        LinkedListNode next = head;
        for(int i=0;i<k;i++){
            if(next==null)
                return root.next;
            next=next.next;
        }

        LinkedListNode current = head;
        while(current.next!=null && current.next!=next){
            LinkedListNode second = current.next;
            current.next = second.next;
            second.next = root.next;
            root.next = second;
        }
        current.next = reverseInKGroup(next,k);
        return root.next;
    }

    public static LinkedListNode reverseInKGroupUsingReversePointer(LinkedListNode head, int K){
        if(head==null || K==0)
            return head;
        LinkedListNode current = head;
        LinkedListNode next=null;
        LinkedListNode previous=null;
        int count=K;
        int currentCount=0;
        LinkedListNode temp=head;
        while(currentCount<K && temp!=null){
            temp=temp.next;
            currentCount++;
        }
        if(currentCount<K)
            return head;
        while(count-->0 && current!=null){
            next = current.next;
            current.next=previous;
            previous=current;
            current=next;
        }
        if(current!=null) {
            head.next = reverseInKGroupUsingReversePointer(current, K);
        }
        return previous;
    }

    public static LinkedListNode reverseInKGroupUsingStack(LinkedListNode head, int k){
        if(head==null || k==0){
            return head;
        }
        LinkedListNode dummy = new LinkedListNode(0);
        dummy.next=head;
        LinkedListNode next = head;
        LinkedListNode result = dummy;
        Stack<LinkedListNode> stack = new Stack<>();
        
        while (next!=null) {
            for (int i = 0; i < k; i++) {
                if(next!=null) {
                    stack.push(next);
                    next = next.next;
                }
            }
            if (stack.size() != k) {
                return dummy.next;
            }
            while (!stack.isEmpty()) {
                result.next = stack.pop();
                result = result.next;
            }
            result.next = next;
        }
        return dummy.next;
    }

}
