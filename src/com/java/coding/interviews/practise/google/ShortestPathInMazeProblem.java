package com.java.coding.interviews.practise.google;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * You are given an M by N matrix consisting of booleans that represents a board. Each True boolean represents a wall.
 * Each False boolean represents a tile you can walk on.
 *
 * Given this matrix, a start coordinate, and an end coordinate, return the minimum number of steps required to reach the end coordinate from the start.
 * If there is no possible path, then return null. You can move up, left, down, and right. You cannot move through walls. You cannot wrap around the edges of the board.
 *
 * For example, given the following board:
 *
 * [[f, f, f, f],
 * [t, t, f, t],
 * [f, f, f, f],
 * [f, f, f, f]]
 * and start = (3, 0) (bottom left) and end = (0, 0) (top left), the minimum number of steps required to reach the end is 7,
 * since we would need to go through (1, 2) because there is a wall everywhere else on the second row.
 */

public class ShortestPathInMazeProblem {

    public static void main(String[] args) {
        boolean[][] board = {{false, false, false, false},
                             {true, true, false, true},
                             {false, false, false, false},
                             {false, false, false, false}};
        int[] start = {3,0};
        int[] end = {0,0};
        System.out.println(findShortestPath(board,start,end));
   }

    public static int findShortestPath(boolean[][] board, int[] start, int[] end){
        if(board==null || board.length==0) return -1;
        int M = board.length;
        int N = board[0].length;

        boolean[][] visited = new boolean[M][N];
        Queue<Element> Q = new LinkedList<>();
        Q.add(new Element(start[0],start[1],0));
        visited[start[0]][start[1]]=true;
        while(!Q.isEmpty()){
            Element element = Q.poll();
            if(element.equals(new Element(end[0],end[1],0))){
                return element.dist;
            }
            if(isValid(M,N,element.src+1,element.dest)
                    && !board[element.src+1][element.dest]
                    && !visited[element.src+1][element.dest])
                Q.add(new Element(element.src+1,element.dest,element.dist+1));
            if(isValid(M,N,element.src-1,element.dest)
                    && !board[element.src-1][element.dest]
                    && !visited[element.src-1][element.dest])
                Q.add(new Element(element.src-1,element.dest,element.dist+1));
            if(isValid(M,N,element.src,element.dest+1)
                    && !board[element.src][element.dest+1]
                    && !visited[element.src][element.dest+1])
                Q.add(new Element(element.src,element.dest+1,element.dist+1));
            if(isValid(M,N,element.src,element.dest-1)
                    && !board[element.src][element.dest-1]
                    && !visited[element.src][element.dest-1])
                Q.add(new Element(element.src,element.dest-1,element.dist+1));
        }
        return -1;
    }

    public static boolean isValid(int M, int N, int i, int j){
        return !(i<0 || j<0 || i>=M || j>=N);
    }
}

class Element{
    int src;
    int dest;
    int dist;

    public Element(int src, int dest, int dist) {
        this.src = src;
        this.dest = dest;
        this.dist = dist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return src == element.src && dest == element.dest;
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, dest, dist);
    }
}
