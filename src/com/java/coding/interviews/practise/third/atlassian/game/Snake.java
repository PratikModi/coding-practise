package com.java.coding.interviews.practise.third.atlassian.game;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Snake {
    private Cell head;
    private List<Cell> snakeBody;
    private Set<Cell> snakeBodySet;

    public Snake(Cell initPos) {
        this.head = initPos;
        this.snakeBody = new LinkedList<>();
        this.snakeBody.add(head);
        this.snakeBodySet = new HashSet<>();
        this.snakeBodySet.add(head);
    }

    public void move(Cell nextCell){
        Cell tail = snakeBody.removeLast();
        tail.setCellType(CellType.EMPTY);
        snakeBodySet.remove(tail);
        this.head=nextCell;
        this.head.setCellType(CellType.SNAKE);
        this.snakeBody.add(head);
        this.snakeBodySet.add(head);
    }

    public boolean checkIfCrash(Cell nextCell){
        return snakeBodySet.contains(nextCell);
    }

    public void grow(){
        snakeBody.add(head);
    }

    public Cell getHead() {
        return head;
    }

    public void setHead(Cell head) {
        this.head = head;
    }

    public List<Cell> getSnakeBody() {
        return snakeBody;
    }

    public void setSnakeBody(List<Cell> snakeBody) {
        this.snakeBody = snakeBody;
    }
}
