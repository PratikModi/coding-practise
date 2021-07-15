package com.java.coding.interviews.practise.amazon;

/**
 * Given a singly linked list, determine if its a palindrome. Return 1 or 0 denoting if its a palindrome or not, respectively.
 *
 * Notes:
 *
 * Expected solution is linear in time and constant in space.
 * For example,
 *
 * List 1-->2-->1 is a palindrome.
 * List 1-->2-->3 is not a palindrome.
 */
public class PalindromeListProblem {

    public static void main(String[] args) {
        ListNode N1 = new ListNode(1);
        ListNode N2 = new ListNode(2);
        ListNode N3 = new ListNode(2);
        ListNode N4 = new ListNode(1);
        N1.next=N2;
        N2.next=N3;
        N3.next=N4;
        System.out.println(isPalindrome(N1));
    }

    public static boolean isPalindrome(ListNode head){
        ListNode slow=head;
        ListNode fast=head;
        ListNode previous_to_slow=null;
        boolean result=false;
        ListNode second_half;
        ListNode midOne=null;
        if(head!=null && head.next!=null){
            while(fast!=null && fast.next!=null){
                fast=fast.next.next;
                previous_to_slow=slow;
                slow=slow.next;
            }
            System.out.println(slow.value);
            if(fast!=null){
                System.out.println("HERE");
                midOne=slow;
                slow=slow.next;
            }
            second_half=slow;
            second_half=reverse(second_half);
            result=compare(head,second_half);
            second_half=reverse(second_half);
            if(midOne!=null){
                previous_to_slow.next=midOne;
                midOne.next=second_half;
            }else{
                previous_to_slow.next=second_half;
            }
        }
        return result;
    }

    private static ListNode reverse(ListNode head){
        ListNode previous=null;
        ListNode current=head;
        ListNode next;
        while(current!=null){
            next=current.next;
            current.next=previous;
            previous=current;
            current=next;
        }
        return previous;
    }

    private static boolean compare(ListNode N1, ListNode N2){
        while(N1!=null && N2!=null){
            if(N1.value==N2.value){
                N1=N1.next;
                N2=N2.next;
            }else
                return false;
            if(N1==null || N2==null)
                return true;
        }
        return false;
    }

}

