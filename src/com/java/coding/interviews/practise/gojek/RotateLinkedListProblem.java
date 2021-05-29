package com.java.coding.interviews.practise.gojek;

/**
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * Example 2:
 *
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 */

public class RotateLinkedListProblem {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);

        head.next=second;
        second.next= third;
        third.next=fourth;
        fourth.next=fifth;

        System.out.println(head);
        System.out.println(rotateList(head,3));

    }

    public static ListNode rotateList(ListNode head, int K){
        if(head==null || K<=0 || head.next==null)
            return head;
        int L = 1;
        ListNode tail = head;
        while(tail.next!=null){
            L++;
            tail=tail.next;
        }
        K=K%L;
        if(K==0)
            return head;
        ListNode current = head;
        for(int pos=L-K-1;pos>0;pos--){
            current=current.next;
        }
        tail.next=head;
        head=current.next;
        current.next=null;
        return head;
    }

}

class ListNode{
    int value;
    ListNode next;

    public ListNode(int value) {
        this.value = value;
        this.next=null;
    }

    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder();
        ListNode current = this;
        while(current!=null){
            SB.append(current.value).append("->");
            current=current.next;
        }
        SB.setLength(SB.length()-2);
        return SB.toString();
    }
}
