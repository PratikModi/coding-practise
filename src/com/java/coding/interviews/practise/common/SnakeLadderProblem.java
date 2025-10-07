package com.java.coding.interviews.practise.common;

import java.util.*;

/**
 * Created by Pratik1 on 21-02-2020.
 */
public class SnakeLadderProblem {

    static class MyQueue{
        int move;
        int pos;
    }

    private static int findMinMoves(int[] board, int n){
        int[] visited = new int[n];
        Queue<MyQueue> queue = new LinkedList<>();
        MyQueue entry = new MyQueue();
        entry.pos=0;
        entry.move=0;
        visited[0]=1;
        queue.add(entry);
        while(!queue.isEmpty()){
            entry = queue.remove();
            int pos = entry.pos;

            if(pos==n-1){
                break;
            }

            for(int j=pos+1;j<n&&j<=pos+6;j++){
                if(visited[j]==0){
                    MyQueue mq = new MyQueue();
                    mq.move = entry.move+1;
                    visited[j]=1;
                    if(board[j]!=-1){
                        mq.pos = board[j];
                    }else{
                        mq.pos=j;
                    }
                    queue.add(mq);
                }
            }
        }
        return entry.move;
    }


    public static int findMoves(int[][] board){
        Set<Integer> visited = new HashSet<>();
        int n = board.length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1,0});
        visited.add(1);
        while(!queue.isEmpty()){
            int[] entry = queue.poll();
            int pos = entry[0];
            int moves = entry[1];

            for(int j=1;pos+j<=n*n&j<=6;j++){
                int next = pos+j;
                int[] position = getPosition(next,n);
                int row = position[0], col=position[1];
                //System.out.println(next+"--"+row+"--"+col);
                if(board[row][col]!=-1){
                    next = board[row][col];
                }
                if(next==n*n) return moves+1;
                if(!visited.contains(next)){
                    visited.add(next);
                    queue.offer(new int[]{next,moves+1});
                }

            }
        }
        return -1;

    }
    private static int[] getPosition(int next, int n){
        int r = (next-1)/n, c = (next-1)%n;
        int row = n-1-r;
        int col = (r%2==0)?c:(n-1-c);
        return new int[]{row,col};
    }


    public static void main(String[] args) {
        int n = 30;
        int[] board = new int[n];
        Arrays.fill(board,-1);
        //Ladders
        board[2] = 21;
        board[4] = 7;
        board[10] = 25;
        board[19] = 28;

        //Snakes
        board[26] = 0;
        board[20] = 8;
        board[16] = 3;
        board[18] = 6;

        System.out.println(findMinMoves(board,30));

        int[][] snakeGameBoard = {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};
        System.out.println(findMoves(snakeGameBoard));


    }

}
