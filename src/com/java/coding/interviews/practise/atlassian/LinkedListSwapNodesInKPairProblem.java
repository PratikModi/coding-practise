package com.java.coding.interviews.practise.atlassian;

import java.util.Stack;

/**
     * Given Linked List Swap the nodes in a Pair of K
 */

public class LinkedListSwapNodesInKPairProblem {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        ListNode sixth = new ListNode(6);
        head.next=second;
        second.next=third;
        third.next=fourth;
        fourth.next=fifth;
        fifth.next=sixth;
        //System.out.println(swapNodes(head,3));
        System.out.println(swapNodeWithConstantSpace(head,3));
    }

    public static ListNode swapNodes(ListNode head, int K){
        if(head==null || K==0)
            return head;
        ListNode result = new ListNode(0);
        result.next = head;
        ListNode next = head;
        ListNode temp = result;
        Stack<ListNode> stack = new Stack<>();
        while(next!=null){
            for(int i=0;i<K;i++){
                if(next!=null) {
                    stack.push(next);
                    next=next.next;
                }
            }
            if(stack.size()<K)
                return result.next;
            while(!stack.isEmpty()){
                temp.next = stack.pop();
                temp = temp.next;
            }
            temp.next = next;
        }
        return result.next;
    }

    public static ListNode swapNodeWithConstantSpace(ListNode head, int K){
        if(head==null || K==0)
            return null;
        ListNode current = head;
        ListNode previous = null;
        ListNode next = null;

        int count=0;
        while(count<K && current!=null){
            next = current.next;
            current.next = previous;
            previous=current;
            current=next;
            count++;
        }
        if(next!=null)
            head.next = swapNodes(next,K);
        return previous;
    }


}

class ListNode{
    int value;
    ListNode next;

    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        ListNode current = this;
        while(current!=null){
            builder.append(current.value+"->");
            current=current.next;
        }
        return builder.toString();
    }
}
