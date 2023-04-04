package com.java.coding.interviews.practise.atlassian.snakegame;

public class Board {
    private int rowCount;
    private int colCount;
    private Cell[][] board;

    public Board(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.board = new Cell[rowCount][colCount];
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<colCount;j++){
                board[i][j] = new Cell(i,j);
                board[i][j].setCellType(CellType.EMPTY);
            }
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    public void setColCount(int colCount) {
        this.colCount = colCount;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }
}
