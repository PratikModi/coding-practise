package com.java.coding.interviews.practise;

/**
 * Print the matrix diagonally
 */
public class MatrixDiagonalProblem {
    public static void main(String[] args) {
        char[][] matrix = new char[][]
                {{'1','2','3'},
                 {'4','5','6'},
                 {'7','8','9'}};
        printMatrixDiagonally(matrix);
    }

    public static void printMatrixDiagonally(char[][] matrix){
        if(matrix==null || matrix.length==0)
            return;
        int M = matrix.length;
        int N = matrix[0].length;
        for(int K=0;K<M;K++){
            int i=K;
            int j=0;
            while(i>=0){
                System.out.print(matrix[i][j]);
                i--;
                j++;
            }
        }

        for(int K=1;K<N;K++){
            int i=M-1;
            int j=K;
            while(j<=N-1){
                System.out.println(matrix[i][j]);
                i--;
                j++;
            }
        }

    }
}
