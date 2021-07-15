package com.java.coding.interviews.practise.amazon;

import java.util.*;

/**
 * Given a string paragraph and a string array of the banned words banned, return the most frequent word that is not banned.
 * It is guaranteed there is at least one word that is not banned, and that the answer is unique.
 *
 * The words in paragraph are case-insensitive and the answer should be returned in lowercase.
 *
 *
 *
 * Example 1:
 *
 * Input: paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.", banned = ["hit"]
 * Output: "ball"
 * Explanation:
 * "hit" occurs 3 times, but it is a banned word.
 * "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 * Note that words in the paragraph are not case sensitive,
 * that punctuation is ignored (even if adjacent to words, such as "ball,"),
 * and that "hit" isn't the answer even though it occurs more because it is banned.
 * Example 2:
 *
 * Input: paragraph = "a.", banned = []
 * Output: "a"
 */
public class MostCommonWordProblem {
    public static void main(String[] args) {
        String paragraph= "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = new String[]{"hit"};
        System.out.println(findMostCommonWord(paragraph,banned));
    }

    public static String findMostCommonWord(String paragraph, String[] banned){
        String result="";
        if(paragraph==null || paragraph.isEmpty())
            return result;
        Set<String> words = new HashSet<>();
        for(String S : banned){
            words.add(S);
        }
        Map<String,Integer> wordMap = new HashMap<>();
        String normalizedString = paragraph.replaceAll("[^a-zA-Z0-9 ]"," ").toLowerCase();
        String[] splits = normalizedString.split(" ");
        for(String S : splits) {
            wordMap.put(S,wordMap.getOrDefault(S,0)+1);
        }
        int frequency=0;
        Iterator<String> iterator = wordMap.keySet().iterator();
        while(iterator.hasNext()){
            String W = iterator.next().trim();
            if(!words.contains(W) && wordMap.get(W)>frequency){
                result=W;
                frequency=wordMap.get(W);
            }
        }
        return result;
    }
}
