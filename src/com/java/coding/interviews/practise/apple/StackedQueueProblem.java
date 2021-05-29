package com.java.coding.interviews.practise.apple;

import java.util.Stack;

/**
 * Implement a queue using two stacks. Recall that a queue is a FIFO (first-in, first-out) data structure with the following methods: enqueue,
 * which inserts an element into the queue, and dequeue, which removes it.
 */
public class StackedQueueProblem {

    public static Stack<Integer> S1 = new Stack<Integer>();
    public static Stack<Integer> S2 = new Stack<Integer>();

    public static void main(String[] args) {
       /* enqueue(1);
        enqueue(2);
        enqueue(3);
        enqueue(4);
        System.out.println(dequeue());*/
        enqueue2(1);
        enqueue2(2);
        enqueue2(3);
        enqueue2(4);
        System.out.println(dequeue2());
    }

    public static void enqueue(int x){
        while(!S1.isEmpty()){
            S2.push(S1.pop());
        }
        S1.push(x);
        while(!S2.isEmpty()){
            S1.push(S2.pop());
        }
    }

    public static int dequeue(){
        if(S1.isEmpty())
            throw new RuntimeException("Stack is Empty");
        else
            return S1.pop();
    }


    public static void enqueue2(int x){
        S1.push(x);
    }

    public static int dequeue2(){
        int result=0;
        if(S1.isEmpty())
            throw new RuntimeException("Stack is Empty");
        else if(S1.size()==1) {
            //System.out.println(S1);
            return S1.pop();
        }
        else{
            //System.out.println(S1);
            int x = S1.pop();
            result = dequeue2();
            S1.push(x);
        }
        return result;
    }
}
