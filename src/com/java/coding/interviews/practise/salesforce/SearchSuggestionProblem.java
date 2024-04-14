package com.java.coding.interviews.practise.salesforce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given an array of strings products and a string searchWord.
 *
 * Design a system that suggests at most three product names from products after each character of searchWord is typed.
 * Suggested products should have common prefix with searchWord.
 * If there are more than three products with a common prefix return the three lexicographically minimums products.
 *
 * Return a list of lists of the suggested products after each character of searchWord is typed.
 *
 *
 *
 * Example 1:
 *
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
 * After typing m and mo all products match, and we show user ["mobile","moneypot","monitor"].
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"].
 * Example 2:
 *
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Explanation: The only word "havana" will always be suggested while typing the search word.
 */
public class SearchSuggestionProblem {

    public static void main(String[] args) {
        SearchSuggestionProblem problem = new SearchSuggestionProblem();
        String[] products = new String[] {"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";
        var result = problem.suggestedProducts(products,searchWord);
        System.out.println(result);
        products = new String[]{"havana"};
        searchWord = "havana";
        result = problem.suggestedProducts(products,searchWord);
        System.out.println(result);

    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNode root = new TrieNode();
        for(String product : products) insert(root,product);

        List<List<String>> result = new ArrayList<>();
        for(char c : searchWord.toCharArray()){
            root = root.children[c-'a'];
            if(root==null) break;
            result.add(root.getTopThree());
        }
        while(result.size()<searchWord.length()){
            result.add(new ArrayList<>());
        }
        return result;
    }

    private void insert(TrieNode root, String word){
        for(char c : word.toCharArray()){
            if(root.children[c-'a']==null){
                root.children[c-'a']=new TrieNode();
            }
            root = root.children[c-'a'];
            root.addToPQ(word);
        }
    }

}

class TrieNode{
    TrieNode[] children;
    PriorityQueue<String> pq;

    TrieNode(){
        this.children = new TrieNode[26];
        this.pq = new PriorityQueue<>((a,b)->b.compareTo(a));
    }
    public void addToPQ(String word){
        pq.add(word);
        if(pq.size()>3)pq.poll();
    }

    public List<String> getTopThree(){
        List<String> topThree = new ArrayList<>();
        while(!pq.isEmpty()){
            topThree.add(pq.poll());
        }
        Collections.reverse(topThree);
        return topThree;
    }


}