package com.java.coding.interviews.practise.google;

/**
 * 160. Intersection of Two Linked Lists
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 */

/**
 * ✅ Complexity
 * 	•	Time: O(n + m) (each pointer traverses both lists once).
 * 	•	Space: O(1) (just two pointers).
 */
public class LinkedListInterSectionProblem {

    public static void main(String[] args) {
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(9);
        headA.next.next = new ListNode(1);
        ListNode headB = new ListNode(3);
        headB.next = new ListNode(2);
        headB.next.next = new ListNode(4);
        headA.next.next.next = headB.next;
        System.out.println(findInterSection(headA,headB));
    }

    private static ListNode findInterSection(ListNode headA, ListNode headB){
        if(headA==null || headB==null) return null;
        ListNode pointA = headA;
        ListNode pointB = headB;
        while(pointA!=pointB){
            pointA = pointA==null ? headB : pointA.next;
            pointB = pointB==null ? headA : pointB.next;
        }
        return pointA;
    }

}
class ListNode {
    int value;
    ListNode next;
    ListNode(int value){
        this.value=value;
        this.next=null;
    }
    public String toString(){
        return this.value+"";
    }
}