package com.java.coding.interviews.practise.hotstar;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an array arr which consists of only zeros and ones, divide the array into three non-empty parts such that all of these parts represent the same binary value.
 *
 * If it is possible, return any [i, j] with i + 1 < j, such that:
 *
 * arr[0], arr[1], ..., arr[i] is the first part,
 * arr[i + 1], arr[i + 2], ..., arr[j - 1] is the second part, and
 * arr[j], arr[j + 1], ..., arr[arr.length - 1] is the third part.
 * All three parts have equal binary values.
 * If it is not possible, return [-1, -1].
 *
 * Note that the entire part is used when considering what binary value it represents. For example, [1,1,0] represents 6 in decimal, not 3.
 * Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.
 * Example 1:
 *
 * Input: arr = [1,0,1,0,1]
 * Output: [0,3]
 * Example 2:
 *
 * Input: arr = [1,1,0,1,1]
 * Output: [-1,-1]
 * Example 3:
 *
 * Input: arr = [1,1,0,0,1]
 * Output: [0,2]
 */
public class ThreeEqualPartProblem {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(threeEqualParts(new int[]{1,0,1,0,1})));
        System.out.println(Arrays.toString(threeEqualParts(new int[]{1,1,0,0,1})));
    }

    public static int[] threeEqualParts(int[] arr) {
        int[] result = {-1,-1};
        int sum = Arrays.stream(arr).sum();
        if(sum%3!=0)
            return result;
        int K = sum/3;
        if(K==0)
            return new int[]{0,arr.length-1};
        List<Integer> intervals = new ArrayList<>();
        int S=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=0) {
                S += arr[i];
                if ((S == 1) || (S == K + 1) || (S == 2 * K + 1)) {
                    intervals.add(i);
                }
                if ((S == K) || (S == 2 * K) || (S == 3 * K)) {
                    intervals.add(i);
                }
            }
        }
        int I1 = intervals.get(0),J1= intervals.get(1);
        int I2 = intervals.get(2),J2= intervals.get(3);
        int I3 = intervals.get(4),J3= intervals.get(5);

        int[] A = Arrays.copyOfRange(arr,I1,J1+1);
        int[] B = Arrays.copyOfRange(arr,I2,J2+1);
        int[] C = Arrays.copyOfRange(arr,I3,J3+1);

        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(B));
        System.out.println(Arrays.toString(C));

        if(!(Arrays.equals(A,B) && Arrays.equals(B,C))) {
            System.out.println("NOT EQUAL");
            return result;
        }
        //Zero in-between
        int X = I2-J1-1;
        int Y = I3-J2-1;
        int Z = arr.length-J3-1;

        if(X<Z || Y<Z) {
            System.out.println("NOT EQUAL ZERO");
            return result;
        }
        J1+=Z;
        J2+=Z;
        result[0]=J1;
        result[1]=J2+1;
        return result;
    }

}
