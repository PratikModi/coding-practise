package com.java.coding.interviews.practise.adobe;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
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
 */
public class LinkedListAdd2NumbersProblem {

    public static void main(String[] args) {
        LinkedListNode L1 = new LinkedListNode(9);
        L1.next= new LinkedListNode(9);
        L1.next.next= new LinkedListNode(9);
        L1.next.next.next= new LinkedListNode(9);
        L1.next.next.next.next= new LinkedListNode(9);
        L1.next.next.next.next.next= new LinkedListNode(9);
        L1.next.next.next.next.next.next= new LinkedListNode(9);
        LinkedListNode L2 = new LinkedListNode(9);
        L2.next= new LinkedListNode(9);
        L2.next.next= new LinkedListNode(9);
        L2.next.next.next= new LinkedListNode(9);
        System.out.println(addTwoNumbers(L1,L2));
    }

    public static LinkedListNode addTwoNumbers(LinkedListNode l1, LinkedListNode l2) {
        LinkedListNode result=null;
        LinkedListNode current =null;
        int carry=0;
        while(l1!=null || l2!=null || carry>0){
            int sum=carry+(l1==null?0:l1.value)+(l2==null?0: l2.value);
            LinkedListNode temp = new LinkedListNode(sum%10);
            carry = sum/10;
            if(result==null){
                result=temp;
            }else{
                current.next=temp;
            }
            current=temp;
            if(l1!=null)
                l1=l1.next;
            if(l2!=null)
                l2=l2.next;
        }
        return result;
    }


}

class LinkedListNode{
    int value;
    LinkedListNode next;

    public LinkedListNode(int value) {
        this.value = value;
        this.next = null;
    }

    @Override
    public String toString() {
        LinkedListNode current=this;
        StringBuilder SB = new StringBuilder();
        while(current!=null){
            SB.append(current.value).append("->");
            current=current.next;
        }
        SB.setLength(SB.length()-2);
        return SB.toString();
    }
}