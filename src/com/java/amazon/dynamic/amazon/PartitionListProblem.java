package com.java.amazon.dynamic.amazon;

/**
 * Created by Pratik1 on 07-06-2020.
 */

/**
 * This problem was asked by Amazon.

 Given a pivot x, and a list lst, partition the list into three parts.

 The first part contains all elements in lst that are less than x
 The second part contains all elements in lst that are equal to x
 The third part contains all elements in lst that are larger than x
 Ordering within a part can be arbitrary.

 For example, given x = 10 and lst = [9, 12, 3, 5, 14, 10, 10],
 one partition may be [9, 3, 5, 10, 10, 12, 14].
 */


public class PartitionListProblem {
    public static void main(String[] args) {
        ListNode head = new ListNode(9);
        ListNode second = new ListNode(12);head.next=second;
        ListNode third = new ListNode(3);second.next=third;
        ListNode fourth = new ListNode(5);third.next=fourth;
        ListNode fifth = new ListNode(14);fourth.next=fifth;
        ListNode sixth = new ListNode(10);fifth.next=sixth;
        ListNode seventh = new ListNode(10);sixth.next=seventh;
        System.out.println(partitionList(head,10));
    }

    private static ListNode partitionList(ListNode head ,int pivot){
        if(head==null)
            return head;
        System.out.println(head);
        ListNode less_head = new ListNode(0);
        ListNode less = less_head;
        ListNode eq_head = new ListNode(0);
        ListNode eq = eq_head;
        ListNode greater_head = new ListNode(0);
        ListNode greater = greater_head;
        while (head!=null){
            if(head.value<pivot){
                less.next = new ListNode(head.value);
                less = less.next;
            }else if(head.value>pivot){
                greater.next = new ListNode(head.value);
                greater = greater.next;
            }else{
                eq.next = new ListNode(head.value);
                eq = eq.next;
            }
            head = head.next;
        }
        if(greater.next!=null){
            greater.next=null;
        }
        if(eq.next!=null){
            eq.next=null;
        }
        less.next=eq_head.next;
        eq.next = greater_head.next;

        return less_head.next;
    }
}
class ListNode{
    public int value;
    public ListNode next;

    public ListNode(int value) {
        this.value = value;
        this.next=null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        ListNode temp = this;
        while(temp!=null){
            sb.append(temp.value);
            sb.append("->");
            temp = temp.next;
        }
        sb.setLength(sb.length()-2);
        return sb.toString();
    }
}