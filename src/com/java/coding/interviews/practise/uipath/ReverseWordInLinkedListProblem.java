package com.java.coding.interviews.practise.uipath;

/**
 * Reverse all the word in a String represented as a Linked List
 * Difficulty Level : Hard
 * Last Updated : 11 Feb, 2020
 * Given a Linked List which represents a sentence S such that each node represents a letter, the task is to reverse the sentence without reversing individual words.
 * For example, for a given sentence “I love Geeks for Geeks”, the Linked List representation is given as:
 * I-> ->l->o->v->e-> ->G->e->e->k->s-> ->f->o->r-> ->G->e->e->k->s
 *
 * Examples:
 *
 * Input: I love Geeks for Geeks
 * Output: Geeks for Geeks love I
 *
 * Input: practice makes a man perfect
 * Output: perfect man a makes practice
 */

public class ReverseWordInLinkedListProblem {

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode('P');
        head.next = new LinkedListNode('R');
        head.next.next = new LinkedListNode('A');
        head.next.next.next = new LinkedListNode('T');
        head.next.next.next.next = new LinkedListNode('I');
        head.next.next.next.next.next = new LinkedListNode('K');
        head.next.next.next.next.next.next = new LinkedListNode(' ');
        head.next.next.next.next.next.next.next = new LinkedListNode('M');
        head.next.next.next.next.next.next.next.next = new LinkedListNode('O');
        head.next.next.next.next.next.next.next.next.next = new LinkedListNode('D');
        head.next.next.next.next.next.next.next.next.next.next = new LinkedListNode('I');
        System.out.println(head);
        //System.out.println(reverseWordLinkedList2(head));
        LinkedListNode result = reverseWordLinkedList(head);
        System.out.println(result);

    }

    public static LinkedListNode reverseWordLinkedList(LinkedListNode head){
        if(head==null)
            return null;
        LinkedListNode start;
        LinkedListNode end=null;
        LinkedListNode sentenceStart;

        start = head;

        while(head!=null && head.val!=' '){
            end=head;
            head=head.next;
        }

        if(head==null){
            return start;
        }

        do{
            LinkedListNode temp = head.next;
            head.next = start;
            start = head;
            head = temp;

            LinkedListNode prev = null;
            sentenceStart = head;
            while(head!=null && head.val!=' '){
                prev=head;
                head=head.next;
            }
            prev.next = start;
            start = sentenceStart;
            if(head==null)
                break;
        }while(head!=null);
        head= sentenceStart;
        end.next=null;
        return head;
    }

    public static LinkedListNode reverseWordLinkedList2(LinkedListNode head){
        if(head==null)
            return null;
        LinkedListNode current = head;
        LinkedListNode next = null;
        LinkedListNode previous = null;

        while(current!=null){
            next = current.next;
            current.next=previous;
            previous=current;
            current=next;
        }
        return reverseWordsInList(previous);

    }

    private static LinkedListNode reverseWordsInList(LinkedListNode head){
        if(head==null)
            return null;
        //System.out.println(head.val);
        LinkedListNode current = head;
        LinkedListNode next = null;
        LinkedListNode previous = null;
        int counter = 0;
        LinkedListNode temp = head;
        while(temp!=null && temp.val!=' '){
            counter++;
            temp=temp.next;
        }
        if(temp!=null) {
            counter++;
        }
        while(counter-->0 && current!=null){
            next = current;
            current.next=previous;
            previous=current;
            current=next;
        }
        //System.out.println(previous);
        //System.out.println(current);
        if(current!=null) {
            head.next = reverseWordsInList(current);
        }
        return previous;
    }

}
class LinkedListNode{

    char val;
    LinkedListNode next;

    public LinkedListNode(char val) {
        this.val = val;
        this.next = null;
    }

    @Override
    public String toString() {
        LinkedListNode current = this;
        StringBuilder SB = new StringBuilder();
        while(current!=null){
            SB.append(current.val).append("->");
            current=current.next;
        }
        SB.setLength(SB.length()-2);
        return SB.toString();
    }
}


