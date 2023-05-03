package com.java.coding.interviews.practise.jpmc;

public class ReverseLinkedListProblem {

    public static void main(String[] args) {
        Node singly = new Node(1);
        singly.next = new Node(2);
        singly.next.next = new Node(3);
        singly.next.next.next = new Node(4);
        System.out.println(reverseSingly(singly));

        Node doubly = new Node(1);
        doubly.next = new Node(2);
        doubly.previous=null;
        doubly.next.next = new Node(3);
        doubly.next.previous = doubly.next;
        doubly.next.next.next = new Node(4);
        doubly.next.next.previous = doubly.next.next;
        System.out.println(doubly);
        reverseDoubly(doubly);
        System.out.println(doubly);
    }

    public static Node reverseSingly(Node head){
        Node next;
        Node current=head;
        Node previous=null;
        while(current!=null){
            next = current.next;
            current.next=previous;
            previous=current;
            current=next;
        }
        return previous;
    }

    public static void reverseDoubly(Node head){
        Node temp=null;
        Node current=head;
        while(current!=null){
            temp = current.previous;
            current.previous = current.next;
            current.next=temp;
            current=current.previous;
        }
        System.out.println(temp);
        if(temp!=null){
            head = temp.previous;
        }
    }

}

class Node{
    Node next;
    Node previous;
     int value;

    public Node(int value) {
        this.value = value;
        this.next = null;
        this.previous = null;
    }

    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder();
        Node current = this;
        while(current!=null){
            SB.append(current.value).append("->");
            current=current.next;
        }
        return SB.toString();
    }
}
