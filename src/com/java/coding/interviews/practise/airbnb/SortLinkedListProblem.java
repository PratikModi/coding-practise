package com.java.coding.interviews.practise.airbnb;

/**
 *
 */
public class SortLinkedListProblem {
    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(5);
        head.next = new LinkedListNode(4);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(2);
        head.next.next.next.next = new LinkedListNode(1);
        System.out.println(sort(head));
    }

    public static LinkedListNode sort(LinkedListNode head){
        if(head==null || head.next==null)
            return head;
        LinkedListNode temp = head;
        LinkedListNode slow = head;
        LinkedListNode fast = head;

        while(fast!=null && fast.next!=null){
            temp=slow;
            slow=slow.next;
            fast=fast.next.next;
        }
        temp.next=null;

        LinkedListNode left = sort(head);
        LinkedListNode right = sort(slow);

        return merge(left,right);
    }

    private static LinkedListNode merge(LinkedListNode left, LinkedListNode right){
        System.out.println(left);
        System.out.println(right);
        LinkedListNode sortedList = new LinkedListNode(0);
        LinkedListNode current = sortedList;

        while(left!=null && right!=null){
            if(left.value < right.value){
                current.next=left;
                left=left.next;
            }else{
                current.next=right;
                right=right.next;
            }
            current=current.next;
        }
        while(left!=null){
            current.next=left;
            left=left.next;
            current=current.next;
        }
        while (right!=null){
            current.next=right;
            right=right.next;
            current=current.next;
        }

        return sortedList.next;
    }
}

