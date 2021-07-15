package com.java.coding.interviews.practise.common;

import java.util.Stack;

/**
 * Created by Pratik1 on 08-03-2020.
 */
public class HistogramRectangleProblem {

    public static int findMaxArea(int[] histogram){
        int max_area=0;
        if(histogram==null || histogram.length==0)
            return max_area;
        int i=0;
        int topElement=0;
        Stack<Integer> stack = new Stack<>();
        while(i<histogram.length) {
            if (stack.isEmpty() || histogram[i] >= histogram[stack.peek()]) {
                stack.push(i++);
            } else {
                topElement = stack.peek();
                stack.pop();
                max_area = Math.max(max_area, histogram[topElement] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        System.out.println(max_area);
        while(!stack.isEmpty()){
            topElement=stack.peek();
            stack.pop();
            max_area=Math.max(max_area,histogram[topElement]*(stack.isEmpty()?i-1:i-stack.peek()-1));
        }

        return max_area;
    }

    public static void main(String[] args) {
        System.out.println(findMaxArea(new int[]{1,2,3,4,1}));
        //System.out.println(findMaxArea(new int[]{0,4,4,4}));
        //System.out.println(findMaxArea(new int[]{ 6, 2, 5, 4, 5, 1, 6 }));
    }
}
