package com.java.coding.interviews.practise.stripe;

import java.util.*;

/**
 * Part 1:
 * The questions was that say you are given a table with rows and columns . Given a column find the row that contains minimum value for that column.
 *
 * for ex:
 * Table = [ { a:1, b : 2, c: 3} , { a : 10}]
 * minBycolumn(Table, "a") -> return { a:1, b : 2, c: 3}
 * minBycolumn(Table, "b") -> return { a : 10} because if a column is not present in a row assume it is 0.
 * So, basically {a : 10} can actually be thought as { a : 10, b : 0, c: 0}
 *
 * since, if we have two rows as of now so you can assume columns are a, b and c
 * However, as you go forward the columns can change.
 * Solution: Save dataset as List of HashMap Basically each row will be represented as a new hasmap and then appended to the List.
 *
 * Part 2:
 * Now, you want to sort by multiple columns. Say for col1 there was a tie then sort by next column and so on.
 *
 * Table : [
 * { x : 1, y : 2, z : 3},
 * { x : 1, y : 2, z : 2},
 * {x : 1, y : 2, z : 4 }
 * ]
 *
 * minByColumn(Table, ["x", "y", "z"]) -> return { x : 1, y : 2, z : 2}
 */
public class TableRowColumnProblem {

    private static List<Row> table = new ArrayList<>();

    public static void main(String[] args) {
        /*addRow("a:1, b : 2, c: 3");
        addRow("a : 10");
        Row row = findRowByColumn("b");
        System.out.println(row);*/
        addRow("x : 1, y : 2, z : 3");
        addRow("x : 1, y : 2, z : 2");
        addRow("x : 1, y : 2, z : 4");
        Row row = findRowByColumn(new String[]{"x", "y", "z"});
        System.out.println(row);
    }

    public static Row findRowByColumn(String column){
        Collections.sort(table, new Comparator<Row>() {
            @Override
            public int compare(Row row, Row t1) {
                return row.columns.getOrDefault(column,0).compareTo(t1.columns.getOrDefault(column,0));
            }
        });
        return table.get(0);
    }

    public static Row findRowByColumn(String[] columns){
        Collections.sort(table, new Comparator<Row>() {
            @Override
            public int compare(Row row, Row t1) {
                for(String column: columns) {
                    int compValue = row.columns.getOrDefault(column, 0).compareTo(t1.columns.getOrDefault(column, 0));
                    if(compValue==0)
                        continue;
                    else
                        return compValue;
                }
                return 0;
            }
        });
        return table.get(0);
    }

    private static void addRow(String row){
        if(row==null || row.length()==0)
            return;
        String[] split = row.split(",");
        Map<String, Integer> M = new HashMap<>();
        for(String R : split){
            R=R.trim();
            M.put(R.substring(0,R.indexOf(":")).trim(),Integer.parseInt(R.substring(R.indexOf(":")+1).trim()));
        }
        table.add(new Row(M));
    }
}

class Row{
    Map<String,Integer> columns;

    public Row(Map<String, Integer> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        StringBuilder cols = new StringBuilder();
        Iterator<String> iterator = this.columns.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            cols.append(key);
            cols.append(":").append(columns.get(key));
            if(iterator.hasNext()){
                cols.append(",");
            }
        }
        return "{" +
                   cols
                +'}';
    }
}

