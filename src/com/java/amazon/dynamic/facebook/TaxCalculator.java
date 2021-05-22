package com.java.amazon.dynamic.facebook;

import java.util.*;

public class TaxCalculator {

    public static class Tax {
        double base, rate;
        public Tax(double base, double rate) {
            this.base = base;
            this.rate = rate;
        }

        @Override
        public String toString() {
            return "Tax{" +
                    "base=" + base +
                    ", rate=" + rate +
                    '}';
        }
    }

    public double calculate(Tax[] taxs, double money) {
        Arrays.sort(taxs, Comparator.comparingDouble(T->T.base));
        //Arrays.stream(taxs).forEach(e-> System.out.println(e));
        double pay = 0;
        for (int i = 0; i < taxs.length; i ++) {
            if (i < taxs.length - 1 && money >= taxs[i + 1].base) {
                pay += (taxs[i + 1].base - taxs[i].base) * taxs[i].rate;
                //System.out.println("1..."+pay);
            }
            else {
                pay += (money - taxs[i].base) * taxs[i].rate;
                //System.out.println("2..."+pay);
                break;
            }
        }
        return pay;
    }

    public static double calculate(List<List<Double>> taxs, double money){
        if(taxs==null || taxs.isEmpty())
            return money;
        Collections.sort(taxs,Comparator.comparingDouble(e->e.get(0)));
        double pay=0;
        int N = taxs.size();
        for(int i=0;i<N;i++){
            if(i<N-1 && money>=taxs.get(i+1).get(0)){
                //System.out.println((taxs.get(i+1).get(0)+"-"+taxs.get(i).get(0))+"*"+taxs.get(i).get(1) +"=="+((taxs.get(i+1).get(0)-taxs.get(i).get(0))*taxs.get(i).get(1)));
                pay+=(taxs.get(i+1).get(0)-taxs.get(i).get(0))*taxs.get(i).get(1);
                //System.out.println("1.."+pay);
            }else{
                //System.out.println("+"+money+"-"+taxs.get(i).get(0)+"*"+taxs.get(i).get(1));
                pay+=(money-taxs.get(i).get(0))*taxs.get(i).get(1);
                //System.out.println("2..."+pay);
                break;
            }
        }
        return pay;
    }

    public static void main(String[] args) {
        TaxCalculator sol = new TaxCalculator();
        Tax[] taxs = new Tax[4];
        taxs[0] = new Tax(10000, 0.1);
        taxs[1] = new Tax(8000, 0.2);
        taxs[2] = new Tax(6000, 0.3);
        taxs[3] = new Tax(0, 0.4);
        System.out.println(sol.calculate(taxs, 12000));
        List<List<Double>> taxes = new ArrayList<>();
        taxes.add(Arrays.asList(10000d,0.1));
        taxes.add(Arrays.asList(8000d,0.2));
        taxes.add(Arrays.asList(6000d,0.3));
        taxes.add(Arrays.asList(0d,0.4));
        System.out.println(calculate(taxes,1000));
    }


}
