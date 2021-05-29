package com.java.coding.interviews.practise.airbnb;

/**
 * Given a linked list and a positive integer k, rotate the list to the right by k places.
 *
 * For example, given the linked list 7 -> 7 -> 3 -> 5 and k = 2, it should become 3 -> 5 -> 7 -> 7.
 *
 * Given the linked list 1 -> 2 -> 3 -> 4 -> 5 and k = 3, it should become 3 -> 4 -> 5 -> 1 -> 2.
 */

public class RotateLinkedList {

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        LinkedListNode second = new LinkedListNode(2);
        LinkedListNode third = new LinkedListNode(3);
        LinkedListNode fourth = new LinkedListNode(4);
        LinkedListNode fifth = new LinkedListNode(5);
        head.next=second;
        second.next=third;
        third.next=fourth;
        fourth.next=fifth;

        System.out.println(head);
        System.out.println(rotateLinkedListByKPlace(head,3));

    }

    public static LinkedListNode rotateLinkedListByKPlace(LinkedListNode head,int K){
        if(head==null){
            return head;
        }
        LinkedListNode current = head;
        LinkedListNode KthNode = head;
        int counter=1;
        while(counter<K && KthNode!=null){
            KthNode=KthNode.next;
            counter++;
        }
        System.out.println(KthNode);
        while(current.next!=null){
            current=current.next;
        }
        System.out.println(current);
        current.next=head;
        head=KthNode.next;
        KthNode.next=null;
        return head;
    }

}

class LinkedListNode{
    LinkedListNode next;
    int value;
    LinkedListNode(int value){
        this.value=value;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        LinkedListNode temp = this;
        while(temp!=null){
            sb.append(temp.value).append("->");
            temp=temp.next;
        }
        sb.setLength(sb.length()-2);
        return sb.toString();
    }
}
