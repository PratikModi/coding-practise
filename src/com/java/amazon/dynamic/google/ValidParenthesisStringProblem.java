package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 06-06-2020.
 */

import java.util.Stack;

/**
 *You're given a string consisting solely of (, ), and *. * can represent either a (, ),
 * or an empty string. Determine whether the parentheses are balanced.
 * For example, (()* and (*) are balanced. )*( is not balanced.
 *=======================================================================================
 * Given a string containing only three types of characters: '(', ')' and '*',
 * write a function to check whether this string is valid.
 * We define the validity of a string by these rules:
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a
 * single left parenthesis '(' or an empty string.
 * An empty string is also valid.
 */
public class ValidParenthesisStringProblem {

    public static void main(String[] args) {
        /*String input ="(*))";
        System.out.println(validParenthesisString(input));*/
        String input = "((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()";
        System.out.println(validParenthesisString(input));
    }

    public static boolean validParenthesisString(String input){
        if(input==null || input.length()==0)
            return false;
        Stack<Integer> open = new Stack<>();
        Stack<Integer> star = new Stack<>();
        int i=0;
        int N=input.length();
        while(i<N){
            if(input.charAt(i)=='('){
                open.push(i++);
            }else if(input.charAt(i)=='*'){
                star.push(i++);
            }else{
                if(!open.isEmpty()){
                    open.pop();
                }else if(!star.isEmpty()){
                    star.pop();
                }else{
                    return false;
                }
                i++;
            }
        }
        System.out.println(open);
        System.out.println(star);
        while (!open.isEmpty()){
            if(star.isEmpty()) {
                return false;
            }else{
             if(star.peek()>open.peek()){
                 star.pop();
                 open.pop();
             }else if(star.peek()<open.peek()){
                 star.pop();
             }else{
                 return false;
             }
            }
        }
        return true;
    }
}
