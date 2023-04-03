package com.java.coding.interviews.practise.bloomberg;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 */
public class ValidateAnagramProblem {
    public static void main(String[] args) {
        String S = "anagram";
        String T = "nagaram";

        System.out.println(validateAnagram(S,T));
        System.out.println(validateAnagram("rat","car"));
    }

    public static boolean validateAnagram(String S, String T){
        if(S.length()!=T.length())
            return false;
        int[] chars = new int[26];
        for(int i=0;i<S.length();i++){
            chars[S.charAt(i)-'a']++;
            chars[T.charAt(i)-'a']--;
        }
        for(int i=0;i<26;i++){
            if(chars[i]!=0)
                return false;
        }
        return true;
    }
}
