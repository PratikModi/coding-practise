package com.java.coding.interviews.practise.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrefixSuffixSearchProblem {
    TrieNode prefixTrie = new TrieNode();
    TrieNode suffixTrie = new TrieNode();

    public PrefixSuffixSearchProblem(String[] words) {
        Set<String> wordSet = new HashSet<>();
        for(int index=words.length-1;index>-1;index--){
            if(wordSet.contains(words[index]))
                continue;
            wordSet.add(words[index]);
            int length = words[index].length();
            //System.out.println(words[index]);
            insert(prefixTrie,words[index].toCharArray(),index,0,length,1);
            insert(suffixTrie,words[index].toCharArray(),index,length-1,-1,-1);
        }
    }

    private void insert(TrieNode trie, char[] word, int index, int start, int end, int step){
        for(int i=start;i!=end;i+=step){
            int c = word[i]-'a';
            if(trie.children[c]==null){
                trie.children[c]=new TrieNode();
            }
            trie = trie.children[c];
            trie.indexes.add(index);
        }
    }

    private List<Integer> retrieve(TrieNode trie, char[] word, int start, int end, int step){
        for(int i=start;i!=end;i+=step){
            trie = trie.children[word[i]-'a'];
            if(trie==null) return new ArrayList<>();
        }
        return trie.indexes;
    }

    public int find(String prefix, String suffix){
        List<Integer> prefixIndexes = retrieve(prefixTrie, prefix.toCharArray(),0,prefix.length(),1);
        List<Integer> suffixIndexes = retrieve(suffixTrie, suffix.toCharArray(),prefix.length()-1,-1,-1);
        int pIndex =0, sIndex=0;
        while(pIndex<prefixIndexes.size() && sIndex<suffixIndexes.size()){
            if(prefixIndexes.get(pIndex)==suffixIndexes.get(sIndex))
                return sIndex;
            else if(prefixIndexes.get(pIndex)>suffixIndexes.get(sIndex))
                pIndex++;
            else sIndex++;
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"apple"};
        PrefixSuffixSearchProblem problem = new PrefixSuffixSearchProblem(words);
        var index = problem.find("a", "e");
        System.out.println(index);
    }


}

class TrieNode{
    TrieNode[] children;
    List<Integer> indexes;

    public TrieNode() {
        this.children = new TrieNode[26];
        indexes = new ArrayList<>();
    }
}
