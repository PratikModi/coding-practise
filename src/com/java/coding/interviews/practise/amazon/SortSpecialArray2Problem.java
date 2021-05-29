package com.java.coding.interviews.practise.amazon;

import java.util.Arrays;

/*
Given a pivot x, and a list lst, partition the list into three parts.

The first part contains all elements in lst that are less than x
The second part contains all elements in lst that are equal to x
The third part contains all elements in lst that are larger than x
Ordering within a part can be arbitrary.

For example, given x = 10 and lst = [9, 12, 3, 5, 14, 10, 10], one partition may be [9, 3, 5, 10, 10, 12, 14].
 */
public class SortSpecialArray2Problem {


    public static void main(String[] args) {
        int[] A = {9, 12, 3, 5, 14, 10, 10};
        int V = 10;
        System.out.println(Arrays.toString(A));
        sortArray(A,V);
        System.out.println(Arrays.toString(A));
    }

    public static void sortArray(int[] A , int V){
        if(A==null || A.length==0)
            return;
        int L = 0,M=0,H=A.length-1;
        while(M<=H){
            if(A[M]<V){
                int temp = A[M];
                A[M]=A[L];
                A[L]=temp;
                L++;
                M++;
            }else if(A[M]==V){
                M++;
            }else{
                int temp=A[H];
                A[H]=A[M];
                A[M]=temp;
                //M++;
                H--;
            }
        }
    }

}
