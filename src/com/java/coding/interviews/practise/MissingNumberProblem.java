package com.java.coding.interviews.practise;

public class MissingNumberProblem {

    public static void main(String[] args) {
        int[] A = new int[] {0,1,2,3,4,5,6,8,9};
        System.out.println(findMissingNumber(A,A.length));
    }

    public static int findMissingNumber(int[] A, int N){
        if(A==null || A.length<2)
            return -1;
        int L=0;
        int R=N;
        while(L<=R){
            int mid=(L+R)/2;
            if(A[mid]!=mid && A[mid-1]!=mid) {
                System.out.println("in if");
                return mid;
            }
            else if(A[mid]!=mid){
                System.out.println("in else if");
                R=mid-1;
            }else{
                System.out.println("else");
                L=mid+1;
            }
        }
        return -1;
    }
}
