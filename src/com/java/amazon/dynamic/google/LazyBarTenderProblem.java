package com.java.amazon.dynamic.google;


import java.util.*;

/**
 * At a popular bar, each customer has a set of favorite drinks, and will happily accept any drink
 * among this set. For example, in the following situation, customer 0 will be satisfied
 * with drinks 0, 1, 3, or 6.
 * preferences = {
 * 0: [0, 1, 3, 6],
 * 1: [1, 4, 7],
 * 2: [2, 4, 7, 5],
 * 3: [3, 2, 5],
 * 4: [5, 8]
 * }
 * A lazy bartender working at this bar is trying to reduce his effort by limiting the drink recipes
 * he must memorize. Given a dictionary input such as the one above, return the fewest number of drinks
 * he must learn in order to satisfy all customers.
 * For the input above, the answer would be 2, as drinks 1 and 5 will satisfy everyone.
 * Examples:
 * Input Type:
 * argument 1:
 * 2D Array where each row number represents the unique customer and each row values represent
 * the drinks that satisfies the corresponding customer
 * argument 2:
 * number of drinks
 * argument 3:
 * number of customers.
 * Output Type : An integer value which represents the fewest number of drinks that satisfy all the customers.
 * Input:
 * {
 * {0,1,3,6},
 * {1,4,7},
 * {2,4,7,5},
 * {3,2,5},
 * {5,8}
 * }
 * ,9, 5
 * Output : 2
 */

public class LazyBarTenderProblem {

    public static void main(String[] args) {
        int[][] CD = new int[][]{{0,1,3,6},{1,4,7},{2,4,7,5},{3,2,5},{5,8}};
        int result = minDrinkCount(CD,9,5);
        System.out.println(result);
        //System.out.println(minDrinksCount(CD));
    }
    static class Data{
        int sum=0;
        List<Integer> C = new ArrayList<>();

        @Override
        public String toString() {
            return "Data{" +
                    "sum=" + sum +
                    ", C=" + C +
                    '}';
        }
    }
    /*public static int minDrinksCount(int[][]CD){
        Map<Integer,Data> map = new TreeMap(new Comparator<Map.Entry<Integer,Data>>() {
            @Override
            public int compare(Map.Entry<Integer,Data> d1, Map.Entry<Integer,Data> d2) {
                return d2.getValue().sum-d1.getValue().sum;
            }
        });
        for(int i=0;i<CD.length;i++){
            for(int j=0;j<CD[i].length;j++){
                map.putIfAbsent(CD[i][j],new Data());
                Data D = map.get(CD[i][j]);
                D.C.add(i);
                D.sum+=1;
                map.put(CD[i][j],D);
            }
        }
        System.out.println(map);
        return 0;
    }*/


    public static int minDrinkCount(int[][] CD, int D, int C){
        int result=0;
        if(CD==null || CD.length==0 || D==0 || C==0)
            return result;
        int[][] AR = new int[D][C+1];

        //Populate the favorite drink of customer
        for(int i=0;i<CD.length;i++){
            for(int j=0;j<CD[i].length;j++){
                int index = CD[i][j];
                AR[index][i]= 1;
            }
        }
        //Populate the Sum (C+1) column
        for(int i=0;i<D;i++){
            int sum=0;
            for(int j=0;j<C;j++){
                sum+=AR[i][j];
            }
            AR[i][C]=sum;
        }
        int P=C, maxIndex=0;
        int max=0;
        while(P>0){
            for(int i=0;i<D;i++){
                if(max<AR[i][C]) {
                    max = Math.max(max, AR[i][C]);
                    maxIndex = i;
                }
            }
            result++;
            P-=max;
            for(int i=0;i<C;i++){
                if(AR[maxIndex][i]==1){
                    for(int j=0;j<D;j++){
                         if(AR[j][i]==1){
                            AR[j][i]=0;
                            AR[j][C]=-1;
                        }
                    }
                }

            }
        }
        return result;
    }
}
