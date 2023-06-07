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

        }
        return 0d;
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
}
