package com.java.coding.interviews.practise.common;

import java.util.*;

public class ExcelSpreadSheetProblem {

    public static void main(String[] args) {
        SpreadSheet sheet = new SpreadSheet("Test");
        sheet.modify("A1","1.2");
        sheet.modify("A2","2");
        sheet.modify("A3","=A1+A2");
        sheet.modify("A1","2.2");
        sheet.display();
    }
}

class Cell{
    String cellName;
    String value;
    String expression;
    boolean isExpression;

    public Cell(String cellName, String expression) {
        this.cellName = cellName;
        this.expression = expression;
        if(this.expression!=null && this.expression.startsWith("=")) {
            this.isExpression = true;
        }else{
            this.value=expression;
        }
    }

    public List<Cell> observers = new ArrayList<>();

    public void addObservers(Cell cell){
        this.observers.add(cell);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "cellName='" + cellName + '\'' +
                ", value='" + value + '\'' +
                ", expression='" + expression + '\'' +
                ", isExpression=" + isExpression +
                ", observers=" + observers +
                '}';
    }

    public void updateObservers(Map<String,Cell> cellMap){
        if(this.isExpression){
            String[] split = this.expression.substring(1).split("\\+");
            for(String s : split) {
                if (!Character.isDigit(s.charAt(0)) || s.charAt(0)!='-'){
                    if(!cellMap.containsKey(s)){
                        throw new IllegalStateException("Cell: "+s+" not found.");
                    }
                    if(this.observers.contains(s)){
                        throw new IllegalStateException("Cycle Detected.");
                    }
                    cellMap.get(s).addObservers(this);
                    System.out.println("OBSERVERS::"+cellMap.get(s).observers);
                }
            }
        }
    }

    public void setValue(Map<String,Cell> cellMap){
        Double calcValue = 0.0;
        if(this.isExpression){
           String[] split = this.expression.substring(1).split("\\+");
           for(String s : split){
               if(!Character.isDigit(s.charAt(0)) || s.charAt(0)!='-'){
                   Cell cell = cellMap.get(s);
                   if(cell==null)
                       throw new IllegalStateException("Cell Not Found.");
                   calcValue = calcValue + Double.parseDouble(cell.value);
               }else{
                   calcValue = calcValue + Double.parseDouble(s);
               }
           }
        }else{
            calcValue = calcValue + Double.parseDouble(expression);
        }
        this.value = String.valueOf(calcValue);
        notifyObservers(cellMap);
    }

    public void notifyObservers(Map<String,Cell> cellMap){
        for(Cell cell : this.observers){
            cell.setValue(cellMap);
        }
    }
}

class SpreadSheet{

    String sheetName;
    Map<String,Cell> cellMap;

    public SpreadSheet(String sheetName) {
        this.sheetName = sheetName;
        this.cellMap = new TreeMap<>();
    }

    public void modify(String cellName,String expression){
        checkCycle(new Cell(cellName, expression));

        Cell cell = cellMap.getOrDefault(cellName,new Cell(cellName,expression));
        cell.expression=expression;
        cell.cellName = cellName;
        cellMap.put(cellName,cell);
        cell.updateObservers(cellMap);
        cell.setValue(cellMap);
    }

    public void display(){
        for(var entry : cellMap.entrySet()){
            System.out.println(entry.getKey()+"->"+entry.getValue().expression+"->"+entry.getValue().value);
        }
    }

    public void checkCycle(Cell cell){
        if(cell.expression.contains(cell.cellName))
            throw new IllegalStateException("Cycle Found");
        Set<String> cellNames = new HashSet<>();
        cellNames.add(cell.expression);
        Iterator<String> iterator = cellNames.iterator();
        while(iterator.hasNext()){
            String expression = iterator.next();
            iterator.remove();
            if(expression.startsWith("=")){
                var exp = expression.substring(1);
                if(exp.contains("+")){
                    String[] split = exp.split("\\+");
                    for(String s : split) {
                        if (!Character.isDigit(s.charAt(0))){
                            if(cellNames.contains(s)){
                                throw new IllegalStateException("Cycle Found");
                            }
                            cellNames.add(s);
                        }
                    }
                }else{
                    if(!Character.isDigit(exp.charAt(0))){
                        if(cellNames.contains(exp))
                            throw new IllegalStateException("Cycle Found");
                        cellNames.add(exp);
                    }
                }
            }
        }
    }
}
