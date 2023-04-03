package com.java.coding.interviews.practise.jpmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneLetterCombinationProblem {
    public static void main(String[] args) {
        System.out.println(findCombination("23"));
    }

    private static List<String> findCombination(String input){
        List<String> result = new ArrayList<>();
        Map<Character,String> letters = new HashMap<>();
        letters.put('2',"abc");
        letters.put('3',"def");
        letters.put('4',"ghi");
        letters.put('5',"jkl");
        letters.put('6',"mno");
        letters.put('7',"pqrs");
        letters.put('8',"tuv");
        letters.put('9',"wxyz");
        StringBuilder combination = new StringBuilder();
        if(input.length()==1 || input.charAt(0)=='1'){
            return result;
        }
        helper(letters,result,combination,0,input);
        return result;
    }


    private static void helper(Map<Character,String> letters, List<String> result, StringBuilder combination, int index, String input){
        if(index==input.length()){
            result.add(combination.toString());
            return;
        }
        String temp = letters.get(input.charAt(index));
        for(int i=0;i<temp.length();i++){
            combination.append(temp.charAt(i));
            helper(letters,result,combination,index+1,input);
            combination.setLength(combination.length()-1);
        }

    }




}
