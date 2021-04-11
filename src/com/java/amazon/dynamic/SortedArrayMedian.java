package com.java.amazon.dynamic;

import java.util.Arrays;

/**
 * Created by Pratik1 on 21-02-2020.
 */
public class SortedArrayMedian {

    public static void sortArray(int[] A, int[] B){

        int m = A.length;
        int n = B.length;

        for(int i=0;i<m;i++){
            if(A[i] > B[0]){
                int temp = A[i];
                A[i] = B[0];
                B[0] = temp;
            }
            int first = B[0];
            int k;
            for(k=1;k<n&&B[k]<first;k++){
                B[k-1] = B[k];
            }
            B[k-1] = first;
        }
    }

    public static double median(int[] A , int[] B){
        int total = A.length+B.length;
        if(total%2!=0){
            if((total/2)>A.length){
                return (double)B[total/2];
            }else{
                return (double)A[total/2];
            }
        }
        else{
            if(A.length==B.length){
                return (double)(A[A.length-1]+B[0])/2;
            }else if(A.length>B.length){
                return (double)(A[total/2]+A[(total/2)-1])/2;
            }else{
                return (double)(B[0]+B[1])/2;
            }
        }
    }


    public static void main(String[] args) {
        int[] A = {1,4,6,8};
        int[] B = {2,3,5,7};
        sortArray(A,B);
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(B));
        System.out.println(median(A,B));
    }

}
