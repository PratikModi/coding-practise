package com.java.coding.interviews.practise.razorpay;

import com.java.coding.interviews.practise.adobe.MSTKrushkalAlgorithm;

import java.sql.Statement;
import java.util.Stack;

/**
 * Check whether there exists a triplet (i, j, k) such that arr[i] < arr[k] < arr[j] for i < j < k
 * Last Updated : 10 Jun, 2021
 * Given an array arr[], the task is to check that if there exist a triplet (i, j, k) such that arr[i]<arr[k]<arr[j] and i<j<k then print Yes else print No.
 *
 * Examples:
 *
 * Input: arr[] = {1, 2, 3, 4, 5}
 * Output: No
 * Explanation:
 * There is no such sub-sequence such that arr[i] < arr[k] < arr[j]
 *
 * Input: arr[] = {3, 1, 5, 0, 4}
 * Output: Yes
 * Explanation:
 * There exist a triplet (3, 5, 4) which is arr[i] < arr[k] < arr[j]
 */
public class TripletProblem {

    public static void main(String[] args) {
        int[] A = {3, 1, 5, 0, 4};
        System.out.println(isTripletExist(A));
    }

    public static boolean isTripletExist(int[] A){
        if(A==null || A.length<3)
            return false;
        Stack<Integer> S = new Stack<>();
        int N = A.length;
        int E1=0;
        int E3=0;
        for(int i=0;i<N;i++){
            E1 = A[i];
            System.out.println("E1==>"+E1);
            System.out.println(S);
            while(!S.isEmpty() && S.peek() < A[i]){
                E3=S.peek();
                S.pop();
                System.out.println("E3==>"+E3);
            }
            S.push(A[i]);
            if(E1<E3){
                System.out.println(E1+"---"+E3);
                return true;
            }
        }
        return false;
    }


}
