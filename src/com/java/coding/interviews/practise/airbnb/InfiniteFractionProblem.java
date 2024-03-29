package com.java.coding.interviews.practise.airbnb;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode – Fraction to Recurring Decimal (Java)
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 *
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * For example,
 *
 *
 * Given numerator = 1, denominator = 2, return "0.5".
 * Given numerator = 2, denominator = 1, return "2".
 * Given numerator = 2, denominator = 3, return "0.(6)".
 */
public class InfiniteFractionProblem {

    public static void main(String[] args) {
        System.out.println(fractionToDecimal(-1,-2147483648));
        System.out.println(fractionToDecimal_2(-1,-2147483648));
    }


    public static String fractionToDecimal_2(int numerator, int denominator) {

        StringBuilder ans = new StringBuilder();
        if(numerator==0)
            return "0";
        if ((numerator < 0) ^ (denominator < 0)) {
            ans.append("-");
        }

        long num = numerator,den = denominator;
        num = Math.abs(num);
        den = Math.abs(den);
        long q = num/den;
        long r = num % den;
        ans.append(q);
        if(r==0)
            return ans.toString();
        ans.append(".");
        //System.out.println(ans);
        Map<Long,Integer> map = new HashMap();
        while(r!=0){
            //System.out.println(r);
            if(map.containsKey(r)){
                ans.insert(map.get(r),"(");
                ans.append(")");
                break;
            }else{
                map.put(r,ans.length());
                r*=10;
                q=r/den;
                r=r%den;
                ans.append(q);
            }
        }
        return ans.toString();
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)
            return "0";
        if (denominator == 0)
            return "";

        String result = "";

        // is result is negative
        if ((numerator < 0) ^ (denominator < 0)) {
            result += "-";
        }

        // convert int to long
        long num = numerator, den = denominator;
        num = Math.abs(num);
        den = Math.abs(den);

        // quotient
        long res = num / den;
        result += String.valueOf(res);

        // if remainder is 0, return result
        long remainder = (num % den) * 10;
        if (remainder == 0)
            return result;

        // right-hand side of decimal point
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        result += ".";
        //System.out.println("REMINDER-->"+remainder);
        while (remainder != 0) {
            // if digits repeat
            if (map.containsKey(remainder)) {
                //System.out.println(remainder);
                int beg = map.get(remainder);
                String part1 = result.substring(0, beg);
                String part2 = result.substring(beg, result.length());
                result = part1 + "(" + part2 + ")";
                return result;
            }

            // continue
            map.put(remainder, result.length());
            //System.out.println(map);
            res = remainder / den;
            //System.out.println("RES:-->"+res);
            result += String.valueOf(res);
            //System.out.println("RESULT:-->"+result);
            remainder = (remainder % den) * 10;
        }

        return result;
    }
}
