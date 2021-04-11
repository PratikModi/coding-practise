package com.java.amazon.dynamic.amazon;

/**
 * Created by Pratik1 on 21-06-2020.
 */

/**
 * Given a string, determine whether any permutation of it is a palindrome.
 * For example, carrace should return true, since it can be rearranged to form racecar,
 * which is a palindrome. daily should return false,
 * since there's no rearrangement that can form a palindrome.
 */
public class PalindromePermutationsProblem {

    public static void main(String[] args) {
        System.out.println(isAnyPermutationPalindrome("carrace"));
        System.out.println(isAnyPermutationPalindrome("amazon"));
        System.out.println(3 & 1);
    }

    public static boolean isAnyPermutationPalindrome(String S){
        if(S==null || S.length()==0)
            return false;
        int[] charCount=new int[26];
        for(int i=0;i<S.length();i++){
            charCount[S.charAt(i)-'a']++;
        }
        int odd=0;
        for(int i=0;i<26;i++){
            if((charCount[i] & 1)==1)
                odd++;
        }
        if(odd>1)
            return false;
        return true;
    }

}
