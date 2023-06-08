package com.java.coding.interviews.practise.rippling;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Question:
 *
 * Design a spreadsheet which can support two operations:
 *
 * void set_cell(string cell, string value)
 *
 * int get_cell(string cell)
 *
 * Example:
 * set_cell("A1", "13)
 * set_cell("A2", "14)
 * get_cell("A1") -> 13
 * set_cell("A3", "=A1+A2")
 * get_cell("A3") -> 27
 */
public class ExcelSpreadSheetProblem {

    public static void main(String[] args) {
        SpreadSheet sheet = new SpreadSheet("Test");
        Cell c1 = new Cell("A1",1,null,false);
        Cell c2 = new Cell("A2",2,null,false);
        Cell c3 = new Cell("A3",null,"A1+A2",true);
        Cell c4 = new Cell("A4",null,"A1+A3",true);
        sheet.addCell(c1);
        sheet.addCell(c2);
        sheet.addCell(c3);
        sheet.addCell(c4);
        StringBuilder SB = new StringBuilder();
        sheet.getNormalizedExpression("1+A1-A3+2",SB);
        System.out.println(SB);
    }

}

class SpreadSheet{
    String name;
    Map<String,Cell> cells;

    public SpreadSheet(String name) {
        this.name = name;
        this.cells = new TreeMap<>();
    }

    public void addCell(Cell cell){
        this.cells.put(cell.getName(),cell);
    }

    public Integer getCell(String name){
        Cell cell = cells.get(name);
        if(cell!=null) {
            if (cell.isExpression()) {
                String expression = cell.getExpression().substring(1);
                StringBuilder normalizedExpression = new StringBuilder();
                getNormalizedExpression(expression, normalizedExpression);
            } else{
                return cell.getValue();
            }
        }
        return 0;
    }

    public void getNormalizedExpression(String expression, StringBuilder SB) {
        StringBuilder cellId = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if (isSign(expression.charAt(i))) {
                if (!cells.containsKey(cellId.toString())) {
                    SB.append(cellId).append(expression.charAt(i));
                    cellId.setLength(0);
                } else {
                    if (cells.containsKey(cellId.toString())) {
                        if (cells.get(cellId.toString()).isExpression()) {
                            SB.append("(");
                            getNormalizedExpression(cells.get(cellId.toString()).getExpression(), SB);
                            SB.append(")").append(expression.charAt(i));
                        } else {
                            SB.append(cells.get(cellId.toString()).getValue()).append(expression.charAt(i));
                        }
                        cellId.setLength(0);
                    }
                }
            } else {
                cellId.append(expression.charAt(i));
            }
        }
        if (cellId.length() != 0) {
            if (cells.containsKey(cellId.toString())) {
                if (cells.get(cellId.toString()).isExpression()) {
                    SB.append("(");
                    getNormalizedExpression(cells.get(cellId.toString()).getExpression(), SB);
                    SB.append(")");
                } else {
                    SB.append(cells.get(cellId.toString()).getValue());
                }
            }else{
                SB.append(cellId);
            }
        }
    }
    private boolean isSign(char ch){
        return ch=='+' || ch=='-' || ch=='/' || ch=='*';
    }

    private double evaluateExpression(String expression){
        char[] tokens = expression.toCharArray();
        Stack<Integer> intValue = new Stack<>();
        Stack<Character> operator = new Stack<>();
        for(int i=0;i<tokens.length;i++){
            if(Character.isDigit(tokens[i])){
                StringBuilder SB = new StringBuilder();
                SB.append(tokens[i]);
                while((i+1)<expression.length() && Character.isDigit(expression.charAt(i+1))){
                    SB.append(tokens[++i]);
                }
                intValue.push(Integer.valueOf(SB.toString()));
            }else if(tokens[i]=='('){
                operator.push(tokens[i]);
            }else if(tokens[i]==')'){
                //if()
            }
        }
        return 0d;
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

    private static boolean hasPrecedence(char op1, char op2){
        if(op2=='(' || op2==')')
            return false;
        if((op1=='*' || op1=='/') && (op2=='+' || op2=='-'))
            return false;
        return true;
    }
}

class Cell{
    private String name;
    private Integer value;
    private String expression;
    private boolean isExpression;

    public Cell(String name, Integer value, String expression, boolean isExpression) {
        this.name = name;
        this.value = value;
        this.expression = expression;
        this.isExpression = isExpression;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public String getExpression() {
        return expression;
    }

    public boolean isExpression() {
        return isExpression;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", expression='" + expression + '\'' +
                ", isExpression=" + isExpression +
                '}';
    }
}
