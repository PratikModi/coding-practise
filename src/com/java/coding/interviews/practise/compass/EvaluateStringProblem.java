package com.java.coding.interviews.practise.compass;
/**
 * Evaluate an expression represented by a String. The expression can contain parentheses,
 * you can assume parentheses are well-matched. For simplicity, you can assume only binary operations allowed are +, -, *, and /.
 * Arithmetic Expressions can be written in one of three forms:
 * Infix Notation: Operators are written between the operands they operate on, e.g. 3 + 4.
 * Prefix Notation: Operators are written before the operands, e.g + 3 4
 * Postfix Notation: Operators are written after operands.
 */

import java.util.Stack;

public class EvaluateStringProblem {

    public static int evaluate(String expression){
        if(expression==null || expression.length()==0)
            return -1;
        char[] chars = expression.toCharArray();
        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for(int i=0;i<chars.length;i++){
            //char ch = chars[i];
            if(chars[i]==' ')
                continue;
            if(Character.isDigit(chars[i])){
                StringBuilder SB = new StringBuilder();
                SB.append(chars[i]);
                while((i+1)<chars.length && Character.isDigit(chars[i+1])){
                    SB.append(chars[++i]);
                }
                values.push(Integer.valueOf(SB.toString()));
            }else if(chars[i]=='('){
                operators.push(chars[i]);
            }else if(chars[i]==')'){
                while (operators.peek()!='('){
                    values.push(applyOperation(operators.pop(),values.pop(),values.pop()));
                }
                operators.pop();
            }else if(isSign(chars[i])){
                while(!operators.isEmpty() && hasPrecedence(chars[i],operators.peek())){
                    values.push(applyOperation(operators.pop(),values.pop(),values.pop()));
                }
                operators.push(chars[i]);
            }
        }
        while(!operators.isEmpty()){
            values.push(applyOperation(operators.pop(),values.pop(),values.pop()));
        }
        return values.pop();
   }

   private static boolean isSign(char ch){
        return ch=='+' || ch=='-' || ch=='*' || ch=='/';
   }

   private static boolean hasPrecedence(char op1, char op2){
        if(op2=='(' || op2==')')
            return false;
        if((op1=='*' || op1=='/') && (op2=='+' || op2=='-'))
            return false;
        return true;
   }

   private static int applyOperation(char op,int b, int a){
       switch (op)
       {
           case '+':
               return a + b;
           case '-':
               return a - b;
           case '*':
               return a * b;
           case '/':
               if (b == 0)
                   throw new
                           UnsupportedOperationException(
                           "Cannot divide by zero");
               return a / b;
       }
       return 0;
   }

    public static int evaluateExpression(String expression)
    {
        char[] tokens = expression.toCharArray();

        // Stack for numbers: 'values'
        Stack<Integer> values = new Stack<Integer>();

        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++)
        {

            // Current token is a
            // whitespace, skip it
            if (tokens[i] == ' ')
                continue;

            // Current token is a number,
            // push it to stack for numbers
            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuffer sbuf = new StringBuffer();

                // There may be more than one
                // digits in number
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') {
                    sbuf.append(tokens[i++]);
                }
                values.push(Integer.parseInt(sbuf.toString()));
                i--;
            }

            // Current token is an opening brace,
            // push it to 'ops'
            else if (tokens[i] == '(')
                ops.push(tokens[i]);

                // Closing brace encountered,
                // solve entire brace
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop();
            }

            // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/')
            {
                while (!ops.empty() && hasPrecedence(tokens[i],ops.peek())) {
                    values.push(applyOp(ops.pop(),values.pop(),values.pop()));
                }
                ops.push(tokens[i]);
            }
        }

        while (!ops.empty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        // Top of 'values' contains
        // result, return it
        return values.pop();
    }
    /*public static boolean hasPrecedence(
            char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') &&
                (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }*/

    // A utility method to apply an
    // operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static int applyOp(char op,
                              int b, int a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException(
                            "Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

    // Driver method to test above methods
    public static void main(String[] args)
    {
        System.out.println(EvaluateStringProblem.evaluate("1+1-(1+2)+2"));
        System.out.println(EvaluateStringProblem.evaluate("10 + 2 * 6"));
        System.out.println(EvaluateStringProblem.evaluateExpression("10 + 2 * 6"));
        System.out.println(EvaluateStringProblem.evaluate("100 * 2 + 12"));
        System.out.println(EvaluateStringProblem.evaluateExpression("100 * 2 + 12"));
        System.out.println(EvaluateStringProblem.evaluate("100 * ( 2 + 12 )"));
        System.out.println(EvaluateStringProblem.evaluateExpression("100 * ( 2 + 12 )"));
        System.out.println(EvaluateStringProblem.evaluate("100 * ( 2 + 12 ) / 14"));
        System.out.println(EvaluateStringProblem.evaluateExpression("100 * ( 2 + 12 ) / 14"));
        System.out.println(EvaluateStringProblem.evaluate("1-(433-6652)+74"));
        System.out.println(EvaluateStringProblem.evaluateExpression("1-(433-6652)+74"));
    }
}