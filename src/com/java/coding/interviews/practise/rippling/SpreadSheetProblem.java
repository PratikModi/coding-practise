package com.java.coding.interviews.practise.rippling;


import java.util.*;

class ExcelCell {

    public String cellName;
    public String value;
    public boolean isExpression;

    public List<ExcelCell> observers = new ArrayList<>();

    public void addObservers(ExcelCell cell){
        observers.add(cell);
    }

    public ExcelCell(String cellName, String value) {
        this.cellName = cellName;
        this.value = value;
        if(this.value!=null && this.value.startsWith("=")){
            this.isExpression=true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExcelCell excelCell = (ExcelCell) o;
        return Objects.equals(cellName, excelCell.cellName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellName);
    }

    @Override
    public String toString() {
        return "ExcelCell{" +
                "cellName='" + cellName + '\'' +
                ", value='" + value + '\'' +
                ", isExpression=" + isExpression +
                '}';
    }
}

class ExcelSpreadSheet{

    String sheetName;
    Map<String,ExcelCell> cellMap;

    public ExcelSpreadSheet(String sheetName) {
        this.sheetName = sheetName;
        this.cellMap = new HashMap<>();
    }




}


public class SpreadSheetProblem {
}
