package com.java.coding.interviews.practise.jpmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Letter Combinations of a Phone Number
 * Medium
 * 14.8K
 * 838
 * Companies
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 *
 *
 *
 * Example 1:
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 *
 * Input: digits = ""
 * Output: []
 * Example 3:
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 */

//Time Complexity : O(4^N)
public class PhoneNumberToWordProblem {

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> words = new ArrayList<>();
        if(digits==null || digits.length()==0)
            return words;
        if(digits.length()==1 && digits.charAt(0)=='1'){
            return words;
        }
        Map<Character,String> letters = new HashMap<>();
        letters.put('2',"abc");
        letters.put('3',"def");
        letters.put('4',"ghi");
        letters.put('5',"jkl");
        letters.put('6',"mno");
        letters.put('7',"pqrs");
        letters.put('8',"tuv");
        letters.put('9',"xyz");

        StringBuilder SB = new StringBuilder();
        recursion(words,digits,0,SB,letters);
        return words;
    }

    private static void recursion(List<String> words, String digits, int index, StringBuilder SB, Map<Character, String> letters){
        if(index==digits.length()){
            words.add(SB.toString());
            return;
        }
        String temp = letters.get(digits.charAt(index));
        for(int i=0;i<temp.length();i++){
            SB.append(temp.charAt(i));
            recursion(words,digits,index+1,SB,letters);
            SB.setLength(SB.length()-1);
        }
    }

}
