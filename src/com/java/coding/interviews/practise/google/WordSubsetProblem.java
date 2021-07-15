package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
import java.util.List;

/**
 * We are given two arrays words1 and words2 of words.  Each word is a string of lowercase letters.
 *
 * Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.
 * For example, "wrr" is a subset of "warrior", but is not a subset of "world".
 *
 * Now say a word a from words1 is universal if for every b in words2, b is a subset of a.
 *
 * Return a list of all universal words in words1.  You can return the words in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
 * Output: ["facebook","google","leetcode"]
 * Example 2:
 *
 * Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
 * Output: ["apple","google","leetcode"]
 * Example 3:
 *
 * Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","oo"]
 * Output: ["facebook","google"]
 * Example 4:
 *
 * Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["lo","eo"]
 * Output: ["google","leetcode"]
 * Example 5:
 *
 * Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["ec","oc","ceo"]
 * Output: ["facebook","leetcode"]
 */

public class WordSubsetProblem {

    public static void main(String[] args) {
        String[] word1 = new String[] {"amazon","apple","facebook","google","leetcode"};
        String[] word2 = new String[] {"ec","oc","ceo"};
        System.out.println(wordSubsets(word1,word2));
    }

    public static List<String> wordSubsets(String[] words1, String[] words2) {
        int[] maxFrequency = new int[26];
        for(String S : words2){
            int[] frequency = getCharsFrequency(S);
            for(int i=0;i<26;i++){
                maxFrequency[i]=Math.max(frequency[i],maxFrequency[i]);
            }
        }
        List<String> result = new ArrayList<>();
        for(String S : words1){
            int[] frequency = getCharsFrequency(S);
            boolean flag = true;
            for(int i=0;i<26;i++){
                if(frequency[i]<maxFrequency[i]){
                    flag=false;
                    break;
                }
            }
            if(flag){
                result.add(S);
            }
        }
        return result;
    }


    private static int[] getCharsFrequency(String S){
        int[] frequency = new int[26];
        for(char c : S.toCharArray()){
            frequency[c-'a']++;
        }
        return frequency;
    }

}
