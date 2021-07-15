package com.java.coding.interviews.practise.common;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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
    }

}
