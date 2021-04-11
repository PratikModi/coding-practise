package com.java.amazon.dynamic.google;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of words, each word consists of English lowercase letters.
 *
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.
 * For example, "abc" is a predecessor of "abac".
 *
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3,
 * and so on.
 *
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: One of the longest word chain is "a","ba","bda","bdca".
 * Example 2:
 *
 * Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 * Output: 5
 */
public class WordChainProblem {

    public static void main(String[] args) {
        String[] words = {"a","b","ba","bca","bda","bdca"};
        int result = longestStrChain(words);
        System.out.println(result);
    }

    public static int longestStrChain(String[] words) {
        int result =0;
        if(words==null || words.length==0)
            return result;
        Arrays.sort(words,(a,b)->a.length()-b.length());
        System.out.println(Arrays.toString(words));
        Map<String,Integer> M = new HashMap<>();
        for(String word : words){
            if(M.containsKey(word)) continue;
            M.putIfAbsent(word,1);
            for(int i=0;i<word.length();i++){
                StringBuilder SB = new StringBuilder(word);
                SB.deleteCharAt(i);
                String new_word=SB.toString();
                //System.out.println(SB);
                if(M.containsKey(new_word) && M.get(new_word)+1>M.get(word)){
                    M.put(word,M.get(new_word)+1);
                }
                //System.out.println(M);
                result=Math.max(result,M.get(word));
            }
        }
        return result;

    }

}
