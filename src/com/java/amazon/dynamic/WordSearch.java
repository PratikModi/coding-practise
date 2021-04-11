package com.java.amazon.dynamic;

/**
 * Created by Pratik1 on 11-04-2020.
 */
public class WordSearch {

    public static boolean exist(char[][] board, String word){
        if(board==null || board.length==0 || word==null || word.length()==0)
            return false;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j] == word.charAt(0) && dfs(board,word,i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, String word,int row, int col, int count){
        if(count==word.length())
            return true;
        if(row<0 || row>=board.length || col<0 || col>=board[row].length || board[row][col]!=word.charAt(count))
            return false;
        char temp = board[row][col];
        board[row][col]=' ';
        boolean found = dfs(board,word,row,col+1,count+1) ||
                dfs(board,word,row,col-1,count+1) ||
                dfs(board,word,row+1,col,count+1) ||
                dfs(board,word,row-1,col,count+1);
        board[row][col]=temp;
        return found;
    }

    public static void main(String[] args) {
        boolean result = exist(new char[][]{{'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}},"SEE");
        System.out.println(result);
    }
}
