package com.java.coding.interviews.practise.google;

/**
 * 142. Linked List Cycle II
 *
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed).
 * It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 *
 * Do not modify the linked list.
 *
 * Example 1:
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 */
public class LinkedListCycleProblem {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next = head.next;
        System.out.println(detectCycle(head));
    }

    /**
     *
     The Math Behind Phase 2
     Let's define the distances:

     X: Distance from the head to the start of the cycle.
     Y: Distance from the start of the cycle to the meeting point.
     Z: Distance from the meeting point back to the start of the cycle.
     L=Y+Z: The length of the cycle.

     When slow and fast meet:

     Distance covered by slow is D
     slow=X+Y.

     Distance covered by fast is D
     fast=X+Y+kL, where k is the number of full loops fast completed.

     Since fast moves twice as fast as slow, D
     fast =2×D slow
     2(X+Y)=X+Y+kL
     X+Y=kL
     X=kL−Y
     X=kL−Y−Z+Z
     X=k(Y+Z)−Y−Z+Z
     X=(k−1)L+Z
     The final equation X=(k−1)L+Z means that the distance from the head to the cycle start (X)
     is equal to the distance from the meeting point back to the cycle start (Z), plus some number of full cycles ((k−1)L).

     Therefore, if you start one pointer from the head (distance X) and another from the meeting point (distance Z),
     and move them one step at a time, they will meet exactly at the start of the cycle.
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                ListNode start = head;
                while(slow!=start){
                    slow=slow.next;
                    start=start.next;
                }
                return start;
            }
        }
        return null;
    }

}
