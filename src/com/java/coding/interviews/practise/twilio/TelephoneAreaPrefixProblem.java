package com.java.coding.interviews.practise.twilio;

import java.util.*;

/**
 * Given two arrays of strings, one containing the prefixes (area code) and one with a long string of numbers (phone numbers),
 * return the longest prefix corresponding to all phone numbers.
 *
 * Input: Area Code: ["213", "21358", "1234", "12"]
 * Phone Numbers: ["21349049", "1204539492", "123490485904"]
 * Output: ['213', '12', '1234']
 */
public class TelephoneAreaPrefixProblem {

    public static void main(String[] args) {
        Trie trie = new Trie();
        List<String> areaCode = Arrays.asList("213", "21358", "1234", "12");
        List<String> phoneNumber = Arrays.asList("21349049", "1204539492", "123490485904");
        List<String> result = new ArrayList<>();
        for(String code : areaCode) {
            trie.insert(code);
        }
        for(String number : phoneNumber){
            result.add(trie.getPrefixMatch(number));
        }
        System.out.println(result);
    }
}

class TrieNode{
    char value;
    Map<Character,TrieNode> children;
    boolean isEnd;

    public TrieNode(char value) {
        this.value = value;
        children = new HashMap<>();
        isEnd=false;
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "value=" + value +
                ", children=" + children +
                ", isEnd=" + isEnd +
                '}';
    }

    public char getValue() {
        return value;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}

class Trie{
    private TrieNode root;

    public Trie() {
        root = new TrieNode((char)0);
    }

    public void insert(String value){
        if(value==null || value.isEmpty())
            return;
        TrieNode crawl = root;
        for(int level=0;level<value.length();level++){
            char ch = value.charAt(level);
            Map<Character,TrieNode> children = crawl.getChildren();
            if(children.containsKey(ch)){
                crawl = children.get(ch);
            }else{
                TrieNode temp = new TrieNode(ch);
                children.put(ch,temp);
                crawl=temp;
            }
        }
        crawl.setEnd(true);

    }

    public String getPrefixMatch(String input){
        String result="";
        int prevMatch=0;
        int N = input.length();

        TrieNode crawl = root;
        for(int i=0;i<N;i++){
            char ch = input.charAt(i);
            Map<Character,TrieNode> children = crawl.getChildren();
            if(children.containsKey(ch)){
                result+=ch;
                //System.out.println(input+"=="+result);
                crawl=children.get(ch);
                if(crawl.isEnd){
                    prevMatch = i+1;
                }
            }else
                break;
        }

        if(!crawl.isEnd){
            return result.substring(0,prevMatch);
        }
        return result;
    }

}

