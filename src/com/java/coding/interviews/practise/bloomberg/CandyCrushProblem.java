package com.java.coding.interviews.practise.bloomberg;

import java.util.Stack;

/**
 * Write a function to crush candy in one dimensional board. In candy crushing games, groups of like items are removed from the board. In this problem, any sequence of 3 or more like items should be removed and any items adjacent to that sequence should now be considered adjacent to each other. This process should be repeated as many time as possible. You should greedily remove characters from left to right.
 *
 * Example 1:
 *
 * Input: "aaabbbc"
 * Output: "c"
 * Explanation:
 * 1. Remove 3 'a': "aaabbbbc" => "bbbbc"
 * 2. Remove 4 'b': "bbbbc" => "c"
 *
 * Example 2:
 *
 * Input: "aabbbacd"
 * Output: "cd"
 * Explanation:
 * 1. Remove 3 'b': "aabbbacd" => "aaacd"
 * 2. Remove 3 'a': "aaacd" => "cd"
 *
 * Example 3:
 *
 * Input: "aabbccddeeedcba"
 * Output: ""
 * Explanation:
 * 1. Remove 3 'e': "aabbccddeeedcba" => "aabbccdddcba"
 * 2. Remove 3 'd': "aabbccdddcba" => "aabbcccba"
 * 3. Remove 3 'c': "aabbcccba" => "aabbba"
 * 4. Remove 3 'b': "aabbba" => "aaa"
 * 5. Remove 3 'a': "aaa" => ""
 *
 * Example 4:
 *
 * Input: "aaabbbacd"
 * Output: "acd"
 * Explanation:
 * 1. Remove 3 'a': "aaabbbacd" => "bbbacd"
 * 2. Remove 3 'b': "bbbacd" => "acd"
 */
public class CandyCrushProblem {

    public static void main(String[] args) {
        System.out.println(crushCandies("aaabbbc"));
        System.out.println(crushCandies("aabbbacd"));
        System.out.println(crushCandies("aabbccddeeedcba"));
        System.out.println(crushCandies("aaabbbacd"));
    }

    private static String crushCandies(String S){
        if(S==null || S.length()==0)
            return S;
        Stack<Character> characters = new Stack<>();
        Stack<Integer> occurrences = new Stack<>();

        for(int i=0;i<S.length();){
            char ch = S.charAt(i);
            if(characters.isEmpty() || characters.peek()!=ch){
                if(!characters.isEmpty() && occurrences.peek()>=3){
                    characters.pop();
                    occurrences.pop();
                }else{
                    characters.push(ch);
                    occurrences.push(1);
                    i++;
                }
            }else{
                int count = occurrences.pop();
                occurrences.push(count+1);
                i++;
            }
        }

        if(occurrences.peek()>=3){
            occurrences.pop();
            characters.pop();
        }

        StringBuilder result = new StringBuilder();
        while(!characters.isEmpty()){
            char ch = characters.pop();
            int count = occurrences.pop();
            while (count-->0){
                result.append(ch);
            }
        }

        return result.reverse().toString();

    }

}
