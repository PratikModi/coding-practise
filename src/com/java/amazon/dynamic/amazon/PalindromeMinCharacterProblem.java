package com.java.amazon.dynamic.amazon;

import java.util.Arrays;

/**
 * Minimum characters to be added at front to make string palindrome
 * Last Updated: 09-10-2019
 * Given a string str we need to tell minimum characters to be added at front of string to make string palindrome.
 *
 * Examples:
 *
 * Input  : str = "ABC"
 * Output : 2
 * We can make above string palindrome as "CBABC"
 * by adding 'B' and 'C' at front.
 *
 * Input  : str = "AACECAAAA";
 * Output : 2
 * We can make above string palindrome as AAAACECAAAA
 * by adding two A's at front of string.
 */

public class PalindromeMinCharacterProblem {
    public static void main(String[] args) {
        System.out.println(findMinCharsRequiredToMakeStringPalindrome("AACECAAAA"));
    }

    private static int[] computeLPS(String A){
        int[] LPS = new int[A.length()];
        int i=1,j=0;
        while(i<A.length()){
            if(A.charAt(i)==A.charAt(j)){
                j++;
                LPS[i]=j;
                i++;
            }else{
                if(j==0) {
                    i++;
                }
                else {
                    j = LPS[j - 1];
                }
            }
        }
        System.out.println(Arrays.toString(LPS));
        return LPS;
    }

    public static int findMinCharsRequiredToMakeStringPalindrome(String A){
        if(A==null || A.length()==0)
            return 0;
        StringBuilder SB = new StringBuilder(A);
        String rev = SB.reverse().toString();
        SB.reverse().append("$").append(rev);
        int[] LPS = computeLPS(SB.toString());
        return A.length()-LPS[SB.length()-1];
    }

}
