package com.java.coding.interviews.practise.uber;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class WordBreakProblem {
    public static void main(String[] args) {
        String S = "applepenapple";
        List<String> wordsList = Arrays.asList("apple", "pen");
        System.out.println(wordBreak(S,wordsList));
        System.out.println(wordBreakIterative(S,wordsList));
    }
    //O(N^2)
    private static boolean wordBreak(String S, List<String> wordsList){
        if(S==null)
            return false;
        int N=S.length();
        Set<String> words = new HashSet<>(wordsList);
        boolean[] DP = new boolean[N+1];
        DP[0]=true;
        for(int i=1;i<=N;i++){
            for(int j=0;j<i;j++){
                String temp = S.substring(j,i);
                if(words.contains(temp) && DP[j]){
                    System.out.println(i+"=="+j);
                    DP[i]=true;
                }
            }
        }
        return DP[N];
    }
    //O(2^N)
    private static boolean wordBreakIterative(String S, List<String> wordsList){
        if(S==null)
            return false;
        int N = S.length();
        Set<String> words = new HashSet<>(wordsList);
        String first;
        String second;
        for(int i=1;i<N;i++){
            first = S.substring(0,i);
            if(words.contains(first)){
                second = S.substring(i);
                if(second==null || second.isEmpty() || words.contains(second) || wordBreakIterative(second,wordsList))
                    return true;
            }
        }
        return false;
    }

}
