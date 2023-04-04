package com.java.coding.interviews.practise.atlassian.snakegame;

public class Game {
    private Snake snake;
    private Board board;
    private boolean isGameOver;
    private int direction;

    private static final int NO_DIRECTION=0;
    private static final int RIGHT_DIRECTION=1;
    private static final int LEFT_DIRECTION=-1;
    private static final int UP_DIRECTION=2;
    private static final int DOWN_DIRECTION=-2;

    public Game(Snake snake, Board board) {
        this.snake = snake;
        this.board = board;
    }

    public void update(){
        if(!isGameOver){
            if(direction!=NO_DIRECTION){
                Cell nextCell = nextCell(snake.getHead());
                if(snake.checkIfCrash(nextCell)){
                    setDirection(NO_DIRECTION);
                    isGameOver=true;
                }else{
                    snake.move(nextCell);
                    if(nextCell.getCellType().equals(CellType.FOOD)){
                        snake.grow();
                    }
                }
            }
        }
    }

    public Cell nextCell(Cell currentPosition){
        int row = currentPosition.getRow();
        int col = currentPosition.getCol();
        if(direction==RIGHT_DIRECTION){
            col++;
        }else if(direction==LEFT_DIRECTION){
            col--;
        }else if(direction==UP_DIRECTION){
            row--;
        }else if(direction==DOWN_DIRECTION){
            row++;
        }
        return new Cell(row,col);
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
