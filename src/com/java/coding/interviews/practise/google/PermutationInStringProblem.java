package com.java.coding.interviews.practise.google;

/**
 * 567. Permutation in String
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 */
public class PermutationInStringProblem {

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(checkInclusion(s1,s2));
        s2 = "eidboaoo";
        System.out.println(checkInclusion(s1,s2));
    }

    public static boolean checkInclusion(String s1, String s2) {
        if(s1.length()>s2.length()) return false;

        int[] freqS1 = new int[26];
        int[] freqS2 = new int[26];

        for(char c : s1.toCharArray()){
            freqS1[c-'a']++;
        }

        for(int i=0;i<s2.length();i++){
            freqS2[s2.charAt(i)-'a']++;
            //Remove Old Characters
            if(i>=s1.length()){
                freqS2[s2.charAt(i-s1.length())-'a']--;
            }
            //This is the first time you will reach the first window, before that S2 is too short to compare.
            //👉 The first time our window reaches size = s1.length() is when i == s1.length() - 1.
            //Before that, the window is too short to compare.
            if(i>=s1.length()-1){
                if(matches(freqS1,freqS2)) return true;
            }
        }

        return false;
    }

    private static boolean matches(int[] a, int[] b){
        for(int i=0;i<26;i++){
            if(a[i]!=b[i]) return false;
        }
        return true;
    }
}
