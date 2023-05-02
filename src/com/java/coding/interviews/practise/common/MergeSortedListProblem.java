package com.java.coding.interviews.practise.common;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists
 *
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 *
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 *
 * Input: lists = []
 * Output: []
 * Example 3:
 *
 * Input: lists = [[]]
 * Output: []
 */
public class MergeSortedListProblem {

    public static void main(String[] args) {

        List<ListNode> nodes = new ArrayList<>();
        ListNode node1 = new ListNode(1);
        node1.next= new ListNode(4);
        node1.next.next = new ListNode(5);
        nodes.add(node1);
        ListNode node2 = new ListNode(1);
        node2.next= new ListNode(3);
        node2.next.next = new ListNode(4);
        nodes.add(node2);
        ListNode node3 = new ListNode(2);
        node3.next= new ListNode(6);
        nodes.add(node3);
        System.out.println(mergeSortedList(nodes));
    }

    public static ListNode mergeSortedList(List<ListNode> nodes){
        ListNode temp = new ListNode(0);
        ListNode result = temp;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b)->a.value-b.value);
        for(ListNode node : nodes){
            minHeap.add(node);
        }
        while(!minHeap.isEmpty()){
            ListNode current = minHeap.poll();
            temp.next = new ListNode(current.value);
            if(current.next!=null){
                minHeap.add(current.next);
            }
            temp=temp.next;
        }
        return result.next;
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
        StringBuilder SB = new StringBuilder();
        ListNode current = this;
        while(current!=null){
            SB.append(current.value).append("->");
            current=current.next;
        }
        return SB.toString();
    }
}
