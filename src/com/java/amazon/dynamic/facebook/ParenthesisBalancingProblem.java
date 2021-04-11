package com.java.amazon.dynamic.facebook;

/**
 * This problem was asked by Facebook.
 *
 * Given a string of parentheses, find the balanced string that can be produced from it
 * using the minimum number of insertions and deletions. If there are multiple solutions,
 * return any of them.
 *
 * For example, given "(()", you could return "(())". Given "))()(",
 * you could return "()()()()".
 */

public class ParenthesisBalancingProblem {
    public static void main(String[] args) {
        String result = fixParenthesis("(()");
        System.out.println(result);
        result=fixParenthesis("())()(");
        System.out.println(result);
    }

    public static String fixParenthesis(String S){
        StringBuilder result = new StringBuilder();
        if(S==null || S.length()==0)
            return S;
        int counter=0;
        for(char c : S.toCharArray()){
            if(c=='('){
                result.append("(");
                counter++;
            }else{
                if(counter==0) //No Open Parenthesis to balance this
                {
                    result.append('(');
                }else{
                    counter--;
                }
                result.append(')');
            }
        }
        while(counter-->0){
            result.append(')');
            //counter--;
        }
        return result.toString();
    }
}
