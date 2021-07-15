package com.java.coding.interviews.practise.common;

import java.util.ArrayList;
import java.util.List;

public class NQueenProblemII {

    public static void main(String[] args) {
        (solveNQueen(8)).stream().forEach(System.out::println);
    }

    public static List<List<String>> solveNQueen(int N){
        List<List<String>> board = new ArrayList<>();
        for(int i=0;i<N;i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                row.add(".");
            }
            board.add(row);
        }
        System.out.println(board);
        List<List<String>> result = new ArrayList<>();
        backtrack(board,0,result);
        return result;
    }

    private static boolean backtrack(List<List<String>> board, int row, List<List<String>> result){
        if(row==board.size()) {
            result.addAll(board);
            return true;
        }
        for(int col=0;col<board.get(row).size();col++){
            //System.out.println("HERE");
            if(!isValid(board,row,col)) continue;
            board.get(row).set(col,"Q");
            if(backtrack(board,row+1,result))
                return true;
            board.get(row).set(col,".");
        }
        return false;
    }

    private static boolean isValid(List<List<String>> board, int row, int col){
        for(int i=0;i<board.size();i++){
            if(board.get(i).get(col).equals("Q"))
                return false;
        }
        for(int i=row-1,j=col+1;i>=0 && j<board.get(row).size();i--,j++){
            if(board.get(i).get(j).equals("Q"))
                return false;
        }

        for(int i=row-1,j=col-1;i>=0 && j>=0;i--,j--){
            if(board.get(i).get(j).equals("Q"))
                return false;
        }
        return true;
    }
}
