package com.java.coding.interviews.practise.salesforce;

import java.util.*;

/**
 * Given an array of words and an array of sentences, determine which words are anagrams of each other.
 * Calculate how many sentences can be created by replacing any word with one of the anagrams.
 * Example wordSet = ['listen', 'silent, 'it', 'is'] sentence = 'listen it is silent' Determine that listen is an anagram of silent.
 * Those two words can be replaced with their anagrams.
 * The four sentences that can be created are:
 * • listen it is silent
 * • listen it is listen
 * • silent it is silent
 * • silent it is listen
 * Function Description Complete the countSentences function in the editor below.
 * countSentences has the following parameters: string wordSet[n]: an array of strings string sentences[m]: an array of strings
 * Returns: int[]: an array of m integers that denote the number of sentences that can be formed from each sentence Constraints . 0
 */
public class CountSentencesProblem {
    public static void main(String[] args) {
        List<String> words = List.of("listen", "silent", "it", "is");
        List<String> sentences = List.of("listen it is silent", "i am listen silent");
        System.out.println(countSentences(words,sentences));
    }

    public static List<Integer> countSentences(List<String> words, List<String> sentences) {
        List<Integer> result = new ArrayList<Integer>();
        Map<String,Integer> dict = new HashMap<String,Integer>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String newWord = new String(chars);
            dict.put(newWord,dict.getOrDefault(newWord,0) + 1);
        }
        for(String sentence : sentences) {
            String[] split = sentence.split(" ");
            int count=1;
            for(String word : split) {
                char[] chars = word.toCharArray();
                Arrays.sort(chars);
                String newWord = new String(chars);
                if(dict.containsKey(newWord)){
                    count*=dict.get(newWord);
                }else {
                    count=0;
                }
            }
            result.add(count);
        }
        return result;
    }


}
