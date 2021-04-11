package com.java.amazon.dynamic.google;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a stack of N elements, interleave the first half of the stack with the second half reversed using
 * only one other queue. This should be done in-place.
 *
 * Recall that you can only push or pop from a stack, and enqueue or dequeue from a queue.
 *
 * For example, if the stack is [1, 2, 3, 4, 5], it should become [1, 5, 2, 4, 3].
 * If the stack is [1, 2, 3, 4], it should become [1, 4, 2, 3].
 */
public class StackInterleaveProblem {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        Stack<Integer> result =interleaveStack(stack);
        while(!result.isEmpty()){
            System.out.print(result.pop()+"  ");
        }
    }

    public static Stack<Integer> interleaveStack(Stack<Integer> stack){
        if(stack==null || stack.size()<3)
            return stack;
        Stack<Integer> interleavedStack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        int half = stack.size()/2;
        for(int i=0;i<half;i++){
            queue.add(stack.pop());
        }
        int count=queue.size();
        while(count>1){
            queue.add(queue.remove());
            count--;
        }
        if(stack.size()%2!=0)
            interleavedStack.push(stack.pop());
        while(!stack.isEmpty() && !queue.isEmpty()){
            interleavedStack.push(stack.pop());
            interleavedStack.push(queue.remove());

        }
        return interleavedStack;
    }
}
