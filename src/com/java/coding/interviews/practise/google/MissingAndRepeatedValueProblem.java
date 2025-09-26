package com.java.coding.interviews.practise.google;

import java.util.Arrays;

/**
 * You are given a 0-indexed 2D integer matrix grid of size n * n with values in the range [1, n2].
 * Each integer appears exactly once except a which appears twice and b which is missing.
 * The task is to find the repeating and missing numbers a and b.
 *
 * Return a 0-indexed integer array ans of size 2 where ans[0] equals to a and ans[1] equals to b.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,3],[2,2]]
 * Output: [2,4]
 * Explanation: Number 2 is repeated and number 4 is missing so the answer is [2,4].
 * Example 2:
 *
 * Input: grid = [[9,1,7],[8,9,2],[3,4,6]]
 * Output: [9,5]
 * Explanation: Number 9 is repeated and number 5 is missing so the answer is [9,5].
 */
public class MissingAndRepeatedValueProblem {

    public static void main(String[] args) {
        int[][] grid = {{9,1,7},{8,9,2},{3,4,6}};
        System.out.println(Arrays.toString(findMissingRepeatedValue(grid)));
        System.out.println(Arrays.toString(findMissingRepeatedValueSpaceEfficient(grid)));
    }

    /**
     *  Time Complexity: O(n^2)
     *  Space Complexity: O(n^2)
     *
     */
    public static int[] findMissingRepeatedValue(int[][] grid){
        int n = grid.length;
        int size = n*n;
        int[] count = new int[size+1];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                count[grid[i][j]]++;
            }
        }
        int a=-1, b=-1;
        for(int i=0;i<=size;i++){
            if(count[i]==2){
                a=i;
            }
            if(count[i]==0){
                b=i;
            }

        }
        return new int[]{a,b};
    }

    //Time Complexity: O(n^2)
    //Space Complexity: O(1)
    public static int[] findMissingRepeatedValueSpaceEfficient(int[][] grid){
        int n = grid.length;
        long nSquared = n*n;
        long expectedSum = (long)nSquared * (nSquared+1)/2;
        long expectedSumOfSquared = (long) nSquared * (nSquared+1)*(2*nSquared+1)/6;

        long actualSum=0;
        long actualSquareSum=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                actualSum+=grid[i][j];
                actualSquareSum+= grid[i][j]*grid[i][j];
            }
        }
        //a-b
        long diffSum = actualSum -expectedSum;
        //a^2 - b^2
        long diffSquareSum = actualSquareSum - expectedSumOfSquared;

        //a+b = a2-b2/a-b
        long sumAB = diffSquareSum / diffSum;
        // a = (a+b)+(a-b)/2 = 2a/2 = a
        // b = (a+b)-(a-b)/2 = 2b/2 = b
        int a = (int)(sumAB+diffSum)/2;
        int b = (int)(sumAB-diffSum)/2;

        return new int[] {a,b};

    }

}
