package com.java.coding.interviews.practise.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.
 *
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * Example 2:
 *
 * Input: words = ["cat","dog","catdog"]
 * Output: ["catdog"]
 */
public class ConcatenatedWordsProblem {

    public static void main(String[] args) {
        String[] words = new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        System.out.println(findAllConcatenatedWordsInADict(words));
    }

    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words,(s1,s2)->s1.length()-s2.length());
        List<String> result = new ArrayList<>();
        Trie2 trie2 = new Trie2();
        for(String word : words){
            trie2.insert(word);
        }
        for(String word : words) {
            if (trie2.isConcatenatedWord(word, 0, 0)){
                result.add(word);
            }
        }
        return result;
    }
}
class TrieNode2{
    TrieNode2[] children;
    boolean isWord;

    public TrieNode2() {
        children = new TrieNode2[26];
        isWord=false;
    }
}

class Trie2{
    TrieNode2 root = null;

    public Trie2() {
        root = new TrieNode2();
    }

    public void insert(String W){
        TrieNode2 crawl = root;
        for(char c : W.toCharArray()){
            if(crawl.children[c-'a']==null){
                crawl.children[c-'a']=new TrieNode2();
            }
            crawl=crawl.children[c-'a'];
        }
        crawl.isWord=true;
    }

    public boolean isConcatenatedWord(String W, int index, int boundryCount){
        if(index==W.length())
            return boundryCount > 1;
        TrieNode2 crawl = root;
        for(int i=index;i<W.length();i++){
            char c = W.charAt(i);
            if(crawl.children[c-'a']==null) return false;
            crawl=crawl.children[c-'a'];
            if(crawl.isWord){
                if(isConcatenatedWord(W,i+1,boundryCount+1)){
                    return true;
                }
            }
        }
        return false;
    }

}