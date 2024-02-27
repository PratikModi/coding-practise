package com.java.coding.interviews.practise.atlassian.snakegame;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Snake {

    private Cell head;

    private LinkedList<Cell> snakeBody;

    private Set<Cell> snakeBodySet;

    public Snake(Cell initPos) {
        this.head = initPos;
        this.head.setCellType(CellType.SNAKE);
        this.snakeBody = new LinkedList<>();
        this.snakeBody.addFirst(head);
        this.snakeBodySet = new HashSet<>();
        this.snakeBodySet.add(head);
    }

    public void grow(){
        this.snakeBody.add(head);
        this.snakeBodySet.add(head);
    }

    public void move(Cell nextCell){
        Cell tail = snakeBody.removeLast();
        tail.setCellType(CellType.EMPTY);
        this.snakeBodySet.remove(tail);
        this.head=nextCell;
        this.head.setCellType(CellType.SNAKE);
        this.snakeBody.addFirst(head);
        this.snakeBodySet.add(head);
    }

    public boolean checkCrash(Cell nextCell){
        return snakeBodySet.contains(nextCell);
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
