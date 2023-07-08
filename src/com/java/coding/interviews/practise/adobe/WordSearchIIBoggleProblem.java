package com.java.coding.interviews.practise.adobe;

import java.util.ArrayList;
import java.util.List;

/**
 *  Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 */
public class WordSearchIIBoggleProblem {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'o','a','a','n'},{'e','t','a','e'}, {'i','h','k','r'},{'i','f','l','v'}};
        String[] words = new String[] {"oath","pea","eat","rain"};
        List<String> result = findWords(board,words);
        System.out.println(result);
    }

    static class Trie {
        Trie[] children;
        String word;

        public Trie() {
            this.children = new Trie[26];
            this.word = null;
        }
    }
    public static Trie buildTrie(String[] words){
        Trie root = new Trie();
        for(String word : words){
            Trie current = root;
            for(int i=0;i<word.length();i++){
                char c = word.charAt(i);
                if(current.children[c-'a']==null){
                    current.children[c-'a']=new Trie();
                }
                current = current.children[c-'a'];
            }
            current.word=word;
        }
        return root;
    }

    public static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        Trie root = buildTrie(words);
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                dfs(board,result,root,i,j);
            }
        }
        return result;
    }

    private static void dfs(char[][] board, List<String> result, Trie root, int i, int j){
        if(i<0 || i>=board.length || j<0 || j>=board[i].length)
            return;
        char c = board[i][j];
        if(c=='*' || root.children[c-'a']==null)
            return;
        root = root.children[c-'a'];
        if(root.word!=null){
            result.add(root.word);
            root.word=null;
        }
        board[i][j]='*';
        dfs(board,result,root,i-1,j);
        dfs(board,result,root,i+1,j);
        dfs(board,result,root,i,j-1);
        dfs(board,result,root,i,j+1);
        board[i][j]=c;
    }
}
