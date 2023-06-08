package com.java.coding.interviews.practise.rippling;

import java.util.Map;
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
        Cell c1 = new Cell("A1",1d,null,false);
        Cell c2 = new Cell("A2",2d,null,false);
        Cell c3 = new Cell("A3",null,"A1+A2",true);
        Cell c4 = new Cell("A4",null,"A1+A3",true);
        sheet.addCell(c1);
        sheet.addCell(c2);
        sheet.addCell(c3);
        sheet.addCell(c4);
        StringBuilder SB = new StringBuilder();
        sheet.getNormalizedExpression("1+A1+A3",SB);
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

    public Double getCell(String name){
        Cell cell = cells.get(name);
        if(cell.isExpression()){
            String expression = cell.getExpression().substring(1);
            StringBuilder exp = new StringBuilder();
            int prevSignIndex = 0;
            for(int i=0;i<expression.length();i++){
                if(isSign(expression.charAt(i))){

                }
            }
        }
        return 0d;
    }

    public void getNormalizedExpression(String expression, StringBuilder SB){
        int previousIndex=0;
        StringBuilder cellId = new StringBuilder();
        for(int i=0;i<expression.length();i++){
            String cell="";
            if(isSign(expression.charAt(i))){
                SB.append(expression.charAt(i));
                cell = expression.substring(previousIndex, i);
                previousIndex=i+1;

            }
            System.out.println(previousIndex+"--"+i);
            if (!cells.containsKey(cell)) {
                cellId.append(expression.charAt(i));
            }
            System.out.println("CELL ID:--"+cellId);
            if(cells.containsKey(cellId.toString())) {
                if (cells.get(cellId.toString()).isExpression()) {
                    getNormalizedExpression(cells.get(cellId.toString()).getExpression(), SB);
                } else {
                    System.out.println("IN ELSE");
                    SB.append(cells.get(cellId.toString()).getValue());
                }
                cellId = new StringBuilder();
            }
        }

    }

    private boolean isSign(char ch){
        return ch=='+' || ch=='-' || ch=='/' || ch=='*';
    }

    private double evaluateExpression(String expression){
        char[] tokens = expression.toCharArray();
        return 0d;
    }
}

class Cell{
    private String name;
    private Double value;
    private String expression;
    private boolean isExpression;

    public Cell(String name, Double value, String expression, boolean isExpression) {
        this.name = name;
        this.value = value;
        this.expression = expression;
        this.isExpression = isExpression;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
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
