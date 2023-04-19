package com.java.coding.interviews.practise.bloomberg;

import java.util.Stack;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
 * so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 */
public class MinimumRemovalParenthesisProblem {

    public static void main(String[] args) {
        System.out.println(removeInvalidParenthesis("lee(t(c)o)de)"));
        System.out.println(removeInvalidParenthesis("a)b(c)d"));
        System.out.println(removeInvalidParenthesis("))(("));
    }

    public static String removeInvalidParenthesis(String S){
        StringBuilder result = new StringBuilder();
        if(S==null || S.length()==0)
            return result.toString();
        Stack<Integer> stack = new Stack<>();
        char[] chars = S.toCharArray();
        for(int i=0;i<chars.length;i++){
            char c = chars[i];
            if(c=='(' || c==')'){
                if(c=='('){
                    stack.push(i);
                }else if(c==')' && !stack.isEmpty()){
                    stack.pop();
                }else{
                    chars[i]='@';
                }
            }
        }
        while(!stack.isEmpty()){
            chars[stack.pop()]='@';
        }
        for(int i=0;i<chars.length;i++){
            if(chars[i]!='@'){
                result.append(chars[i]);
            }
        }

        return result.toString();
    }

}
