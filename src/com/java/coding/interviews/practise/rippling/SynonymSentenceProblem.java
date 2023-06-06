package com.java.coding.interviews.practise.rippling;

import java.util.*;

/**
 *You are given a list of equivalent string pairs synonyms where synonyms[i] = [si, ti] indicates that si and ti are equivalent strings.
 *You are also given a sentence text.
 *
 * Return all possible synonymous sentences sorted lexicographically.
 *
 *
 *
 * Example 1:
 *
 * Input: synonyms = [["happy","joy"],["sad","sorrow"],["joy","cheerful"]], text = "I am happy today but was sad yesterday"
 * Output: ["I am cheerful today but was sad yesterday","I am cheerful today but was sorrow yesterday","I am happy today but was sad yesterday",
 * "I am happy today but was sorrow yesterday","I am joy today but was sad yesterday","I am joy today but was sorrow yesterday"]
 * Example 2:
 *
 * Input: synonyms = [["happy","joy"],["cheerful","glad"]], text = "I am happy today but was sad yesterday"
 * Output: ["I am happy today but was sad yesterday","I am joy today but was sad yesterday"]
 *
 *
 * Constraints:
 *
 * 0 <= synonyms.length <= 10
 * synonyms[i].length == 2
 * 1 <= si.length, ti.length <= 10
 * si != ti
 * text consists of at most 10 words.
 * All the pairs of synonyms are unique.
 * The words of text are separated by single spaces.
 * Algorithm
 * Union search, recursive enumeration
 *
 * O(2^n * L)
 * Use the union search to find the synonym group, and then recursively enumerate and replace the synonym.
 *
 * Time complexity
 * The total time complexity of finding the synonym group is approximately O(n), and the time complexity of recursion is O(2^n * L), where L is the length of the string O(L) time is required for each word replacement).
 *
 * Therefore, the total time complexity is O(2^n * L).
 *
 * Space complexity
 * Union search requires O(n) space, and recording answers requires O(2^n * L) space, so the total space complexity is O(2^n * L).
 */
public class SynonymSentenceProblem {

    public static void main(String[] args) {
        List<List<String>> words = new ArrayList<>();
        List<String> w1 = List.of("happy","joy");
        List<String> w2 = List.of("cheerful","glad");
        words.add(w1);
        words.add(w2);
        var result = getSynonymSentence(words,"I am happy today but was sad yesterday");
        System.out.println(result);
        words = new ArrayList<>();
        List<String> w3 = List.of("happy","joy");
        List<String> w4 = List.of("sad","sorrow");
        List<String> w5 = List.of("joy","cheerful");
        words.add(w3);
        words.add(w4);
        words.add(w5);
        result = getSynonymSentence(words,"I am happy today but was sad yesterday");
        System.out.println(result);
    }

    public static List<String> getSynonymSentence(List<List<String>> words, String sentence){
        List<String> result = new ArrayList<>();
        //System.out.println(words);
        Map<String, Set<String>> wordsMap = new HashMap<>();
        for(List<String> list : words){
            String w1 = list.get(0);
            String w2 = list.get(1);
            wordsMap.putIfAbsent(w1,new HashSet<>());
            wordsMap.putIfAbsent(w2,new HashSet<>());
            wordsMap.get(w1).add(w2);
            wordsMap.get(w2).add(w1);
        }
        //System.out.println(wordsMap);
        String[] sWords = sentence.split("\\W+");
        helper(wordsMap,sWords,0,"",result);
        Collections.sort(result);
        return result;
    }

    private static void helper(Map<String,Set<String>> wordsMap, String[] sWords, int index, String sentence,List<String> result){
        if(index== sWords.length){
            result.add(sentence);
            return;
        }
        if(wordsMap.containsKey(sWords[index])){
            List<String> choices = new ArrayList<>();
            getSynonyms(wordsMap,sWords[index],new HashSet<String>(),choices);
            //System.out.println(choices);
            for(String S : choices){
                helper(wordsMap,sWords,index+1,sentence+((index==sWords.length-1)?S:S+" "),result);
            }
        }else{
            helper(wordsMap,sWords,index+1,sentence+((index== sWords.length-1)?sWords[index]:sWords[index]+" "),result);
        }
    }

    private static void getSynonyms(Map<String,Set<String>> wordsMap,String s, Set<String> visited, List<String> choices){
        choices.add(s);
        visited.add(s);
        for(String each : wordsMap.get(s)){
            if(!visited.contains(each)){
                getSynonyms(wordsMap, each, visited, choices);
            }
        }

    }



}
