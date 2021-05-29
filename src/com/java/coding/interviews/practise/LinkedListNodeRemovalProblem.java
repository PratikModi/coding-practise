package com.java.coding.interviews.practise;

/**
 * Created by Pratik1 on 20-04-2020.
 */
public class LinkedListNodeRemovalProblem {

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        LinkedListNode second = new LinkedListNode(2);
        LinkedListNode third = new LinkedListNode(3);
        LinkedListNode fourth = new LinkedListNode(4);
        LinkedListNode fifth = new LinkedListNode(5);
        LinkedListNode sixth = new LinkedListNode(6);
        head.next=second;
        second.next=third;
        third.next=fourth;
        fourth.next=fifth;
        fifth.next=sixth;
        System.out.println(head);
        System.out.println(removeNthNodeFromLast(head,3));
    }

    public static LinkedListNode removeNthNodeFromLast(LinkedListNode head, int n){
        if(head==null)
            return head;
        int size=0;
        LinkedListNode current = head;
        while(current!=null){
            current=current.next;
            size++;
        }
        //System.out.println(size);
        if(size<=n){
            return head.next;
        }
        current=head;
        for(int i=0;i<size-n-1;i++){
            current=current.next;
        }
        System.out.println(current.value);
        if(current.next!=null){
            current.next = current.next.next;
        }
        return head;
    }
}
