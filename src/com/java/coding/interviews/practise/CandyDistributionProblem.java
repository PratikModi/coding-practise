package com.java.coding.interviews.practise;

import java.util.Arrays;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Example 1:
 *
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 *              The third child gets 1 candy because it satisfies the above two conditions.
 */

public class CandyDistributionProblem {

    public static void main(String[] args) {
        int[] R = {1,0,2};
        System.out.println(findMinimumCandies(R));
        R=new int[]{1,2,2};
        System.out.println(findMinimumCandies(R));
        R=new int[]{1,2,87,87,87,2,1};
        System.out.println(findMinimumCandies(R));
    }

    public static int findMinimumCandies(int[] R){
        int N = R.length;
        int[] LT = new int[N];
        int[] RT = new int[N];
        int requireCandy=0;
        Arrays.fill(LT,1);
        Arrays.fill(RT,1);
        for(int i=1;i<N;i++){
            if(R[i]>R[i-1]) {
                LT[i] = LT[i-1]+1;
            }
        }
        for(int i=N-2;i>=0;i--){
            if(R[i]>R[i+1])
                RT[i]=RT[i+1]+1;
        }
        System.out.println(Arrays.toString(LT));
        System.out.println(Arrays.toString(RT));
        for(int i=0;i<N;i++){
            requireCandy+=Math.max(LT[i],RT[i]);
        }
        return requireCandy;
    }

}
