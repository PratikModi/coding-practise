package com.java.coding.interviews.practise.third.atlassian.game;

/**
 * Questions:-
 * ========
 * Some Clarification
 * --------------------
 * 1. I believe snake can move in 4 directions Left/Right/Up/Down.
 * 2. We need to take care of snake movement along with crashing at the boundaries of the board and also with crashing itself.
 * 3. Do we need to generate food?
 * 4. Snake can grow after eating food?
 *
 * Talking Points:
 * ===========
 * 1. This is kind of low level code design, so we will define the different class defining different objects of the snake game.
 * 2. We will also define how these different objects will interact with each other.
 * 3. We will also define the functions that each object will be responsible for.
 *
 * Game --- this is object that will represent the entire Snake Game Instance.
 * ====
 *  ---This object will contain below objects.
 *       Board -- This object will represent the board like mobile screen on which Snake can move.
 *               --This object will contain below objects.
 *               Cell -- List of cells. Each cell represents the single cell on the board.
 *                     -- each Cell will have a position in terms of row and column
 *                     -- each Cell will also have a type like it's EMPTY,SNAKE,FOOD
 *                     CellType -- this is enum that represents different types of Cell.
 *               Board Function:-
 *               ===========
 *               initiate() -- This will initiate the board.
 *               generateFood() -- This will generate food on random cells.
 *       Snake -- This object will represent Snake of the game.
 *                --  Snake can have a body which can be represented as LinkedList of Cell
 *                -- Snake can also have a head which is again Cell. This head will be used to find the next cell while moving the cell in any given direction.
 *                Snake Functions:-
 *                =============
 *                move(Cell nextCell) -- moves the snake to next position.
 *                checkIfCrash(Cell nextCell) -- check if Snake is crashing.
 *       Direction -- we can represent the direction with Enum LEFT/RIGHT/UP/DOWN
 *       Boolean -- this will be boolean attribute that will indicate if game is over or not.
 *       Game Functions:-
 *       ============
 *        update() -- Update game based on next cell based on direction. It will initiate snake move to next cell also checks if snake is clashing.
 *        nextCell(Cell current) -- Based on direction it gives next cell on the board where Snake can move.
 *        start() -- this will initiate the Game object so that game can be started.
 *
 */
public class Game {

    private Board board;
    private Snake snake;
    private Direction direction;
    private boolean isGameOver;

    public Game(Board board, Snake snake) {
        this.board = board;
        this.snake = snake;
        this.direction = Direction.NO_DIRECTION;
    }

    public void update(){
        if(!isGameOver){
            if(direction!=Direction.NO_DIRECTION){
                Cell nextCell = getNextCell(snake.getHead());
                if(nextCell!=null){
                    CellType cellType = nextCell.getCellType();
                    if(!snake.checkIfCrash(nextCell)){
                        snake.move(nextCell);
                        if(cellType.equals(CellType.FOOD))
                            snake.grow();
                        nextCell.setCellType(CellType.SNAKE);
                    }else{
                        isGameOver=true;
                        direction=Direction.NO_DIRECTION;
                    }
                }else{
                    isGameOver=true;
                    direction=Direction.NO_DIRECTION;
                }
            }
        }
    }

    private Cell getNextCell(Cell current){
        int row = current.getRow();
        int col = current.getColumn();
        if(direction==Direction.LEFT){
            col--;
        }else if(direction==Direction.RIGHT){
            col++;
        }else if(direction==Direction.UP){
            row--;
        }else if(direction==Direction.DOWN){
            row++;
        }
        if(!board.isCrashingOnBoundary(row,col)){
            return board.getCells()[row][col];
        }
        return null;
    }


}
