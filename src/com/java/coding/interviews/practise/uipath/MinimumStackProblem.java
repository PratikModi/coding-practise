package com.java.coding.interviews.practise.uipath;

import java.util.Stack;

/**
 *Question: Design a Data Structure SpecialStack that supports all the stack operations like push(), pop(), isEmpty(), isFull() and
 * an additional operation getMin() which should return minimum element from the SpecialStack.
 * All these operations of SpecialStack must be O(1). To implement SpecialStack,
 * you should only use standard Stack data structure and no other data structure like arrays, list, .. etc.
 * Example:
 *
 *
 * Consider the following SpecialStack
 * 16  --> TOP
 * 15
 * 29
 * 19
 * 18
 *
 * When getMin() is called it should return 15,
 * which is the minimum element in the current stack.
 *
 * If we do pop two times on stack, the stack becomes
 * 29  --> TOP
 * 19
 * 18
 *
 * When getMin() is called, it should return 18
 * which is the minimum in the current stack.
 */
public class MinimumStackProblem {
    private static Stack<Integer> stack = new Stack<>();
    private static int minElement=Integer.MAX_VALUE;
    public static void main(String[] args) {
        push(5);
        System.out.println(getMin());
        push(3);
        System.out.println(getMin());
        push(6);
        System.out.println(getMin());
        System.out.println(pop());
        System.out.println(getMin());
        System.out.println(pop());
        System.out.println(getMin());
    }

    public static void push(int x){
        if(stack.empty()){
            minElement=x;
            stack.push(x);
        }else if(x<minElement){
            stack.push(2*x-minElement);
            minElement=x;
        }else{
            stack.push(x);
        }
    }

    public static int pop(){
        if(stack.isEmpty())
            throw new RuntimeException("No Element In Stack");
        else if(stack.peek()>=minElement){
            return stack.pop();
        }else{
            int pop = minElement;
            minElement = 2*minElement-stack.peek();
            return pop;
        }
    }

    public static int getMin(){
        if(!stack.isEmpty())
            return minElement;
        else{
            throw new RuntimeException();
        }
    }
}
