package com.java.coding.interviews.practise.atlassian.snakegame;

public class Game {

    /**
     * Questions: -
     * 1. Is it same mobile Snake game?
     * 2. Do we need to generate food on randomly?
     * 3. Is it on fixed board size like mobile screen?
     * 4. Can Snake grow after going through food cell?
     * 5. I believe Snake can move in 4 direction. Left/Right/Up/Down
     * 6. I believe we need to check snake crashed with itself.
     *
     * Talking Points: -
     * ---------------
     * Set Agenda: -
     * -----------
     * 1. We will create the different classes that represent different objects
     * 2. We will discuss they can interact with through each other using Aggregation/Composition.
     * 3. We will discuss how we can make better by putting proper error check.
     * 4. we will run to see it working.
     *
     */

    private Snake snake;
    private Board board;
    private boolean isGameOver;
    private int direction;

    private static final int NO_DIRECTION=0;
    private static final int LEFT_DIRECTION=-1;
    private static final int RIGHT_DIRECTION=1;
    private static final int UP_DIRECTION=2;
    private static final int DOWN_DIRECTION=-2;

    public Game(Snake snake, Board board) {
        this.snake = snake;
        this.board = board;
    }

    private void update(){
        if(!isGameOver){
            if(direction!=NO_DIRECTION){
                var nextCell = nextCell(snake.getHead());
                var cellType = nextCell.getCellType();
                //System.out.println("Next::"+nextCell);
                if(nextCell.getRow()<0 || nextCell.getRow()>= board.getRowCount() || nextCell.getColumn()<0 || nextCell.getColumn()>= board.getColumnCount() || snake.checkCrash(nextCell)){
                    setDirection(NO_DIRECTION);
                    isGameOver=true;
                }else{
                    snake.move(nextCell);
                    if(cellType.equals(CellType.FOOD)) {
                        snake.grow();
                    }
                    board.printBoard();
                }
            }
        }
    }

    private Cell nextCell(Cell currentPosition){
        int row = currentPosition.getRow();
        int col = currentPosition.getColumn();
        if(direction==LEFT_DIRECTION)
            col--;
        else if(direction==RIGHT_DIRECTION)
            col++;
        else if(direction==UP_DIRECTION)
            row--;
        else if(direction==DOWN_DIRECTION)
            row++;
        //Cell newCell = new Cell(row,col); --Error Check
        return board.getBoard()[row][col];
    }

    public static void main(String[] args) {
        Cell initPosition = new Cell(0,0);
        Snake snake = new Snake(initPosition);
        Board board = new Board(10,10);
        board.printBoard();
        Game newGame = new Game(snake,board);
        newGame.isGameOver=false;
        newGame.direction = RIGHT_DIRECTION;

        System.out.println(snake.getSnakeBody().size());
        for (int i = 0; i < 5; i++) {
            if (i == 0)
                newGame.board.generateFood();
            //newGame.board.printBoard();
            newGame.update();
            System.out.println(snake.getSnakeBody().size());
            if (i == 3)
                newGame.direction = RIGHT_DIRECTION;
            if (newGame.isGameOver())
                break;
        }
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
