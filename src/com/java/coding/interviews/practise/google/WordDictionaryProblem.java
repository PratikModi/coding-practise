package com.java.coding.interviews.practise.google;

/**
 * Leet Code: 211. Design Add and Search Words Data Structure
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 */
public class WordDictionaryProblem {

    public static void main(String[] args) {
        WordDictionary dictionary = new WordDictionary();
        String[] words = {"bad","dad","mad","pad"};//,".ad",{"b.."}}
        for(String word : words){
            dictionary.addWord(word);
        }
        System.out.println(dictionary.search("bad"));
        System.out.println(dictionary.search(".ad"));
        System.out.println(dictionary.search("b.."));
        System.out.println(dictionary.search("xad"));
    }


}
class WordDictionary{
    WordDictionary[] children;
    boolean isEndOfWord;

    public WordDictionary() {
        this.children = new WordDictionary[26];
        this.isEndOfWord = false;
    }

    public void addWord(String word) {
        WordDictionary root = this;
        for(char c : word.toCharArray()){
            if(root.children[c-'a']==null){
                root.children[c-'a'] = new WordDictionary();
            }
            root=root.children[c-'a'];
        }
        root.isEndOfWord=true;
    }

    public boolean search(String word) {
        WordDictionary root = this;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(ch == '.'){
                for(WordDictionary wd : root.children){
                    if(wd!=null && wd.search(word.substring(i+1))) return true;
                }
                return false;
            }
            if(root.children[ch-'a'] == null) return false;
            root = root.children[ch-'a'];
        }
        return root!=null && root.isEndOfWord;
    }

}
