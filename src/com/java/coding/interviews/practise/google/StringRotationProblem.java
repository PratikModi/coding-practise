package com.java.coding.interviews.practise.google;

/**
 * Created by Pratik1 on 19-05-2020.
 */

/**
 * Given two strings A and B, return whether or not A can be shifted some number of times to get B.
 * For example, if A is abcde and B is cdeab, return true. If A is abc and B is acb, return false.
 */
public class StringRotationProblem {

    public static void main(String[] args) {
        boolean result = isStringRotated("abcde","cdeab");
        System.out.println(result);
        result = isStringRotated("abc","acb");
        System.out.println(result);
    }

    public static boolean isStringRotated(String A, String B){
        if(A==null || B==null || A.length()!=B.length())
            return false;
        if(A.length()==0)
            return true;
        char start = A.charAt(0);
        for(int i=0;i<B.length();i++){
            int j=i;
            while(j<B.length() && B.charAt(j)!=start) {
                j++;
            }
            if(j>=B.length())
                return false;
            for(int n=0;n<A.length();n++){
                if(A.charAt(n)!=B.charAt((n+j)%B.length()))
                    break;
                if(n==B.length()-1)
                    return true;
            }
            i=j;
        }
        return false;
    }

}
