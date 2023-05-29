package com.java.coding.interviews.practise.uber;

import java.util.*;

/**
 * Remove Invalid Parentheses
 *
 * Solution
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 *
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 *
 * Input: ")("
 * Output: [""]
 */
public class RemoveInvalidParenthesis {
    public static void main(String[] args) {
        System.out.println(removeInvalidParenthesis("()())()"));
        System.out.println(removeInvalidParenthesis("()v)"));
        System.out.println(removeInvalidParenthesis("))(("));
    }

    private static boolean isParenthesis(char c){
        return c=='(' || c==')';
    }

    private static boolean isValidParenthesis(String S){
        if(S==null || S.length()==0)
            return false;
        int count=0;
        for(int i=0;i<S.length();i++){
            if(S.charAt(i)=='(')
                count++;
            else if(S.charAt(i)==')')
                count--;
            if(count<0)
                return false;
        }
        return count==0;
    }

    public static List<String> removeInvalidParenthesis(String S){
        List<String> result = new ArrayList<>();
        if(S==null)
            return result;
        HashSet<String> visited = new HashSet<>();
        Queue<String> Q = new LinkedList<>();
        Q.offer(S);
        visited.add(S);
        String temp;
        boolean level=false;
        while(!Q.isEmpty()){
            S=Q.remove();
            //System.out.println(level);
            if(isValidParenthesis(S)){
                //System.out.println(S+"\n"+level);
                result.add(S);
                level=true;
            }
            if(level)
                continue;
            for(int i=0;i<S.length();i++){
                if(!isParenthesis(S.charAt(i)))
                    continue;
                temp=S.substring(0,i)+S.substring(i+1);
                if(!visited.contains(temp)){
                    Q.add(temp);
                    visited.add(temp);
                }
            }
        }
        return result;
    }

}
