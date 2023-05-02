package com.java.coding.interviews.practise.jpmc;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 */

public class GenerateParenthesisProblem {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int N){
        List<String> result = new ArrayList<>();
        generate(result,N,N,"");
        return result;
    }

    private static void generate(List<String> result, int open, int close,String parenthesis){
        if(open==0 && close==0) {
            result.add(parenthesis);
            return;
        }
        if(open>0){
            generate(result,open-1,close,parenthesis+"(");
        }
        if(close>open){
            generate(result,open,close-1,parenthesis+")");
        }
    }

}
