package com.java.coding.interviews.practise.atlassian.sg;

import java.util.*;
 enum Direction {
    U(-1,0),
    D(1,0),
    L(0,-1),
    R(0,1);
    int row, col;
    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }

     public static Direction fromString(String d) {
         return Direction.valueOf(d.toUpperCase());
     }
}

class Point{
    int row;
    int col;
    Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
    Point move(Direction direction) {
        return new Point(row + direction.row, col + direction.col);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return row == point.row && col == point.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "Point{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}

class Board{
    int height, width;
    List<Point> foods;
    int foodIndex;

    Board(int height, int width, int[][] foodPositions) {
        this.height = height;
        this.width = width;
        this.foods = new ArrayList<>();
        for(int[] foodPosition : foodPositions) {
            this.foods.add(new Point(foodPosition[0], foodPosition[1]));
        }
        this.foodIndex = 0;
    }

    boolean isOutOfBounds(Point point) {
        return point.row < 0 || point.row >= this.height || point.col < 0 || point.col >= this.width;
    }

    boolean isFood(Point point) {
        return foodIndex < this.foods.size() &&  foods.get(foodIndex).equals(point);
    }

    void consumeFeed(){
        foodIndex++;
    }
}

class Snake{
    Deque<Point> body;
    Set<Point> occupied;
    Snake() {
        this.body = new LinkedList<>();
        this.occupied = new HashSet<>();
        Point start = new Point(0, 0);
        this.body.addFirst(start);
        this.occupied.add(start);
    }

    public Point getHead(){
        return this.body.peekFirst();
    }

    public boolean move(Point next, boolean grow){
        Point tail = body.peekLast();
        // Remove tail from Set temporarily
        if(!grow){
            body.pollLast();
            occupied.remove(tail);
        }
        // Check self-collision
        if(occupied.contains(next)){ return false;}

        //Add new head
        body.addFirst(next);
        occupied.add(next);

        //If Growing, Keep Tail
        if(grow){
            body.addLast(tail);
            occupied.add(tail);
        }
        return true;
    }

    public int getLength(){
        return this.body.size()-1;
    }
}

public class SnakeGame{

     private Board board;
     private Snake snake;
     private int score;

     public SnakeGame(int height, int width, int[][] foodPositions) {
         this.board = new Board(height, width, foodPositions);
         this.snake = new Snake();
         this.score = 0;
     }

     public int move(String dir) {
         Direction direction = Direction.fromString(dir);
         Point next = snake.getHead().move(direction);
         if(board.isOutOfBounds(next)){
             return -1;
         }
         boolean isFood = board.isFood(next);
         boolean success = snake.move(next, isFood);
         if(!success){
             return -1;
         }
         if(isFood){
             score++;
             board.consumeFeed();
         }
         //System.out.println(snake.body.peekFirst());
         return score;
     }

    public static void main(String[] args) {
        int[][] food = {{1, 2}, {0, 1}};
        SnakeGame game = new SnakeGame(2, 3, food);
        System.out.println(game.move("R")); // 0
        System.out.println(game.move("D")); // 0
        System.out.println(game.move("R")); // 1
        System.out.println(game.move("U")); // 1
        System.out.println(game.move("L")); // 2
        System.out.println(game.move("U")); // -1
    }

}
