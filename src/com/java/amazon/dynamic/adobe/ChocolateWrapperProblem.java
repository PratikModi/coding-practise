package com.java.amazon.dynamic.adobe;

/**
 * Program for Chocolate and Wrapper Puzzle
 * Difficulty Level : Medium
 * Last Updated : 22 Apr, 2021
 * Given the following three values, the task is to find the total number of maximum chocolates you can eat.
 *
 * money: Money you have to buy chocolates
 * price: Price of a chocolate
 * wrap: Number of wrappers to be returned for getting one extra chocolate.
 * It may be assumed that all given values are positive integers and greater than 1.
 * Examples:
 *
 * Input: money = 16, price = 2, wrap = 2
 * Output:   15
 * Price of a chocolate is 2. You can buy 8 chocolates from
 * amount 16. You can return 8 wrappers back and get 4 more
 * chocolates. Then you can return 4 wrappers and get 2 more
 * chocolates. Finally you can return 2 wrappers to get 1
 * more chocolate.
 *
 * Formula:-
 * Find initial number of chocolates by
 * dividing the amount with per piece cost.
 * i.e. choc = money / price
 *
 * then apply below formula
 * choc += (choc - 1)/(wrap - 1)
 */
public class ChocolateWrapperProblem {

    public static void main(String[] args) {
        int money = 16;
        int price = 2;
        int wrap = 2;
        System.out.println(countTotalChocolate(money,price,wrap));
    }

    public static int countTotalChocolate(int money, int price, int wrap){
        int chocolate = money/price;
        return chocolate+recursion(chocolate,wrap);
    }

    private static int recursion(int chocolate, int wrap){
        if(chocolate<wrap)
            return 0;
        int extraChocolate = chocolate/wrap;
        return extraChocolate + recursion(extraChocolate+chocolate%wrap,wrap);
    }



}
