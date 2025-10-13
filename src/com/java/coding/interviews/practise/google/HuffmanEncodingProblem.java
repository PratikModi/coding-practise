package com.java.coding.interviews.practise.google;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Perfect — the Huffman Encoding problem is a classic and elegant greedy algorithm problem for data compression.
 * 🧩 What is Huffman Encoding?
 *
 * It’s a lossless data compression algorithm that:
 * 	•	Assigns shorter binary codes to more frequent characters
 * 	•	Assigns longer binary codes to less frequent characters
 *
 * Thus, the total number of bits needed to encode the message is minimized.
 *
 * 💡 Core Idea
 * 	1.	Count frequency of each character.
 * 	2.	Build a binary tree (Huffman Tree) such that:
 * 	•	The least frequent characters are deeper (longer codes)
 * 	•	The most frequent characters are closer to root (shorter codes)
 * 	3.	Generate binary codes for each character by traversing the tree:
 * 	•	Left → append '0'
 * 	•	Right → append '1'
 */
public class HuffmanEncodingProblem {

    static class HuffmanNode{
        char ch;
        int freq;
        HuffmanNode left;
        HuffmanNode right;
        HuffmanNode(char ch, int freq){
            this.ch = ch;
            this.freq = freq;
        }
        //To create patent node
        HuffmanNode(int freq, HuffmanNode left, HuffmanNode right){
            this.ch = '\0'; //internal node
            this.freq = freq;
            this.left = left;
            this.right=right;
        }
    }

    public static void main(String[] args) {
        String text = "abbcccdddd";
        Map<Character,String> codeMap = buildHuffmanCodes(text);
        System.out.println(codeMap);
        StringBuilder encodedString = new StringBuilder();
        for(char c : text.toCharArray()){
            encodedString.append(codeMap.get(c));
        }
        System.out.println(encodedString);
    }

    public static Map<Character, String> buildHuffmanCodes(String text) {
        Map<Character,Integer> frequencyMap = new HashMap<>();
        // Step 1: Count frequency
        for(char c : text.toCharArray()){
            frequencyMap.put(c, frequencyMap.getOrDefault(c,0)+1);
        }
        // Step 2: Build min-heap
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(Comparator.comparingInt(n->n.freq));
        for(Map.Entry<Character,Integer> entry : frequencyMap.entrySet()){
            pq.offer(new HuffmanNode(entry.getKey(),entry.getValue()));
        }
        // Step 3: Build Huffman Tree
        while(pq.size()>1){
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode(left.freq+right.freq,left,right);
            pq.offer(parent);
        }

        HuffmanNode root  = pq.peek();
        // Step 4: Generate codes
        Map<Character,String> codeMap = new HashMap<>();
        generateCodes(root,"",codeMap);
        return codeMap;
    }

    private static void generateCodes(HuffmanNode root, String code, Map<Character,String> codeMap){
        if(root==null) return;
        if(root.left==null && root.right==null){
            codeMap.put(root.ch,code);
            return;
        }
        generateCodes(root.left,code+'0',codeMap);
        generateCodes(root.right,code+'1',codeMap);
    }




}
