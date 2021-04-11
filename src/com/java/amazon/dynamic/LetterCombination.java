package com.java.amazon.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pratik1 on 08-03-2020.
 */
public class LetterCombination {

    public static List<String> getCombinations(String digits){
        List<String> result = new ArrayList<>();
        Map<Character,String> LETTERS= new HashMap<>();
        LETTERS.put('2',"abc");
        LETTERS.put('3',"def");
        LETTERS.put('4',"ghi");
        LETTERS.put('5',"jkl");
        LETTERS.put('6',"mno");
        LETTERS.put('7',"pqrs");
        LETTERS.put('8',"tuv");
        LETTERS.put('9',"xyz");
        if(digits.length()==1 && digits.charAt(0)=='1')
            return result;
        StringBuilder sb = new StringBuilder();
        letterCombination(LETTERS,digits,0,result,sb);
        return result;
    }

    private static void letterCombination(Map<Character,String> LETTERS,String digits,int index, List<String> result, StringBuilder combination){
        if(index==digits.length()) {
            result.add(combination.toString());
            return;
        }
        String temp = LETTERS.get(digits.charAt(index));
        for(int i=0;i<temp.length();i++){
            combination.append(temp.charAt(i));
            letterCombination(LETTERS,digits,index+1,result,combination);
            combination.setLength(combination.length()-1);
        }
    }


    public static void main(String[] args) {
        System.out.println(getCombinations("23"));
    }
}
