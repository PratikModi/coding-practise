package com.java.coding.interviews.practise.airbnb;

import java.util.*;

/**
 *  Alien Dictionary
 *  There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.
 *
 * You are given a list of strings words from the alien language's dictionary,
 * where the strings in words are sorted lexicographically by the rules of this new language.
 *
 * Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules.
 * If there is no solution, return "". If there are multiple solutions, return any of them.
 *
 * A string s is lexicographically smaller than a string t if at the first letter where they differ,
 * the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same,
 * then s is smaller if and only if s.length < t.length.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 * Example 2:
 *
 * Input: words = ["z","x"]
 * Output: "zx"
 * Example 3:
 *
 * Input: words = ["z","x","z"]
 * Output: ""
 * Explanation: The order is invalid, so return "".
 */

public class AlienDictionaryProblem {

    public static void main(String[] args) {
        String[] words = new String[] {"wrt","wrf","er","ett","rftt"};
        System.out.println(alienOrder(words));
    }

    public static String alienOrder(String[] words) {
        if(words==null || words.length==0){
            return "";
        }
        Map<Character, Set<Character>> graph = new HashMap<>();
        for(int i=0;i<words.length;i++){
            String word = words[i];
            for(int j=0;j<word.length();j++){
                char c = word.charAt(j);
                if(!graph.containsKey(c)){
                    graph.put(c,new HashSet<>());
                }
            }
        }
        int[] indegree = new int[26];
        for(int i=1;i< words.length;i++) {
            String first = words[i - 1];
            String second = words[i];

            if (first.length() > second.length() && first.startsWith(second)) {
                graph.clear();
                return "";
            }
            for (int j = 0; j < first.length(); j++) {
                char F = first.charAt(j);
                char S = second.charAt(j);
                if (F != S) {
                    Set<Character> characters = graph.get(F);
                    if (!characters.contains(S)) {
                        characters.add(S);
                        graph.put(F, characters);
                        indegree[S - 'a']++;
                    }
                    break;
                }
            }
        }

            Queue<Character> Q = new LinkedList<>();
            for(Character C : graph.keySet()){
                if(indegree[C-'a']==0){
                    Q.add(C);
                }
            }

            StringBuilder SB = new StringBuilder();
            while(!Q.isEmpty()){
                char ch = Q.poll();
                SB.append(ch);
                Set<Character> characters = graph.get(ch);
                for(Character c : characters){
                    indegree[c-'a']--;
                    if(indegree[c-'a']==0){
                        Q.add(c);
                    }
                }
            }
            return SB.toString().length()==graph.keySet().size()?SB.toString():"";
        }

}
