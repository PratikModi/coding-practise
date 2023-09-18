package com.java.coding.interviews.practise.second;


class Cell{
    String cellName;
    String value;
    String expression;
    boolean isExpression;

    public Cell(String cellName, String expression) {
        this.cellName = cellName;
        this.expression = expression;
        if(this.expression!=null && this.expression.startsWith("=")){
            this.isExpression=true;
        }else{
            this.value=expression;
        }
    }
}


public class ExcelSpreadSheetProblem {

    public static void main(String[] args) {

    }

}
