package com.java.coding.interviews.practise.atlassian.snakegame;

import java.util.LinkedList;

public class Snake {
    private Cell head;
    private LinkedList<Cell> snakeBody;

    public Snake(Cell initPos) {
        this.head = initPos;
        snakeBody = new LinkedList<>();
        snakeBody.add(head);
        head.setCellType(CellType.SNAKE_NODE);
    }

    public void grow(){
        snakeBody.add(head);
    }

    public void move(Cell nextCell){
        Cell tail = snakeBody.removeLast();
        tail.setCellType(CellType.EMPTY);
        head = nextCell;
        head.setCellType(CellType.SNAKE_NODE);
        snakeBody.addFirst(head);
    }

    public boolean checkIfCrash(Cell nextCell){
        for(Cell cell : snakeBody){
            if(cell==nextCell){
                return true;
            }
        }
        return false;
    }

    public Cell getHead() {
        return head;
    }

    public void setHead(Cell head) {
        this.head = head;
    }

    public LinkedList<Cell> getSnakeBody() {
        return snakeBody;
    }

    public void setSnakeBody(LinkedList<Cell> snakeBody) {
        this.snakeBody = snakeBody;
    }
}
