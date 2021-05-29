package com.java.coding.interviews.practise.amazon;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead,
 * the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine,
 * which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 3
 * Output: "III"
 * Example 2:
 *
 * Input: num = 4
 * Output: "IV"
 * Example 3:
 *
 * Input: num = 9
 * Output: "IX"
 * Example 4:
 *
 * Input: num = 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 * Example 5:
 *
 * Input: num = 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 *
 * Constraints:
 *
 * 1 <= num <= 3999
 */
public class IntegerToRomanProblem {
    public static void main(String[] args) {
        System.out.println(convertIntegerToRoman(1994));
        System.out.println(convertIntegerToRoman(58));
        System.out.println(convertIntegerToRoman(1990));
    }

    public static String convertIntegerToRoman(int num){
        String result = "";
        if(num==0)
            return result;
        List<Numerals> numeralsList = Arrays.asList(
                new Numerals("I",1),
                new Numerals("IV",4),
                new Numerals("V",5),
                new Numerals("IX",9),
                new Numerals("X",10),
                new Numerals("XL",40),
                new Numerals("L",50),
                new Numerals("XC",90),
                new Numerals("C",100),
                new Numerals("CD",400),
                new Numerals("D",500),
                new Numerals("CM",900),
                new Numerals("M",1000)
        );
        Collections.sort(numeralsList,(n1,n2)->n2.value-n1.value);
        for(Numerals numerals : numeralsList){
            int noOfTimes = num/numerals.value;
            if(noOfTimes!=0) {
                result += numerals.symbol.repeat(noOfTimes);
                num%=numerals.value;
            }
        }
        return result;
    }
}

class Numerals{
    String symbol;
    int value;

    public Numerals(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Numerals{" +
                "symbol='" + symbol + '\'' +
                ", value=" + value +
                '}';
    }
}
