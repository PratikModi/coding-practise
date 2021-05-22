package com.java.amazon.dynamic.hotstar;

/**
 * Merge In Between Linked Lists
 * Medium
 *
 * Add to List
 *
 * Share
 * You are given two linked lists: list1 and list2 of sizes n and m respectively.
 *
 * Remove list1's nodes from the ath node to the bth node, and put list2 in their place.
 *
 * Build the result list and return its head.
 *
 * Example 1:
 *
 *
 * Input: list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
 * Output: [0,1,2,1000000,1000001,1000002,5]
 * Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place. The blue edges and nodes in the above figure indicate the result.
 */

public class MergeLinkedListInBetweenProblem {
    public static void main(String[] args) {
        Node head1 = new Node(0);
        Node second = new Node(1);
        Node third = new Node(2);
        Node fourth = new Node(3);
        Node fifth = new Node(4);
        Node sixth = new Node(5);
        Node seventh = new Node(6);

        head1.next=second;
        second.next=third;
        third.next=fourth;
        fourth.next=fifth;
        fifth.next=sixth;
        sixth.next=seventh;

        Node head2 = new Node(1000);
        head2.next = new Node(1001);
        head2.next.next = new Node(1002);
        head2.next.next.next = new Node(1003);
        head2.next.next.next.next = new Node(1004);

        Node result = mergeInBetween(head1,3,4,head2);
        System.out.println(result);
    }

    public static Node mergeInBetween(Node head1, int a, int b, Node head2){
        if(head1==null)
            return head2;
        if(head2==null)
            return head1;
        Node aPositions = head1;
        System.out.println(head1);
        int aIndex=1;
        while(aIndex<a){
            aPositions=aPositions.next;
            aIndex++;
        }
        System.out.println(aPositions.value);
        Node bPositions = aPositions;
        int bIndex=aIndex;
        while(bIndex<=b+1){
            bPositions=bPositions.next;
            bIndex++;
        }
        System.out.println(bPositions.value);
        aPositions.next=head2;
        Node current=head2;
        while(current.next!=null){
            current=current.next;
        }
        current.next=bPositions;
        return head1;
    }

}
class Node{
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next=null;
    }

    @Override
    public String toString() {
        Node node = this;
        StringBuilder SB = new StringBuilder();
        while(node!=null){
            SB.append(node.value).append("->");
            node=node.next;
        }
        SB.setLength(SB.length()-2);
        return SB.toString();
    }
}
