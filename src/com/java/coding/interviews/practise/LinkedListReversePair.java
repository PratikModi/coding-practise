package com.java.coding.interviews.practise;

/**
 * Created by Pratik1 on 18-04-2020.
 */
public class LinkedListReversePair {

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(4);
        System.out.println(toString(swapPairs(head)));
    }

    public static LinkedListNode swapPairs(LinkedListNode head){
        LinkedListNode temp = new LinkedListNode(0);
        temp.next = head;
        LinkedListNode current = temp;
        while(current.next!=null && current.next.next!=null){
            LinkedListNode first = current.next;
            LinkedListNode second = current.next.next;
            first.next = second.next;
            current.next=second;
            second.next = first;
            current = current.next.next;
        }

        return temp.next;
    }

    public static String toString(LinkedListNode head){
        StringBuilder sb = new StringBuilder();
        while(head!=null){
            sb.append(head.value).append("->");
            head=head.next;
        }
        return sb.toString();
    }

}

class LinkedListNode{
    int value;
    LinkedListNode next;
    LinkedListNode random;
    LinkedListNode(int value){
        this.value=value;
        next=null;
        random=null;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        StringBuilder randomBuilder = new StringBuilder();
        LinkedListNode current = this;
        while(current!=null){
            builder.append(current.value+"->");
            randomBuilder.append(current.random.value+"->");
            current=current.next;
        }
        return "Next Pointers==>"+builder.toString().substring(0,builder.length()-2)+"\nRandom Pointers==>"+randomBuilder.toString().substring(0,randomBuilder.length()-2);
    }
}
