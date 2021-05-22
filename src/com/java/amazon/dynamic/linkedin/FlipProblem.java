package com.java.amazon.dynamic.linkedin;

import java.util.Arrays;

/**
 * You are given a string consisting of the letters x and y, such as xyxxxyxyy.
 * In addition, you have an operation called flip, which changes a single x to y or vice versa.
 *
 * Determine how many times you would need to apply this operation to ensure that all x's come before all y's.
 * In the preceding example, it suffices to flip the second and sixth characters, so you should return 2.
 */
public class FlipProblem {
    public static void main(String[] args) {
        String S = "xyxxxyxyy";
        System.out.println(findMinimumFlip(S));
    }

    public static int findMinimumFlip(String S){
        if(S==null || S.isEmpty())
            return 0;
        int N = S.length();
        int[] leftY = new int[N];
        int[] rightX = new int[N];
        leftY[0]=0;
        rightX[N-1]=0;

        for(int i=1;i<N;i++){
            leftY[i]=leftY[i-1]+(S.charAt(i-1)=='y'?1:0);
        }
        System.out.println(Arrays.toString(leftY));
        for(int i=N-2;i>=0;i--){
            rightX[i]=rightX[i+1]+(S.charAt(i+1)=='x'?1:0);
        }
        System.out.println(Arrays.toString(rightX));
        int result=N;
        for(int i=0;i<N;i++){
            result=Math.min(result,leftY[i]+rightX[i]);
        }
        return result;
    }

}
