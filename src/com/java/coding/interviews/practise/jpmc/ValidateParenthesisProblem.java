package com.java.coding.interviews.practise.jpmc;

import java.util.*;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: true
 * Example 2:
 *
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: s = "(]"
 * Output: false
 */

public class ValidateParenthesisProblem {

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
    }

    private static boolean isValid(String parenthesis){
        boolean isValid=false;
        Set<Character> openParenthesis = new HashSet<>();
        openParenthesis.add('(');
        openParenthesis.add('{');
        openParenthesis.add('[');

        Map<Character,Character> closeParenthesis = new HashMap<>();
        closeParenthesis.put(')','(');
        closeParenthesis.put('}','{');
        closeParenthesis.put(']','[');

        Stack<Character> stack = new Stack<>();
        int index=-1;

        for(char c : parenthesis.toCharArray()){
            index++;
            if(stack.isEmpty() && !openParenthesis.contains(c)){
                System.out.println("Wrong Parenthesis at Index::"+index);
                return false;
            }
            if(openParenthesis.contains(c)){
                stack.push(c);
            }else if(stack.peek() == closeParenthesis.get(c)){
                stack.pop();
                isValid=true;
            }else{
                System.out.println("Wrong Parenthesis at Index::"+index);
                return false;
            }
        }
        if(!stack.isEmpty()){
            isValid=false;
        }
        return isValid;
    }



}
