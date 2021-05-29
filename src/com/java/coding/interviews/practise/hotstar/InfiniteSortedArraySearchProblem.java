package com.java.coding.interviews.practise.hotstar;

public class InfiniteSortedArraySearchProblem {

    public static void main(String[] args) {
        int[] A = {3, 5, 7, 9, 10, 90,
                100, 130, 140, 160, 170};
        System.out.println(findPosition(A,170));
    }

    public static int findPosition(int[] A, int K){
        int L = 0;
        int H = 1;
        int value = A[H];
        boolean reachedEnd=false;
        while(value<K && !reachedEnd){
            L=H;
            if(2*H < A.length-1)
                H=2*H;
            else{
                H = A.length-1;
                reachedEnd=true;
            }
            value=A[H];
        }
        return binarySearch(A,L,H,K);
    }

    private static int binarySearch(int[] A, int L, int H, int K){
        while(L<=H){
            int mid = L + (H-L)/2;
            if(A[mid]==K)
                return mid;
            else if(A[mid]<K)
                L=mid+1;
            else
                H=mid-1;
        }
        return -1;
    }

}
