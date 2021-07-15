package com.java.coding.interviews.practise.amazon;

import java.util.Arrays;
import java.util.Stack;

/**
 * The Stock Span Problem
 * Difficulty Level : Medium
 * Last Updated : 10 May, 2021
 * The stock span problem is a financial problem where we have a series of n daily price quotes for a stock and we need to calculate span of stock’s price for all n days.
 * The span Si of the stock’s price on a given day i is defined as the maximum number of consecutive days just before the given day,
 * for which the price of the stock on the current day is less than or equal to its price on the given day.
 * For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, then the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
 */
//O(N) // O(N)
public class StockSpanProblem {

    public static void main(String[] args) {
        int[] prices = new int[]{100, 80, 60, 70, 60, 75, 85};
        System.out.println(Arrays.toString(stockSpan(prices)));
    }

    public static int[] stockSpan(int[] prices){
        if(prices==null || prices.length==0)
            return new int[]{};
        int N = prices.length;
        Stack<Integer> stack = new Stack<>();
        int[] span = new int[N];
        span[0]=1;
        stack.push(0);
        for(int i=1;i<prices.length;i++){
            while(!stack.isEmpty() && prices[stack.peek()]<=prices[i]) {
                stack.pop();
            }
            if(stack.isEmpty()){
                span[i] = i + 1;
            }else{
                span[i]=i-stack.peek();
            }
            stack.push(i);
        }
        return span;
    }
}
