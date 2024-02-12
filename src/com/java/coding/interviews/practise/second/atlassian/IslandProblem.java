package com.java.coding.interviews.practise.second.atlassian;

import java.util.*;

public class IslandProblem {

    private int[][] grid;
    private int row;
    private int column;
    private int totalIsland=0;
    int[] parent;
    Set<Point2D> visited;

    public IslandProblem(int row, int column) {
        this.row = row;
        this.column = column;
        this.grid = new int[row][column];
        this.parent = new int[row*column];
        this.visited = new HashSet<>();
        for(int i=0;i<row*column;i++){
            parent[i]=i;
        }
    }

    private void addIsland(int x, int y){
        if(x<0 || x>=row || y<0 || y>=column)
            return;
        grid[x][y]=1;
        union(x,y);
        totalIsland++;
    }

    public int countIsland(){
        int result=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(grid[i][j]==1){
                    result+=dfs(i,j);
                }
            }
        }
        return result;
    }

    public int countIsland2(){
        int result = totalIsland;
        int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(grid[i][j]==1){
                    int p1 = findParent(i*column+j);
                    for(int d=0;d<direction.length;d++){
                        int r = i+direction[d][0];
                        int c = j+direction[d][1];
                        if(r>=0 && r<row && c>=0 && c<column && grid[r][c]==1){
                            int p2 = findParent(r*column+c);
                            if(p1!=p2){
                                parent[p2]=p1;
                                result--;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private int findParent(int u){
        if(parent[u]!=u){
            parent[u]=findParent(parent[u]);
        }
        return parent[u];
    }

    private void union(int u, int v){
        int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
        for(int i=0;i<row;i++){
            for (int j=0;j<column;j++){
                if(grid[i][j]==1){
                    int p1 = findParent(i*column+j);
                    for(int d=0;d<direction.length;d++){
                        int r = i+direction[d][0];
                        int c = j+direction[d][1];
                        if(r==u && c==v){
                            int p2 = findParent(r*column+c);
                            if(p1!=p2){
                                parent[p2]=p1;
                                totalIsland--;
                            }
                        }
                    }
                }
            }
        }
    }

    private int dfs(int i, int j){
        if(i<0 || i>=row || j<0 || j>=column || grid[i][j]!=1)
            return 0;
        grid[i][j]=0;
        dfs(i-1,j);
        dfs(i+1,j);
        dfs(i,j-1);
        dfs(i,j+1);
        return 1;
    }
    public int countIsland3(){
        return totalIsland;
    }

    public int countIsland4(){
        Queue<Point2D> queue = new LinkedList<>();
        totalIsland = 0;
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(grid[i][j]==1){
                    var point = new Point2D(i,j,1);
                    if(!visited.contains(point)){
                        queue.add(point);
                        while(!queue.isEmpty()){
                            var pt = queue.remove();
                            if(!visited.contains(pt)){
                                visited.add(pt);
                                queue.addAll(findAdjacent(pt));
                            }
                        }
                        totalIsland++;
                    }
                }
            }
        }
        return totalIsland;
    }

    private List<Point2D> findAdjacent(Point2D point){
        List<Point2D> adjacentPoints = new ArrayList<>();
        if(point.getX()-1>=0 && grid[point.getX()-1][point.getY()]==1){
            adjacentPoints.add(new Point2D(point.getX()-1, point.getY(), 1));
        }
        if(point.getX()+1<row && grid[point.getX()+1][point.y]==1){
            adjacentPoints.add(new Point2D(point.getX()+1, point.getY(), 1));
        }
        if(point.getY()-1>=0 && grid[point.getX()][point.getY()-1]==1){
            adjacentPoints.add(new Point2D(point.getX(), point.getY()-1, 1));
        }
        if(point.getY()+1<column && grid[point.getX()][point.getY()+1]==1){
            adjacentPoints.add(new Point2D(point.getX(), point.getY()+1, 1));
        }
        return adjacentPoints;
    }

    public static void main(String[] args) {
        IslandProblem test = new IslandProblem(5,5);
        test.addIsland(0,0);
        test.addIsland(0,1);
        //System.out.println(test.countIsland3());
        test.addIsland(1,1);
        test.addIsland(1,4);
        test.addIsland(2,0);
        test.addIsland(2,3);
        test.addIsland(2,4);
        //System.out.println(test.countIsland3());
        test.addIsland(4,0);
        test.addIsland(4,2);
        test.addIsland(4,4);

        //System.out.println(test.countIsland());
        System.out.println(test.countIsland2());
        System.out.println(test.countIsland3());
        System.out.println(test.countIsland4());
    }
}

class Point2D{

    int x;
    int y;
    int value;
    public Point2D(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return x == point2D.x && y == point2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                '}';
    }
}
