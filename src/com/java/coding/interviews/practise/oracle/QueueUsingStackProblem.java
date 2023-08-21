package com.java.coding.interviews.practise.oracle;

import java.util.Stack;

/**
 * Queue using Stacks
 */

//Time Complexity: enQueue: O(N)
//Time Complexity: deQueue: O(1)
//Space Complexity: O(N)

public class QueueUsingStackProblem {

    private static Stack<Integer> stack1;
    private static Stack<Integer> stack2;

    static {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public static void main(String[] args) {
        enQueue(1);
        enQueue(2);
        System.out.println(deQueue());
        enQueue(3);
        enQueue(4);
        System.out.println(deQueue());

    }

    private static void enQueue(int value){
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        stack1.push(value);
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
    }

    private static int deQueue(){
        int x = stack1.peek();
        stack1.pop();
        return x;
    }

}
