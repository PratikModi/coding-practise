package com.java.coding.interviews.practise.common;

import java.util.Arrays;

/**
 * Created by Pratik on 12-04-2020.
 *
 * There are N farms in a line. Running from one farm to the next one consumes a single unit of Monk's current energy .
 * So sometimes he may have to refill his energy. He can ask a farm owner to give some milk so that he can gain
 * some energy. The Farm owner agrees to give him milk only on one condition that he wont be allowed to take apples
 * from that farm.
 *
 * So, at each farm, Monk has one choice either to take milk ( for increasing his energy) or apples from the farm.
 * Each farm has different amount of apples and milk. So, from each farm, Monk is allowed to take only either the
 * entire amount of Milk or the entire amount of apples and not none or both.
 *
 * By following so, what is the maximum number of apples Monk can collect, always having non-negative energy ?
 */
public class AppleEnergyProblem {
    static double[][] dp;
    public static void main(String[] args) {
        int n=5;
        int energy = 1;
        int[] apple = {5,4,3,2,1};
        int[] milk = {5,4,3,2,1};
        dp = new double[n+1][n+1];
        for(int i=0;i<dp.length;i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println((int)maxApple(n,0,energy,milk,apple));
    }

    public static double maxApple(int n, int index, int energy, int[] milk, int[] apple){
        if(index==n)
            return 0;
        if(energy>n)
            energy=n;
        if(dp[index][energy]!=-1)
            return dp[index][energy];
        if(energy > 0){
            dp[index][energy] = Math.max(apple[index]+maxApple(n,index+1,energy-1,milk,apple),
                    maxApple(n,index+1,energy+milk[index]-1,milk,apple));
        }else if(milk[index]!=0){
            dp[index][energy] = Math.max(apple[index],maxApple(n,index+1,energy+milk[index]-1,milk,apple));
        }else{
            dp[index][energy]=apple[index];
        }
        return dp[index][energy];
    }

}
