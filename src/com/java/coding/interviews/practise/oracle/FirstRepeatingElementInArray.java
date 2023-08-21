package com.java.coding.interviews.practise.oracle;

import java.util.HashSet;
import java.util.Set;

/**
 * Find the first repeating element in an array of integers
 * Read
 * Discuss(190+)
 * Courses
 * Practice
 * Video
 * Given an array of integers arr[], The task is to find the index of first repeating element in it i.e. the element that occurs more than once and whose index of the first occurrence is the smallest.
 *
 * Examples:
 *
 * Input: arr[] = {10, 5, 3, 4, 3, 5, 6}
 * Output: 5
 * Explanation: 5 is the first element that repeats
 *
 * Input: arr[] = {6, 10, 5, 4, 9, 120, 4, 6, 10}
 * Output: 6
 * Explanation: 6 is the first element that repeats
 */

public class FirstRepeatingElementInArray {

    public static void main(String[] args) {
        int[] A1 = {10, 5, 3, 4, 3, 5, 6};
        System.out.println(findFirstRepeating(A1));
        int[] A2 = {6, 10, 5, 4, 9, 120, 4, 6, 10};
        System.out.println(findFirstRepeating(A2));
    }

    public static int findFirstRepeating(int[] A){
        int min=-1;
        int N = A.length;
        Set<Integer> elements = new HashSet<>();
        for(int i=N-1;i>=0;i--){
            if(elements.contains(A[i])){
                min=i;
            }else{
                elements.add(A[i]);
            }
        }
        if(min!=-1)
            return A[min];
        else
            return -1;
    }



}
