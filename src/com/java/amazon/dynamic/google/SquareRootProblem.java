package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 24-05-2020.
 */
public class SquareRootProblem {

    public static void main(String[] args) {
        System.out.println((findSquareRoot(2147483647)));
    }

    public static int findSquareRoot(int N){
        if(N==0 || N==1)
            return N;
        long start =0, end=N;
        int result=0;
        while(start<=end){
            long mid = start+(end-start)/2;
            if(mid*mid==N)
                return (int)mid;
            if(mid*mid<N){
                start=mid+1;
            }else{
                end=mid-1;
            }
        }
        return (int)end;
    }

}
