package com.java.amazon.dynamic.microsoft;

/**
 * Created by Pratik1 on 24-05-2020.
 */

/**
 * For example, the following linked list:

 1 -> 2 -> 3 -> 4 -> 5
 is the number 54321.

 Given two linked lists in this format, return their sum in the same linked list format.

 For example, given

 9 -> 9
 5 -> 2
 return 124 (99 + 25) as:

 4 -> 2 -> 1
 */
public class LinkedListSumProblem {

    public static void main(String[] args) {
        Node F = new Node(9);
        F.next = new Node(9);
        Node S = new Node(5);
        S.next = new Node(2);
        Node R = sumTheLinkedList(F,S);
        System.out.println(R);
    }

    public static Node sumTheLinkedList(Node F, Node S){
        Node result = null;
        Node prev = null;
        Node temp = null;
        int carry=0,sum;
        while(F!=null || S!=null){
            sum=carry+(F!=null?F.data:0)+(S!=null?S.data:0);
            System.out.println(sum);
            carry = sum>=10?1:0;
            sum = sum%10;
            System.out.println(sum);
            temp = new Node(sum);
            if(result==null){
                result=temp;
            }else{
                prev.next=temp;
            }
            prev = temp;
            if(F!=null)
                F= F.next;
            if(S!=null)
                S=S.next;
        }
        if(carry>0){
            temp.next = new Node(carry);
        }
        System.out.println(temp);
        return result;
    }

}

class Node{
    int data;
    Node next;
    Node(int data){
        this.data = data;
        this.next = null;
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        Node node = this;
        while(node!=null){
            builder.append(node.data).append("->");
            node = node.next;
        }
        return builder.toString().substring(0,builder.length()-2);
    }
}