package com.java.coding.interviews.practise.second;


import java.util.*;

class Cell{
    String cellName;
    String value;
    String expression;
    boolean isExpression;

    List<Cell> observers = new ArrayList<>();

    public void addObserver(Cell cell){
        observers.add(cell);
    }

    public Cell(String cellName, String expression) {
        this.cellName = cellName;
        this.expression = expression;
        if(this.expression!=null && this.expression.startsWith("=")){
            this.isExpression=true;
        }else{
            this.value=expression;
        }
    }

    public void updateObservers(Map<String,Cell> cellMap){
        String exp = this.expression;
        if(isExpression){
            String[] split = exp.substring(1).split("\\+");
            for(String s : split){
                if(!Character.isDigit(s.charAt(0)) && s.charAt(0)!='-'){
                    if(!cellMap.containsKey(s)){
                        throw new IllegalArgumentException("Cell Not Found");
                    }
                    if(this.observers.contains(s)){
                        throw new IllegalStateException("Cycle Detected.");
                    }
                    cellMap.get(s).addObserver(this);
                }
            }
        }
    }

    public void notifyObservers(Map<String,Cell> cellMap){
        for(Cell cell : this.observers){
            cell.setValue(cellMap);
        }
    }
    public void setValue(Map<String,Cell> cellMap){
        Double calcValue=0.0;
        if(isExpression){
            String[] split = expression.substring(1).split("\\+");
            for(String s : split){
                if(!Character.isDigit(s.charAt(0)) && s.charAt(0)!='-'){
                    Cell cell = cellMap.get(s);
                    if(cell==null){
                        throw new IllegalArgumentException("Cell Not Found");
                    }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return Objects.equals(cellName, cell.cellName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellName);
    }
}

class SpreadSheet{
    String sheetName;
    Map<String,Cell> cellMap;

    public SpreadSheet(String sheetName) {
        this.sheetName = sheetName;
        this.cellMap = new TreeMap<>();
    }

    public void modify(String cellName, String expression){
        Cell temp = new Cell(cellName, expression);
        checkCycle(temp);
        Cell cell = cellMap.get(cellName);
        cell.cellName=cellName;
        cell.expression = expression;
        cellMap.put(cellName,cell);
        cell.updateObservers(cellMap);
        cell.setValue(cellMap);
    }

    public void checkCycle(Cell cell){

        if(cell.expression.contains(cell.cellName)){
            throw new IllegalStateException("Cycle Found.");
        }
        Set<String> cells = new HashSet<>();
        cells.add(cell.expression);
        Iterator<String> iterator = cells.iterator();
        while(iterator.hasNext()){
            var value = iterator.next();
            iterator.remove();
            if(value.startsWith("=")){
                String expression = value.substring(1);
                if(expression.contains("+")){
                    String[] split = expression.split("\\+");
                    for(String s : split){
                        if(!Character.isDigit(s.charAt(0))){
                            if(cells.contains(s)){
                                throw new IllegalStateException("Cycle Found");
                            }else{
                                cells.add(s);
                            }
                        }
                    }
                }else{
                    if(!Character.isDigit(expression.charAt(0))){
                        throw new IllegalStateException("Cycle Found");
                    }else{
                        cells.add(expression);
                    }
                }
            }
        }
    }
}


public class ExcelSpreadSheetProblem {

    public static void main(String[] args) {

    }

}
