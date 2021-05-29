package com.java.coding.interviews.practise.google;

/**
 * Created by Pratik1 on 27-06-2020.
 */

import java.util.Arrays;

/**
 * Given (x, y) coordinates, create a function such that each coordinate is uniquely mapped
 * to an integer. Also make sure that given an integer,
 * you should be able to find (x, y) coordinates.
 * So F(x, y) = z and also that inverse F(z) = (x, y).
 * BaseValue = Max(X,Y)+1;
 * N=x+(y%BaseValue)*BaseValue;
 * X=N%BaseValue
 * Y=N/BaseValue
 */
public class CoordinatesToDecimalProblem {
    private static int BASE_VALUE=0;
    public static void main(String[] args) {
        int[] C = {45,39};
        int number = coordinatesToDecimal(C);
        System.out.println(number);
        C=decimalToCoordinates(number);
        System.out.println(Arrays.toString(C));
    }

    public static int coordinatesToDecimal(int[] C){
        if(C==null || C.length<2)
            return -1;
        int number;
        BASE_VALUE = Math.max(C[0],C[1])+1;
        number = C[0]+(C[1]%BASE_VALUE)*BASE_VALUE;
        return number;
    }

    public static int[] decimalToCoordinates(int number){
        int[] coordinates = new int[2];
        if(number==-1)
            return coordinates;
        coordinates[0]=number%BASE_VALUE;
        coordinates[1]=number/BASE_VALUE;
        return coordinates;
    }
}
