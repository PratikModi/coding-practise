package com.java.coding.interviews.practise.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Calculate tax if Salary and Tax Brackets are given as list in the form
 * [ [10000, 0.3],[20000, 0.2], [30000, 0.1], [0, .1]]
 * null being rest of the salary
 * Big O for both questions
 */
public class TaxCalculator {

    public static void main(String[] args) {
        List<List<Double>> taxes = new ArrayList<>();
        taxes.add(Arrays.asList(10000d,0.3d));
        taxes.add(Arrays.asList(20000d,0.2d));
        taxes.add(Arrays.asList(30000d,0.1d));
        taxes.add(Arrays.asList(0d,0.1d));
        System.out.println(calculateTax(taxes,12000d));
    }

    public static double calculateTax(List<List<Double>> taxes, double amount){
        if(taxes==null || taxes.isEmpty())
            return amount;
        int taxAmount=0;
        Collections.sort(taxes,(l1,l2)->(int)(l1.get(0)-l2.get(0)));
        int N = taxes.size();
        for(int i=0;i<N;i++){
            if(i<N-1 && amount >= taxes.get(i+1).get(0)){
                taxAmount+=(taxes.get(i+1).get(0)-taxes.get(i).get(0))*taxes.get(i).get(1);
            }else{
                taxAmount+=(amount-taxes.get(i).get(0))*taxes.get(i).get(1);
                break;
            }
        }
        return taxAmount;
    }




}
