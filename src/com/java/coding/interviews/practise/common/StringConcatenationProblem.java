package com.java.coding.interviews.practise.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Substring with Concatenation of All Words
 *
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once
 * and without any intervening characters.
 *
 * Example 1:
 *
 * Input:
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 * Example 2:
 *
 * Input:
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * Output: []
 */

public class StringConcatenationProblem {

    public static void main(String[] args) {
        String S="barfoothefoobarman";
        String[] W = new String[]{"foo","bar"};
        List<Integer> result = findSubstring(S,W);
        System.out.println(result);
        S="wordgoodgoodgoodbestword";
        W=new String[]{"word","good","best","word"};
        result=findSubstring(S,W);
        System.out.println(result);
    }

    public static List<Integer> findSubstring(String S, String[] W){
        List<Integer> result = new ArrayList<>();
        if(S==null || W==null || S.length()==0 || W.length==0)
            return result;
        Map<String,Integer> wordMap = new HashMap<>();
        for(String word : W){
            wordMap.put(word,wordMap.getOrDefault(word,0)+1);
        }
        int SL=S.length();
        int WL=W.length;
        int L=W[0].length();
        int totalLength=WL*W[0].length();
        int searchWindow=SL-totalLength+1;
        for(int i=0;i<searchWindow;i++){
            Map<String,Integer> map = new HashMap<>(wordMap);
            int counter=map.size();
            for(int j=i;j<i+totalLength;j+=L){
                String substring = S.substring(j,j+L);
                if(map.containsKey(substring)){
                    if(map.get(substring)==1) counter--;
                    map.put(substring,map.get(substring)-1);
                }
            }
            if(counter==0)
                result.add(i);
        }

        return result;
    }
}
