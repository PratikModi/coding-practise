package com.java.coding.interviews.practise.uipath;

/**
 * Delete all the nodes from the list that are greater than x
 * Difficulty Level : Easy
 * Last Updated : 23 Jan, 2020
 * Given a linked list, the problem is to delete all the nodes from the list that are greater than the specified value x.
 *
 * Examples:
 *
 * Input : list: 7->3->4->8->5->1
 *         x = 6
 * Output : 3->4->5->1
 *
 * Input : list: 1->8->7->3->7->10
 *         x = 7
 * Output : 1->7->3->7
 */
public class DeleteLinkedListNodeProblem {

    public static void main(String[] args) {
        ListNode head = new ListNode(7);
        head.next = new ListNode(3);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(1);
        System.out.println(deleteNode(head,6));
    }

    private static ListNode deleteNode(ListNode head, int K){
        if(head==null)
            return null;
        ListNode current = head;
        ListNode previous = null;

        if(current!=null && current.val>K){
            head = head.next;
            current=head;
        }
        while (current!=null) {
            while (current != null && current.val <= K) {
                previous = current;
                current = current.next;
            }
            if(current==null)
                return head;
            previous.next = current.next;
            current = previous.next;
        }
        return head;
    }
}

class ListNode{
    int val;
    ListNode next;

    public ListNode(int value) {
        this.val = value;
        this.next = null;
    }

    @Override
    public String toString() {
        ListNode current = this;
        StringBuilder SB = new StringBuilder();
        while(current!=null){
            SB.append(current.val).append("->");
            current=current.next;
        }
        SB.setLength(SB.length()-2);
        return SB.toString();
    }
}
