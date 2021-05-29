package com.java.coding.interviews.practise.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II.
 * The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
 * Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine,
 * which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "III"
 * Output: 3
 */


public class RomanToIntegerProblem {
    private static final Map<String,Integer> ROMAN = new HashMap<>();
    static{
        ROMAN.put("I",1);
        ROMAN.put("V",5);
        ROMAN.put("X",10);
        ROMAN.put("L",50);
        ROMAN.put("C",100);
        ROMAN.put("D",500);
        ROMAN.put("M",1000);
        ROMAN.put("IV",4);
        ROMAN.put("IX",9);
        ROMAN.put("XL",40);
        ROMAN.put("XC",90);
        ROMAN.put("CD",400);
        ROMAN.put("CM",900);
    }
    public static void main(String[] args) {
        String roman = "III";
        System.out.println(romanToInteger(roman));
        roman = "LVIII";
        System.out.println(romanToInteger(roman));
    }

    public static int romanToInteger(String roman){
        int sum=0;
        int N = roman.length();
        int i=0;
        while(i<N){
            if(i<N-1){
                String doubleString = roman.substring(i,i+2);
                if(ROMAN.containsKey(doubleString)){
                    sum+=ROMAN.get(doubleString);
                    i+=2;
                    continue;
                }
            }
            String singleString = roman.substring(i,i+1);
            sum+=ROMAN.get(singleString);
            i++;
        }
        return sum;
    }
}
