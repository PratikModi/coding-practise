package com.java.coding.interviews.practise.oracle;

import java.util.*;

/**
 * Find the shortest path from source to destination in a matrix that satisfies given constraints
 * Google Translate Icon
 * Given an N × N matrix of positive integers, find the shortest path from the first cell of the matrix to its last cell that satisfies given constraints.
 *
 * We are allowed to move exactly k steps from any cell in the matrix where k is the cell’s value, i.e., from a cell (i, j) having value k in a matrix M,
 * we can move to (i+k, j), (i-k, j), (i, j+k), or (i, j-k). The diagonal moves are not allowed.
 *
 * For example,
 *
 * Input:
 *
 * [ 7  1  3  5  3  6  1  1  7  5 ]
 * [ 2  3  6  1  1  6  6  6  1  2 ]
 * [ 6  1  7  2  1  4  7  6  6  2 ]
 * [ 6  6  7  1  3  3  5  1  3  4 ]
 * [ 5  5  6  1  5  4  6  1  7  4 ]
 * [ 3  5  5  2  7  5  3  4  3  6 ]
 * [ 4  1  4  3  6  4  5  3  2  6 ]
 * [ 4  4  1  7  4  3  3  1  4  2 ]
 * [ 4  4  5  1  5  2  3  5  3  5 ]
 * [ 3  6  3  5  2  2  6  4  2  1 ]
 *
 * Output:
 *
 * The shortest path length is 6
 * The shortest path is (0, 0) (0, 7) (0, 6) (1, 6) (7, 6) (7, 9) (9, 9)
 *
 *
 * Input:
 *
 * [ 4  4  6  5  5  1  1  1  7  4 ]
 * [ 3  6  2  4  6  5  7  2  6  6 ]
 * [ 1  3  6  1  1  1  7  1  4  5 ]
 * [ 7  5  6  3  1  3  3  1  1  7 ]
 * [ 3  4  6  4  7  2  6  5  4  4 ]
 * [ 3  2  5  1  2  5  1  2  3  4 ]
 * [ 4  2  2  2  5  2  3  7  7  3 ]
 * [ 7  2  4  3  5  2  2  3  6  3 ]
 * [ 5  1  4  2  6  4  6  7  3  7 ]
 * [ 1  4  1  7  5  3  6  5  3  4 ]
 *
 * Output:
 *
 * The shortest path length is 6
 * The shortest path is (0, 0) (0, 4) (5, 4) (5, 2) (5, 7) (5, 9) (9, 9)
 */
public class ShortestPathInMatrixProblem {
    public static void main(String[] args) {
        int[][] matrix = {
                {7,1,3,5,3,6,1,1,7,5},
                {2,3,6,1,1,6,6,6,1,2},
                {6,1,7,2,1,4,7,6,6,2},
                {6,6,7,1,3,3,5,1,3,4},
                {5,5,6,1,5,4,6,1,7,4},
                {3,5,5,2,7,5,3,4,3,6},
                {4,1,4,3,6,4,5,3,2,6},
                {4,4,1,7,4,3,3,1,4,2},
                {4,4,5,1,5,2,3,5,3,5},
                {3,6,3,5,2,2,6,4,2,1}
        };
        System.out.println(findPath(matrix,0,0));
    }

    public static boolean isValid(int X, int Y, int N){
        return (X>=0 && X<N) && (Y>=0 && Y<N);
    }

    public static void findPath(Cell cell, List<String> path){
        if(cell!=null){
            findPath(cell.parent,path);
            path.add(cell.toString());
        }
    }

    public static List<String> findPath(int[][] matrix, int X, int Y){
        List<String> path = new ArrayList<>();
        if(matrix==null || matrix.length==0)
            return path;
        int[] row = {-1,0,0,1};
        int[] col = {0,1,-1,0};
        int N =matrix.length;
        Queue<Cell> Q = new LinkedList<>();
        Cell src = new Cell(X,Y,null);
        Q.add(src);
        Set<String> visited = new HashSet<>();
        String key = src.X+","+src.Y;
        visited.add(key);
        while(!Q.isEmpty()){
            Cell cell = Q.poll();
            int i= cell.X;
            int j = cell.Y;
            if(i==N-1 && j==N-1){
                findPath(cell,path);
                return path;
            }
            int n = matrix[i][j];
            for(int k=0;k< row.length;k++){
                X=i+ row[k]*n;
                Y=j+ col[k]*n;
                if(isValid(X,Y,N)){
                    Cell next = new Cell(X,Y,cell);
                    key = next.X+","+next.Y;
                    if(!visited.contains(key)){
                        Q.add(next);
                        visited.add(key);
                    }
                }
            }
        }
        return path;
    }


}

class Cell{
    int X;
    int Y;
    Cell parent;

    Cell(int X, int Y, Cell parent){
        this.X=X;
        this.Y=Y;
        this.parent=parent;
    }

    public String toString(){
        return new StringBuilder("(").append(X).append(",").append(Y).append(")").toString();
    }
}
