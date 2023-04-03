package com.java.coding.interviews.practise.jpmc;

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

public class ParenthesisBalanceProblem {

    public static void main(String[] args) {
        System.out.println(balanceParenthesis("(()"));
        System.out.println(balanceParenthesis("))()("));
    }

    private static String balanceParenthesis(String parenthesis){
        StringBuilder result = new StringBuilder();
        if(parenthesis==null || parenthesis.length()==0)
            return parenthesis;
        int counter=0;
        for(char c : parenthesis.toCharArray()){
            if(c=='('){
                result.append("(");
                counter++;
            }else{
                if(counter>0){
                    counter--;
                }else{
                    result.append("(");
                }
                result.append(")");
            }
        }
        while (counter-->0){
            result.append(")");
        }

        return result.toString();
    }

}
