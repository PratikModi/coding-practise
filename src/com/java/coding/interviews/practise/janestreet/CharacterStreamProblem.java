package com.java.coding.interviews.practise.janestreet;

/**
 * 1032. Stream of Characters
 * Hard
 * 1.7K
 * 177
 * Companies
 * Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings words.
 *
 * For example, if words = ["abc", "xyz"] and the stream added the four characters (one by one) 'a', 'x', 'y', and 'z',
 * your algorithm should detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.
 *
 * Implement the StreamChecker class:
 *
 * StreamChecker(String[] words) Initializes the object with the strings array words.
 * boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from the stream forms a word that is in words.
 *
 *
 * Example 1:
 *
 * Input
 * ["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
 * [[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
 * Output
 * [null, false, false, false, true, false, true, false, false, false, false, false, true]
 *
 * Explanation
 * StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
 * streamChecker.query("a"); // return False
 * streamChecker.query("b"); // return False
 * streamChecker.query("c"); // return False
 * streamChecker.query("d"); // return True, because 'cd' is in the wordlist
 * streamChecker.query("e"); // return False
 * streamChecker.query("f"); // return True, because 'f' is in the wordlist
 * streamChecker.query("g"); // return False
 * streamChecker.query("h"); // return False
 * streamChecker.query("i"); // return False
 * streamChecker.query("j"); // return False
 * streamChecker.query("k"); // return False
 * streamChecker.query("l"); // return True, because 'kl' is in the wordlist
 */
public class CharacterStreamProblem {

    Trie trie=null;
    String check = "";

    public static void main(String[] args) {
        CharacterStreamProblem streamChecker = new CharacterStreamProblem(new String[]{"cd", "f", "kl"});
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('c'));
        System.out.println(streamChecker.query('d'));
        System.out.println(streamChecker.query('e'));
        System.out.println(streamChecker.query('f'));
        System.out.println(streamChecker.query('g'));
        System.out.println(streamChecker.query('h'));
        System.out.println(streamChecker.query('i'));
        System.out.println(streamChecker.query('j'));
        System.out.println(streamChecker.query('k'));
        System.out.println(streamChecker.query('l'));


    }

    public CharacterStreamProblem(String[] words) {
        this.trie= new Trie();
        for(String word : words){
            String reverse = new StringBuilder(word).reverse().toString();
            trie.insert(reverse);
        }
    }

    public boolean query(char letter) {
        check=letter+check;
        return trie.search(check);
    }

}
class Trie{
    TrieNode root =null;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String S){
        TrieNode temp = root;

        for(char c : S.toCharArray()){
            if(temp.child[c-'a']==null){
                temp.child[c-'a'] = new TrieNode();
            }
            temp=temp.child[c-'a'];
        }
        temp.end=true;
    }

    public boolean search(String S){
        TrieNode temp = root;

        for(char c: S.toCharArray()){
            if(temp.child[c-'a']==null)
                return false;
            if(temp.child[c-'a'].end)
                return true;
            temp = temp.child[c-'a'];
        }
        return false;
    }
}
class TrieNode{
    TrieNode[] child = new TrieNode[26];
    boolean end =false;
}
