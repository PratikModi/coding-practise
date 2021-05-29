package com.java.coding.interviews.practise.google;

/**
 * Swap the linked list pair
 * 1->2->3->4 ===> 2->1->4->3
 */
public class SwapLinkedListPair {
    public static void main(String[] args) {
        LinkedListNode first = new LinkedListNode(1);
        LinkedListNode second = new LinkedListNode(2);
        LinkedListNode third = new LinkedListNode(3);
        LinkedListNode fourth = new LinkedListNode(4);
        first.next=second;
        second.next=third;
        third.next=fourth;
        System.out.println(swapLinkedPair(first));
    }

    public static LinkedListNode swapLinkedPair(LinkedListNode head){
        if(head==null)
            return head;
        LinkedListNode root = new LinkedListNode(0);
        root.next=head;
        LinkedListNode current = root;
        while(current.next!=null && current.next.next!=null){
            LinkedListNode F = current.next;
            LinkedListNode S = current.next.next;
            F.next = S.next;
            current.next = S;
            S.next = F;
            current = current.next.next;
        }
        return root.next;

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
//            randomBuilder.append(current.random.value+"->");
            current=current.next;
        }
        return "Next Pointers==>"+builder.toString().substring(0,builder.length()-2);//+"\nRandom Pointers==>"+randomBuilder.toString().substring(0,randomBuilder.length()-2);
    }
}
