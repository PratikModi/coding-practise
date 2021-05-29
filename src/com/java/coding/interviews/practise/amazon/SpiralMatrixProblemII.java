package com.java.coding.interviews.practise.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an integer A, generate a square matrix filled with elements from 1 to A2 in spiral order.
 *
 *
 *
 * Input Format:
 *
 * The first and the only argument contains an integer, A.
 * Output Format:
 *
 * Return a 2-d matrix of size A x A satisfying the spiral order.
 * Constraints:
 *
 * 1 <= A <= 1000
 * Examples:
 *
 * Input 1:
 *     A = 3
 *
 * Output 1:
 *     [   [ 1, 2, 3 ],
 *         [ 8, 9, 4 ],
 *         [ 7, 6, 5 ]   ]
 *
 * Input 2:
 *     4
 *
 * Output 2:
 *     [   [1, 2, 3, 4],
 *         [12, 13, 14, 5],
 *         [11, 16, 15, 6],
 *         [10, 9, 8, 7]   ]
 */
public class SpiralMatrixProblemII {

    public static void main(String[] args) {
        createSpiralMatrix(4);
        String[] A = new String[]{"1","91"};
        String L = Arrays.stream(A).sorted(
            new Comparator<String>(){
                @Override
                public int compare(String s, String t1) {
                   // return (t1+s).compareTo((s+t1));
                    return (s+t1).compareTo((t1+s));
                }
            }
        ).collect(Collectors.joining());

        System.out.println(L);
    }

    public static ArrayList<ArrayList<Integer>> createSpiralMatrix(int A){
        var result = new ArrayList<ArrayList<Integer>>();
        if(A==0){
            return result;
        }
        int[][] spiralMatrix = new int[A][A];
        int rowIndex=0;
        int colIndex=0;
        int lastRow=A-1;
        int lastCol=A-1;
        int count=1;
        while(rowIndex<=lastRow && colIndex<=lastCol){
            for(int i=colIndex;i<=lastCol;i++){
                spiralMatrix[rowIndex][i]=count++;
            }
            rowIndex++;

            for(int i=rowIndex;i<=lastRow;i++){
                spiralMatrix[i][lastCol]=count++;
            }
            lastCol--;
            if(rowIndex<=lastRow){
                for(int i=lastCol;i>=colIndex;i--){
                    spiralMatrix[lastRow][i]=count++;
                }
            }
            lastRow--;
            if(colIndex<=lastCol){
                for(int i=lastRow;i>=rowIndex;i--){
                    spiralMatrix[i][colIndex]=count++;
                }
                colIndex++;
            }
        }
        //Arrays.stream(spiralMatrix).forEach(e-> result.add((ArrayList<Integer>) Arrays.stream(e).boxed().collect(Collectors.toList())));
        for(int i=0;i<A;i++){
            ArrayList<Integer> row = new ArrayList<>();
            for(int j=0;j<A;j++){
                row.add(spiralMatrix[i][j]);
            }
            result.add(row);
        }
        System.out.println(result);
        return result;
    }

}
