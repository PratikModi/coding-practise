package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 31-05-2020.
 */

import java.util.Arrays;
import java.util.Stack;

/**
 * /*
 Given an N by M matrix consisting only of 1's and 0's,
 find the largest rectangle containing only 1's and return its area.
 For example, given the following matrix:
 [
 [1, 0, 0, 0],
 [1, 0, 1, 1],
 [1, 0, 1, 1],
 [0, 1, 0, 0]
 ]
 Return 4.
 */
public class MatrixRectangleProblem {
    public static void main(String[] args) {
        int[][] matrix = {{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}};
        System.out.println(findMaxRectangle(matrix));
        matrix = new int[][]{{1,0,1,1},{1,1,1,1},{1,1,1,1},{0,1,0,1}};
        System.out.println(findMaxRectangle(matrix));
    }

    public static int findMaxRectangle(int[][] matrix){
        int result=0;
        if(matrix==null || matrix.length==0)
            return 0;
        for(int i=0;i<matrix.length;i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        int[] histogram = new int[matrix[0].length];
        for(int i=0;i<matrix[0].length;i++){
            histogram[i]=matrix[0][i];
        }
        //System.out.println(Arrays.toString(histogram)+"=0=>"+maxHistogramArea(histogram));
        result = Math.max(result,maxHistogramArea(histogram));
        for(int i=1;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]==1){
                    histogram[j]++;
                }else{
                    histogram[j]=0;
                }
            }
            //System.out.println("==================");
            //System.out.println(Arrays.toString(histogram)+"==>"+maxHistogramArea(histogram));
            result = Math.max(result,maxHistogramArea(histogram));
        }
        return result;
    }

    public static int maxHistogramArea(int[] histogram){
        int max_area=0;
        int topElement =0;
        int N = histogram.length;
        Stack<Integer> stack = new Stack<>();
        int i=0;
        while(i<N) {
            if (stack.isEmpty() || histogram[i] >= histogram[stack.peek()]) {
                stack.push(i++);
            } else {
                topElement = stack.peek();
                stack.pop();
                max_area = Math.max(max_area, (stack.isEmpty() ? i : i - stack.peek() - 1) * histogram[topElement]);
            }
        }
        while (!stack.isEmpty()){
            topElement = stack.peek();
            stack.pop();
            max_area =Math.max(max_area,(stack.isEmpty()?i:i-stack.peek()-1)*histogram[topElement]);
        }
        return max_area;
    }

}
