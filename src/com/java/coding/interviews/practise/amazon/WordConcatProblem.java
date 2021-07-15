package com.java.coding.interviews.practise.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find the most granular words from list of words.
 *
 * Example:
 *
 * Input : ['athens', 'greece', 'athensgreece', 'felix', 'felixathensgreece', 'greeceathens']
 *
 * Output: ['athens', 'greece', 'felix']
 */
public class WordConcatProblem {
    public static void main(String[] args) {
        String[] words = new String[]{"athens", "greece", "athensgreece", "felix", "felixathensgreece", "greeceathens"};
        System.out.println(findAllConcatenatedWordsInADict(words));
    }

    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words,(s1,s2)->s1.length()-s2.length());
        System.out.println(Arrays.toString(words));
        List<String> result = new ArrayList<>();
        Trie trie = new Trie();
        for(String W : words){
            trie.insert(W,result);
        }
        return result;
    }

}

class Trie{
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word, List<String> result){
        TrieNode crawl = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(crawl.children[c-'a']==null){
                crawl.children[c-'a']=new TrieNode();
            }
            crawl=crawl.children[c-'a'];
            if(crawl.is_word){
                result.add(word.substring(0,i+1));
            }
        }
        crawl.is_word=true;
    }

}

class TrieNode{
    TrieNode[] children;
    boolean is_word;

    public TrieNode() {
        children = new TrieNode[26];
        is_word=false;
    }
}
