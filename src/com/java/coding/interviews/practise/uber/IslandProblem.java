package com.java.coding.interviews.practise.uber;

import java.util.*;

public class IslandProblem {

    char[][] terrin;
    int row;
    int column;

    int[] parent;

    int numIsland=0;

    private Set<Point2D> visited;

    public IslandProblem(int n, int m){
        this.terrin = new char[n][m];
        this.row = n;
        this.column = m;
        this.parent = new int[n*m];
        this.visited = new HashSet<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                terrin[i][j]='.';
            }
        }
        for(int i=0;i<n*m;i++){
            parent[i]=i;
        }
    }

    public void addIsland(int x, int y){
        if(x<0 || x>=row || y<0 || y>=column)
            return;
        numIsland++;
        terrin[x][y]='X';
        union(x,y);
    }

    //Time Complexity = O(N*M)
    public int countIsland(){
        int count=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(terrin[i][j]=='X'){
                    count+=dfs(i,j);
                }
            }
        }
        return count;
    }

    public int dfs(int i, int j){
        if(i<0 || i>=row || j<0 || j>=column || terrin[i][j]=='.')
            return 0;
        terrin[i][j]='.';
        dfs(i-1,j);
        dfs(i+1,j);
        dfs(i,j-1);
        dfs(i,j+1);
        return 1;
    }


    //Using Disjoint Set Data Structure

    public int findParent(int u){
        //return parent[u]== u ? u :(parent[u]=findParent(parent[u]));
        if(parent[u]!=u){
            parent[u]=findParent(parent[u]);
        }
        return parent[u];
    }

    /*public void union(int p1, int p2){
        *//*int p1 = findParent(u);
        int p2 = findParent(v);*//*
        if(p1!=p2)
            parent[p2]=p1;
    }*/

    public int countIsland2(){
        int count=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(terrin[i][j]=='X')
                    count++;
            }
        }
        System.out.println(count);
        int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(terrin[i][j]=='X'){
                    int p1= findParent(i*column+j);
                    for(int d=0;d<direction.length;d++){
                        int r = i+direction[d][0];
                        int c = j+direction[d][1];
                        if(r>=0 && r<row && c>=0 && c<column && terrin[r][c]=='X') {
                            int p2 = findParent(r * column + c);
                            if(p1!=p2) {
                                parent[p2]=p1;
                                count--;
                            }
                        }
                    }

                }
            }
        }
        System.out.println(Arrays.toString(parent));
        return count;
    }

    public int countIsland3(){
        return numIsland;
    }

    public void union(int x,int y){
        int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
        for(int i=0;i<row;i++) {
            for (int j = 0; j < column; j++) {
                if (terrin[i][j] == 'X') {
                    int p1 = findParent(i * column + j);
                    for (int d = 0; d < direction.length; d++) {
                        int r = i + direction[d][0];
                        int c = j + direction[d][1];
                        if (r == x && c == y) {
                            int p2 = findParent(x * column + y);
                            if (p1 != p2){
                                parent[p2]=p1;
                                numIsland--;
                            }
                        }
                    }
                }
            }
        }
    }

    public int countIsland4(){
        numIsland=0;

        var queue = new ArrayDeque<Point2D>();
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                Point2D point = new Point2D(i,j,terrin[i][j]);
                if(!visited.contains(point)){
                    if(point.getValue()=='X'){
                        queue.addLast(point);
                        while(!queue.isEmpty()){
                            var pt = queue.getFirst();
                            queue.removeFirst();
                            if(!visited.contains(pt)) {
                                queue.addAll(findAdjacent(pt));
                                visited.add(pt);
                            }
                        }
                        numIsland++;
                    }
                }
            }
        }

        return numIsland;

    }

    private List<Point2D> findAdjacent(Point2D point){
        List<Point2D> result = new ArrayList<>();
        if(point.getX()-1>=0 && terrin[point.getX()-1][point.getY()]=='X'){
            result.add(new Point2D(point.getX()-1,point.getY(),terrin[point.getX()-1][point.getY()]));
        }

        if(point.getX()+1<row && terrin[point.getX()+1][point.getY()]=='X'){
            result.add(new Point2D(point.getX()+1,point.getY(),terrin[point.getX()+1][point.getY()]));
        }

        if(point.getY()-1>=0 && terrin[point.getX()][point.getY()-1]=='X'){
            result.add(new Point2D(point.getX(),point.getY()-1,terrin[point.getX()][point.getY()-1]));
        }

        if(point.getY()+1<column && terrin[point.getX()][point.getY()+1]=='X'){
            result.add(new Point2D(point.getX(),point.getY()+1,terrin[point.getX()][point.getY()+1]));
        }
        return result;
    }


    public static void main(String[] args) {
        IslandProblem test = new IslandProblem(5,5);
        test.addIsland(0,0);
        test.addIsland(0,1);
        test.addIsland(1,1);
        test.addIsland(1,4);
        test.addIsland(2,0);
        test.addIsland(2,3);
        test.addIsland(2,4);
        test.addIsland(4,0);
        test.addIsland(4,2);
        test.addIsland(4,4);

        //System.out.println(test.countIsland());
        //System.out.println(test.countIsland2());
        System.out.println(test.countIsland3());
        System.out.println(test.countIsland4());
    }

}

class Point2D{
    private int x;
    private int y;
    private char value;

    public Point2D(int x, int y, char value) {
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

    public char getValue() {
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
