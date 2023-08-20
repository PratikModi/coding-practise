package com.java.coding.interviews.practise.oracle;

/**
 * Remove duplicates from a sorted linked list
 * Write a function that takes a list sorted in non-decreasing order and deletes any duplicate nodes from the list.
 * The list should only be traversed once.
 * For example if the linked list is 11->11->11->21->43->43->60 then removeDuplicates() should convert the list to 11->21->43->60.
 */
public class RemoveDuplicatesFromSortedLinkedList {

    public static void main(String[] args) {
        Node N1 = new Node(11);
        Node N2 = new Node(11);
        Node N3 = new Node(11);
        Node N4 = new Node(21);
        Node N5 = new Node(43);
        Node N6 = new Node(43);
        Node N7 = new Node(60);
        N1.next = N2;
        N2.next = N3;
        N3.next = N4;
        N4.next = N5;
        N5.next = N6;
        N6.next = N7;
        Node result = removeDuplicateNodes(N1);
        System.out.println(result);
    }

    public static Node removeDuplicateNodes(Node head){
        Node current = head;
        while(current!=null){
            Node temp = current;
            while(temp!=null && temp.value== current.value){
                temp=temp.next;
            }
            current.next=temp;
            current=current.next;
        }
        return head;
    }


}

class Node{
    int value;
    Node next;

    Node(int v){
        this.value=v;
        this.next=null;
    }

    public String toString(){
        Node current = this;
        StringBuilder SB = new StringBuilder();
        while(current!=null){
            SB.append(current.value).append(current.next!=null?"->":"");
            current=current.next;
        }
        return SB.toString();
    }
}
