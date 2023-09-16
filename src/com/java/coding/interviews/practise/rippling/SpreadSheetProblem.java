package com.java.coding.interviews.practise.rippling;


import java.util.*;

class ExcelCell {

    public String cellName;
    public String value;
    public String expression;
    public boolean isExpression;

    public List<ExcelCell> observers = new ArrayList<>();

    public void addObservers(ExcelCell cell){
        observers.add(cell);
    }

    public ExcelCell(String cellName, String expression) {
        this.cellName = cellName;
        this.expression = expression;
        if(this.expression!=null && this.expression.startsWith("=")){
            this.isExpression=true;
        }else{
            this.value=expression;
        }
    }

    public void updateObservers(Map<String,ExcelCell> cellMap){
        String exp = expression;
        if(isExpression){ //A=1+B
            String[] split = exp.substring(1).split("\\+");
            //System.out.println(Arrays.toString(split));
            for(String s: split){
                if(!Character.isDigit(s.charAt(0)) && s.charAt(0)!='-'){
                    if(!cellMap.containsKey(s)){
                        throw new IllegalStateException("Cell "+s+" not found.");
                    }
                    if(this.observers.contains(cellMap.get(s))){ //A:=A
                        throw new IllegalStateException("Cycle Detected.");
                    }
                    cellMap.get(s).addObservers(this);
                    //System.out.println("OBSERVERS::"+cellMap.get(s).observers);
                }
            }
        }
    }

    public void setValue(Map<String,ExcelCell> cellMap){
        Integer calcValue = 0;
        if(isExpression){
            String[] split = expression.substring(1).split("\\+");
            for(String s: split){
                if(!Character.isDigit(s.charAt(0)) && s.charAt(0)!='-'){
                    ExcelCell cell = cellMap.get(s);
                    if(cell==null){
                        throw new IllegalStateException("Cell Not Found.");
                    }
                    calcValue=calcValue+Integer.parseInt(cell.value);
                }else{
                    calcValue=calcValue+Integer.parseInt(s);
                }
            }
        }else{
            calcValue=Integer.parseInt(expression);
        }
        this.value = String.valueOf(calcValue);
        notifyObservers(cellMap);
    }

    public void notifyObservers(Map<String,ExcelCell> cellMap){
        //System.out.println("Notify::"+cellMap.get(this.cellName).observers);
        for(ExcelCell cell : this.observers){
            //System.out.println("In Loop::"+cell);
            cell.setValue(cellMap);
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
                ", expression='" + expression + '\'' +
                ", isExpression=" + isExpression +
                ", observers=" + observers +
                '}';
    }
}

class ExcelSpreadSheet{

    String sheetName;
    Map<String,ExcelCell> cellMap;

    public ExcelSpreadSheet(String sheetName) {
        this.sheetName = sheetName;
        this.cellMap = new TreeMap<>();
    }

    public void modify(String cellName, String value){
        ExcelCell cell = new ExcelCell(cellName,value);
        checkCycle(cell);
        var excelCell = cellMap.getOrDefault(cellName,new ExcelCell(cellName,value));
        excelCell.expression=value;
        excelCell.cellName=cellName;
        cellMap.put(cellName,excelCell);

        excelCell.updateObservers(cellMap);
        //System.out.println("xxxxxx:::"+excelCell);
        excelCell.setValue(cellMap);

    }

    public void printSheet(){
        for(var entry : cellMap.entrySet()){
            //System.out.println(entry.getValue());
            System.out.println(entry.getKey()+"->"+entry.getValue().expression+"->"+entry.getValue().value);
        }
    }

    public void checkCycle(ExcelCell cell){
        if(cell.expression.contains(cell.cellName)){
            throw new IllegalStateException("Cycle for Cell:"+cell.cellName+" Value:"+cell.expression);
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
                    for(String s:split){
                        if(!Character.isDigit(s.charAt(0))){
                            if(cells.contains(s))
                                throw new IllegalStateException("Cycle for Cell:"+cell.cellName+" Value:"+cell.expression);
                            else
                                cells.add(s);
                        }
                    }
                }else{
                    if(!Character.isDigit(expression.charAt(0))){
                        if(cells.contains(expression))
                            throw new IllegalStateException("Cycle for Cell:"+cell.cellName+" Value:"+cell.expression);
                        else
                            cells.add(expression);
                    }
                }
            }

        }
    }
}


public class SpreadSheetProblem {

    public static void main(String[] args) {
        ExcelSpreadSheet sheet = new ExcelSpreadSheet("Test");
        sheet.modify("A1","1");
        sheet.modify("B1","2");
        //sheet.printSheet();

        sheet.modify("A2","=1+2");
        sheet.modify("B2","=-2+3");
        sheet.modify("A3","=1+A1");
        sheet.modify("A1","2");
        sheet.modify("A4","=A1+A3");
        sheet.modify("A3","=2+A1");

        sheet.printSheet();
    }

}
