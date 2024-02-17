package com.java.coding.interviews.practise.second.uber;

import java.util.Stack;

public class ExpressionEvaluationProblem {

    public static int evaluate(String expression){
        if(expression==null || expression.length()==0)
            return -1;
        expression = expression.trim();
        int count=0;
        int openParenthesis=-1;
        int closeParenthesis=-1;
        int comma =-1;
        int n = expression.length();
        for(int i=0;i<n;i++) {
            char ch = expression.charAt(i);
            if (ch != ' ') {
                if (ch == '(') {
                    if (count == 0 && openParenthesis == -1) {
                        openParenthesis = i;
                    }
                    count++;
                } else if (ch == ',') {
                    if (count == 1) {
                        comma = i;
                    }
                } else if (ch == ')') {
                    count--;
                    if (count == 0) {
                        closeParenthesis = i;
                    }
                }
            }
        }
        if(openParenthesis==-1 && comma==-1 && closeParenthesis==-1)
            return Integer.parseInt(expression);
        String operand = expression.substring(0,openParenthesis);
        int operand1 = evaluate(expression.substring(openParenthesis+1,comma));
        int operand2 = evaluate(expression.substring(comma+1,closeParenthesis));
        if(operand.equals("add"))
            return operand1+operand2;
        else if(operand.equals("sub"))
            return operand1-operand2;
        else throw new RuntimeException("Invalid Expression!!");
    }

    public static int calculate(String expression){
        if(expression==null || expression.length()==0)
            return -1;
        Stack<String> operator = new Stack<>();
        Stack<Integer> number = new Stack<>();
        char[] tokens = expression.toCharArray();
        int n = tokens.length;
        for(int i=0;i<n;i++) {
            if (tokens[i] != ' ' && tokens[i] != ',') {
                if (Character.isDigit(tokens[i])) {
                    int start = i;
                    while (i < n && Character.isDigit(tokens[i])) {
                        i++;
                    }
                    number.push(Integer.parseInt(expression.substring(start, i)));
                    i--;
                } else if (Character.isAlphabetic(tokens[i])) {
                    int start = i;
                    while (i < n && Character.isAlphabetic(tokens[i])) {
                        i++;
                    }
                    operator.push(expression.substring(start, i));
                    i--;
                } else if (tokens[i] == '(') {
                    operator.push("(");
                } else {
                    operator.pop();
                    number.push(apply(operator.pop(), number.pop(), number.pop()));
                }
            }
        }
        return number.peek();
    }

    private static int apply(String operation, int operand2, int operand1){
        if(operation.equals("add"))
            return operand1+operand2;
        else if(operation.equals("sub"))
            return operand1-operand2;
        else throw new RuntimeException("Invalid Input!!");
    }

    public static boolean validate(String expression){
        if(expression==null || expression.length()==0)
            return false;
        Stack<String> operators = new Stack<>();
        Stack<Character> openParenthesis = new Stack<>();
        Stack<String> number = new Stack<>();
        Stack<Character> comma = new Stack<>();
        char[] tokens = expression.toCharArray();
        int n = tokens.length;
        for(int i=0;i<tokens.length;i++){
            if(tokens[i]!=' '){
                if(Character.isAlphabetic(tokens[i])){
                    int start=i;
                    while(i<n && Character.isAlphabetic(tokens[i]))
                        i++;
                    operators.push(expression.substring(start,i));
                    i--;
                }else if(Character.isDigit(tokens[i])){
                    int start=i;
                    while(i<n && Character.isDigit(tokens[i]))
                        i++;
                    number.push(expression.substring(start,i));
                    i--;
                }else if(tokens[i]==','){
                    comma.push(',');
                }else if(tokens[i]=='('){
                    openParenthesis.push('(');
                }else{
                    if(!number.isEmpty())
                        number.pop();
                    else return false;
                    if(!comma.isEmpty())
                        comma.pop();
                    else return false;
                    if(!number.isEmpty())
                        number.pop();
                    else return false;
                    if(!openParenthesis.isEmpty())
                        openParenthesis.pop();
                    else return false;
                    if(!operators.isEmpty()) {
                        operators.pop();
                        number.push("999");
                    }
                    else return false;
                }
            }
        }
        if(number.peek().equals("999"))
            number.pop();
        if(!number.isEmpty() || !openParenthesis.isEmpty() || !comma.isEmpty() || !operators.isEmpty())
            return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(evaluate("sub(add(238943, 2343), add(1, sub(323, 43)))"));
        System.out.println(evaluate("add(1,2)"));
        System.out.println(calculate("sub(add(238943, 2343), add(1, sub(323, 43)))"));
        System.out.println(calculate("add(1,2)"));
        System.out.println(validate("sub(add(238943, 2343), add(1, sub(323, 43)))"));
        System.out.println(validate("add(1,2)"));
        System.out.println(validate("sub(add(238943,, 2343), add(1, sub(323, 43)))"));
        System.out.println(validate("add(1,2))"));
    }

}
