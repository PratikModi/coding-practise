package com.java.coding.interviews.practise.common;

import java.util.Arrays;

/**
 * Created by Pratik1 on 11-04-2020.
 */
public class TrappingRainWaterProblem {

    public static void main(String[] args) {
        System.out.println(findTotalTrappedWater(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }

    public static int findTotalTrappedWater(int[] A){
        int result=0;
        if(A==null || A.length==0){
            return result;
        }
        int[] leftMax = new int[A.length+1];
        leftMax[0]=0;
        for (int i=0; i<A.length; i++) {
            leftMax[i+1] = Math.max(leftMax[i],A[i]);
        }
        System.out.println(Arrays.toString(leftMax));
        int rightMax=0;
        for(int i=A.length-1;i>=0;i--){
            rightMax=Math.max(rightMax,A[i]);
            result+=Math.min(leftMax[i],rightMax)>A[i]?Math.min(leftMax[i],rightMax)-A[i]:0;
            System.out.println(leftMax[i]+"--"+rightMax+"--"+result+" ");
        }
        return result;
    }
}
