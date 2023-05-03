package com.java.coding.interviews.practise.jpmc;

public class ReverseLinkedListProblem {

    public static void main(String[] args) {

    }

    public static Node reverseSingly(Node head){
        Node next=null;
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
