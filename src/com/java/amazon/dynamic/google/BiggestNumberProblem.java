package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 02-06-2020.
 */

/**
 * Given an Array of integers, find out the biggest number that can be formed out of array.
 * [3,0,9,5,6] ==> 96530
 * [3,39] ==> 393
 */
public class BiggestNumberProblem {

    public static void main(String[] args) {
        String[] numbers = new String[]{"3","0","9","5","6"};
        System.out.println(findBiggestNumberFormed(numbers));
        numbers = new String[]{"3","39"};
        System.out.println(findBiggestNumberFormed(numbers));
    }

    public static int findBiggestNumberFormed(String[] N){
        String result="";
        if(N==null || N.length==0)
            return -1;
        for(int i=0;i<N.length;i++){
            for(int j=i+1;j<N.length;j++){
                String number1 = N[i].concat(N[j]);
                String number2 = N[j].concat(N[i]);
                if(Integer.parseInt(number2)> Integer.parseInt(number1)){
                    String temp = N[i];
                    N[i] = N[j];
                    N[j] = temp;
                }
            }
        }
        for(int i=0;i<N.length;i++){
            result+=N[i];
        }
        return Integer.parseInt(result);
    }

}
