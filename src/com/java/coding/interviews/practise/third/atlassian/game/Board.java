package com.java.coding.interviews.practise.third.atlassian.game;

public class Board {

    private Cell[][] cells;
    private int rowCount;
    private int columnCount;

    public Board(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.cells = new Cell[rowCount][columnCount];
        initiate(rowCount,columnCount);
    }

    private void initiate(int rowCount, int columnCount){
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<columnCount;j++){
                cells[i][j] = new Cell(i,j);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
}
