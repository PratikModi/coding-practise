package com.java.coding.interviews.practise.zuora;

/**
 * 2. Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Example 1:
 *
 *
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * Example 2:
 *
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * Example 3:
 *
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 * Complexity
 * Time complexity: O(n)
 * n is number of nodes in longer list l1 or l2.
 *
 * Space complexity:O(n) or O(1)
 * If we count new list we create, that is O(n). If we don't count, that is O(1)
 */
public class LinkedListAddProblem {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
            this.next =null;
        }
        public String toString(){
            StringBuilder sb = new StringBuilder();
            ListNode temp = this;
            while(temp!=null){
                sb.append(temp.val);
                sb.append("->");
                temp = temp.next;
            }
            sb.setLength(sb.length()-2);
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        ListNode l2 = new ListNode(1);
        //l2.next = new ListNode(6);
        //l2.next.next = new ListNode(4);
        ListNode result = addTwoNumbers(l1,l2);
        System.out.println(result);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            return addTwoNumbers(l1,l2,0);
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry){
        if(l1==null && l2==null && carry==0) return null;
        int val1 = l1==null?0:l1.val;
        int val2 = l2==null?0:l2.val;

        int sum = val1+val2+carry;
        if(sum>=10){
            carry=1;
            sum=sum-10;
        }else{
            carry=0;
        }

        ListNode result = new ListNode(sum);
        ListNode node1 = l1==null?null:l1.next;
        ListNode node2 = l2==null?null:l2.next;
        result.next = addTwoNumbers(node1,node2,carry);
        return result;

    }
}
