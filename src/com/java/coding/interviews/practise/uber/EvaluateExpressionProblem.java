package com.java.coding.interviews.practise.uber;

import java.util.Stack;

public class EvaluateExpressionProblem {

    /*
     * Parse + validate strings for a simple language as follows :
     * input -> "add(1,3)"
     * input -> "sub(1,3)"
     * operations take only 2 params. commands may be nested.
     * ie. add(sub(3,4), 1) sub(add(238943, 2343), add(1, sub(323, 43)))
     * if an error is detected, report the column and what the error was
     */

    /**
     * Questions: --
     * 1. only add/sub operator?
     * 2. Do we need to take care of spaces in between?
     * 3. size of the input?
     * 4. is operator case-sensitive?
     * 5. only positive number?
     * 6. add(1,2)) ,add((1,2)) valid/invalid input?
     * 7. can it have negative number?
     *
     * //Assumption
     * 1. Input is always valid
     */

    public static int evaluate(String expression){
        expression = expression.trim();
        int openParenthesis = -1;
        int closeParenthesis = -1;
        int count = 0;
        int comma=-1;
        for(int i=0;i<expression.length();i++){
            char ch = expression.charAt(i);
            if(ch=='('){
                if(count==0 && openParenthesis==-1)
                    openParenthesis=i;
                count++;
            }else if(ch==')'){
                //System.out.println(count+"-"+i+"-"+ch + "-"+expression.lastIndexOf(')'));
                count--;
                if(count==0 && i==expression.lastIndexOf(')')){
                    closeParenthesis=i;
                }
            }else if(ch==',' && count==1){
                comma=i;
            }
        }
        if(openParenthesis==-1 && closeParenthesis==-1 && comma==-1){
            return Integer.parseInt(expression);
        }
        if(openParenthesis==-1 || closeParenthesis==-1 || comma==-1)
            throw new RuntimeException("Invalid Input");
        String operand = expression.substring(0,openParenthesis);
        int operand1 = evaluate(expression.substring(openParenthesis+1,comma));
        int operand2 = evaluate(expression.substring(comma+1,closeParenthesis));
        if(operand.equals("add")){
            return operand1+operand2;
        }else if(operand.equals("sub")){
            return operand1-operand2;
        }else{
            throw new RuntimeException("Invalid Input");
        }
    }



    public static boolean validate(String expression){
        //add(1,2)
        //System.out.println(expression);
        boolean isValid=true;
        expression = expression.trim();
        int count=0;
        int openParenthesis=-1;
        int closeParenthesis=-1;
        int comma=-1;
        for(int i=0;i<expression.length();i++){
            char ch = expression.charAt(i);
            if(ch=='('){
                if(count==0 && openParenthesis==-1)
                    openParenthesis=i;
                count++;
            }else if(ch==')'){
                count--;
                if(count==0 && i==expression.lastIndexOf(')')){
                    closeParenthesis=i;
                }
            }else if(ch==',' && count==1){
                comma=i;
            }
        }
        if(openParenthesis==-1 && closeParenthesis==-1 && comma==-1){
            isValid&=isNumber(expression);
            return isValid;
        }
        if(openParenthesis==-1 || closeParenthesis==-1 || comma==-1)
            throw new RuntimeException("Invalid Input");
        //System.out.println(closeParenthesis);
        String operand = expression.substring(0,openParenthesis);
        isValid&=operand.equals("add")|| operand.equals("sub")?true:false && count==0 && comma!=-1;
        isValid&=validate(expression.substring(openParenthesis+1,comma));
        isValid&=validate(expression.substring(comma+1,closeParenthesis));

        return isValid;
    }

    public static boolean isNumber(String value){
        try{
            Integer.parseInt(value);
        }catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }

    public static int calculate(String expression){
        char[] tokens = expression.toCharArray();
        Stack<String> operator = new Stack<>();
        Stack<Integer> number = new Stack<>();
        for(int i=0;i< tokens.length;i++){
            if(tokens[i]==' ' || tokens[i]==',')
                continue;
            if(Character.isAlphabetic(tokens[i])){
                StringBuilder operand = new StringBuilder();
                while(i<tokens.length && Character.isAlphabetic(tokens[i])){
                    operand.append(tokens[i++]);
                }
                operator.push(operand.toString());
                //System.out.println(operator);
                i--;
            }else if(Character.isDigit(tokens[i])){
                StringBuilder num = new StringBuilder();
                while(i< tokens.length && Character.isDigit(tokens[i])){
                    num.append(tokens[i++]);
                }
                number.push(Integer.parseInt(num.toString()));
                i--;
            }else if(tokens[i]=='('){
                operator.push("(");
            }else{
                while(operator.peek().equals("(")){
                    operator.pop();
                }
                number.push(apply(operator.pop(),number.pop(),number.pop()));
            }

            //System.out.println(number);
        }
        return number.pop();
    }

    public static boolean validate2(String expression){
        char[] tokens =  expression.toCharArray();
        Stack<String> operators = new Stack<>();
        Stack<String> numbers = new Stack<>();
        Stack<Character> openParenthesis = new Stack<>();
        Stack<Character> comma = new Stack<>();
        int N = tokens.length;
        for(int i=0;i< tokens.length;i++){
            if(tokens[i]!=' '){
                if(Character.isAlphabetic(tokens[i])){
                    int start=i;
                    while(i<N && Character.isAlphabetic(tokens[i])){
                        i++;
                    }
                    operators.push(expression.substring(start,i));
                    i--;
                }else if(Character.isDigit(tokens[i])){
                    int start=i;
                    while(i<N && Character.isDigit(tokens[i])){
                        i++;
                    }
                    numbers.push(expression.substring(start,i));
                    i--;
                }else if(tokens[i]=='('){
                    openParenthesis.push('(');
                }else if(tokens[i]==','){
                    comma.push(',');
                }else{
                    if(!numbers.isEmpty())
                            numbers.pop();
                        else return false;
                        if(!comma.isEmpty())
                            comma.pop();
                        else return false;
                        if(!numbers.isEmpty())
                            numbers.pop();
                        else return false;
                        if(!openParenthesis.isEmpty())
                            openParenthesis.pop();
                        else return false;
                        if(!operators.isEmpty()){
                            operators.pop();
                            numbers.push("999");
                        }
                    }
                }
        }
        if(!openParenthesis.isEmpty() || !comma.isEmpty() || numbers.isEmpty() || !operators.isEmpty())
            return false;

        return true;
    }

    public static int apply(String operation, int b, int a){
        if(operation.equals("add")){
            return a+b;
        }else if(operation.equals("sub")){
            return a-b;
        }else{
            throw new RuntimeException("Invalid Input");
        }
    }


    public static void main(String[] args) {
        /*System.out.println(evaluate("add(1,2)"));
        System.out.println(calculate("add(1,2)"));
        System.out.println(evaluate("sub(add(238943, 2343), add(1, sub(323, 43)))"));
        System.out.println(calculate("sub(add(238943, 2343), add(1, sub(323, 43)))"));*/
        System.out.println(validate2("sub(add(238943, 2343), add(1, sub(323, 43)))"));
        //System.out.println(validate("add(1,add(2))"));
    }

}
