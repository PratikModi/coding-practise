package com.java.amazon.dynamic.uber;

import java.util.*;

/**
 * Word Ladder II
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: []
 * "red"
 * "tax"
 * ["ted","tex","red","tax","tad","den","rex","pee"]
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadderIIProblem {
    public static void main(String[] args) {
        String beginWord="red";
        String endWord="tax";
        List<String> dictionary = Arrays.asList("ted","tex","red","tax","tad","den","rex","pee");
        System.out.println(findLadders(beginWord,endWord,dictionary));
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        if(!wordList.contains(endWord))
            return result;
        Map<String,List<String>> adj = new HashMap<>();
        Map<String,Integer> visited = new HashMap<>();
        for(String s:wordList){
            visited.put(s,Integer.MAX_VALUE);
        }
        //STEP-1: Find min-depth using BFS
        Queue<String> Q = new LinkedList<>();
        Q.add(beginWord);
        visited.put(beginWord,0);
        //int min = Integer.MAX_VALUE;
        while(!Q.isEmpty()){
            String temp = Q.poll();
            //int step=visited.get(temp)+1;
       //     if(step>min)continue;
            for(int i=0;i<temp.length();i++){
                StringBuilder sb = new StringBuilder(temp);
                for(char x='a';x<='z';x++){
                    if(sb.charAt(i)==x) continue;
                    sb.setCharAt(i,x);
                    String new_word=sb.toString();
                    if(visited.containsKey(new_word)){
                        if(visited.get(new_word)<visited.get(temp)+1)continue;
                        else if(visited.get(new_word)>visited.get(temp)+1){
                            Q.add(new_word);
                            visited.put(new_word,visited.get(temp)+1);
                        }
                        adj.putIfAbsent(temp,new ArrayList<String>());
                        adj.get(temp).add(new_word);
                        //if(new_word.equals(endWord))
         //                   min=step;
                    }
                }
            }
        }
        System.out.println(visited);
        System.out.println(adj);
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord,endWord,adj,path,result);
        return result;
    }

    private static void dfs(String beginWord, String endWord,Map<String,List<String>> adj,List<String> path, List<List<String>> result){
        //System.out.println(adj);
        if(beginWord.equals(endWord)){
            result.add(new ArrayList<>(path));
            return;
        }
        if(adj.containsKey(beginWord)) {
            for (String s : adj.get(beginWord)) {
                path.add(s);
                dfs(s, endWord, adj, path, result);
                path.remove(path.size()-1);
            }
        }

    }




}
