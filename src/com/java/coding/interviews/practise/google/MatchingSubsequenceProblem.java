package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Leetcode: 792. Number of Matching Subsequences
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted
 * without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 *
 *
 * Example 1:
 *
 * Input: s = "abcde", words = ["a","bb","acd","ace"]
 * Output: 3
 * Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
 * Example 2:
 *
 * Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * Output: 2
 * ⚙️ Time Complexity
 *
 * Let:
 * 	•	n = s.length()
 * 	•	m = total length of all words
 *
 * Each character of each word is processed once across buckets.
 *
 * 👉 O(n + m) — very efficient.
 *
 * 🧩 Space Complexity
 * 	•	O(m) for storing all words across queues.
 */
public class MatchingSubsequenceProblem {

    public static void main(String[] args) {
        String s = "dsahjpjauf";
        String[] words = new String[]{"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"};
        System.out.println(numMatchingSubsequence(s,words));
        s="abcde";
        words=new String[]{"a","bb","acd","ace"};
        System.out.println(numMatchingSubsequence(s,words));
    }

    public static int numMatchingSubsequence(String s, String[] words) {
        List<Queue<String>> buckets = new ArrayList<>();
        for(int i=0;i<26;i++){
            buckets.add(new LinkedList<>());
        }
        for(String word : words){
            char ch = word.charAt(0);
            buckets.get(ch-'a').offer(word);
        }

        int count=0;
        for(char ch : s.toCharArray()){
            Queue<String> queue = buckets.get(ch-'a');
            int size = queue.size();
            for(int i=0;i<size;i++){
                String word = queue.poll();
                if(word.length()==1) count++;
                else{
                    String next = word.substring(1);
                    char nextChar = next.charAt(0);
                    buckets.get(nextChar-'a').offer(next);
                }
            }
        }
        return count;
    }

}
