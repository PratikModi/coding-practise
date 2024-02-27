package com.java.coding.interviews.practise.atlassian.snakegame;

public class Board {

    private int rowCount;
    private int columnCount;
    private Cell[][] board;

    public Board(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.board = new Cell[rowCount][columnCount];
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<columnCount;j++){
                board[i][j] = new Cell(i,j);
                board[i][j].setCellType(CellType.EMPTY);
            }
        }
    }

    public void generateFood(){
        int row=0, column=0;
        while(true){
            row = (int)(Math.random()*rowCount);
            column = (int)(Math.random()*column);
            if(board[row][column].getCellType()!=CellType.SNAKE)
                break;
        }
        board[row][column].setCellType(CellType.FOOD);
        board[0][1].setCellType(CellType.FOOD);
        System.out.println("Food Generate at row:"+row+"  column:"+column);
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

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public void printBoard(){
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<columnCount;j++){
                System.out.print(board[i][j].getCellType()+"|");
            }
            System.out.println();
        }
        //Arrays.stream(board).forEach(e-> System.out.println(Arrays.toString(e)));
    }


}
