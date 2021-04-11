package com.java.amazon.dynamic.google;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
 * so that the concatenation of the two words,
 * i.e. words[i] + words[j] is a palindrome.
 *
 * Example 1:
 *
 * Input: ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * Example 2:
 *
 * Input: ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 */
public class PalindromePairProblem {
    public static void main(String[] args) {
        String[] words = new String[] {"abcd","dcba","lls","s","sssll"};
        System.out.println(palindromePairs(words));
        words = new String[]{"a","abc","aba",""};
        System.out.println(palindromePairs(words));
    }

    public static ArrayList<ArrayList<Integer>> palindromePairs(String[] words){
        var result = new ArrayList<ArrayList<Integer>>();
        var reverseWordMap = new HashMap<String,Integer>();
        if(words==null || words.length<2){
            return result;
        }
        for(int i=0;i<words.length;i++){
            reverseWordMap.put(new StringBuilder(words[i]).reverse().toString(),i);
        }
        for(int i=0;i<words.length;i++){
            String word = words[i];
            //Prefix Check
            for(int j=0;j<word.length();j++){
                if(isPalindrome(word.substring(j))){
                    if(reverseWordMap.containsKey(word.substring(0,j)) && reverseWordMap.get(word.substring(0,j))!=i){
                        //System.out.println("==="+word.substring(0,j));
                        var pair = new ArrayList<Integer>();
                        pair.add(i);
                        pair.add(reverseWordMap.get(word.substring(0,j)));
                        result.add(pair);
                    }
                }
            }
            //System.out.println(result);
            //Suffix Search
            for(int k=word.length()-1;k>=0;k--){
                if(isPalindrome(word.substring(0,k+1))){
                    if(reverseWordMap.containsKey(word.substring(k+1)) && reverseWordMap.get(word.substring(k+1))!=i){
                        var pair = new ArrayList<Integer>();
                        pair.add(reverseWordMap.get(word.substring(k+1)));
                        pair.add(i);
                        result.add(pair);
                    }
                }
            }

            //Complete String search
            if(reverseWordMap.containsKey(word) && reverseWordMap.get(word)!=i ){
                var pair = new ArrayList<Integer>();
                pair.add(i);
                pair.add(reverseWordMap.get(word));
                result.add(pair);
            }
        }
        return result;
    }

    private static boolean isPalindrome(String S){
        int len = S.length();
        for(int i=0;i<len/2;i++){
            if(S.charAt(i)!=S.charAt(len-i-1)){
                return false;
            }
        }
        return true;
    }
}
