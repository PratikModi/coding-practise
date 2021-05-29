package com.java.coding.interviews.practise.google;

/**
 * Created by Pratik1 on 25-06-2020.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing of ‘0’, ‘1’ and ‘?’ wildcard characters,
 * generate all binary strings that can be formed by replacing each
 * wildcard character by ‘0’ or ‘1’.
 Example :
 Input str = "1??0?101"
 Output:
 10000101
 10001101
 10100101
 10101101
 11000101
 11001101
 11100101
 11101101
 */
public class BinaryStringPatternProblem {
    public static void main(String[] args) {
        String S="1??0?101";
        System.out.println(generateBinaryString(S));
    }

    public static List<String> generateBinaryString(String S){
        List<String> result = new ArrayList<>();
        if(S==null || S.length()==0)
            return result;
        helper(S.toCharArray(),result,0);
        return result;
    }

    private static void helper(char[] S, List<String> result, int index){
        if(index==S.length){
            result.add(new String(S));
            return;
        }
        if(S[index]=='?'){
            S[index]='0';
            helper(S,result,index+1);
            S[index]='1';
            helper(S,result,index+1);
            S[index]='?';
        }else{
            helper(S,result,index+1);
        }
    }
}
