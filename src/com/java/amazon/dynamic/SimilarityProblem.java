package com.java.amazon.dynamic;

import java.util.Arrays;

/**
 * Created by Pratik1 on 12-04-2020.
 */
public class SimilarityProblem {

    public static void main(String[] args) {
        System.out.println(findSimilarities("ababa"));
    }

    private static int[] constructZArray(String str){
        int[] Z = new int[str.length()];
        if(str==null || str.length()==0)
            return Z;
        int L,R,k;
        L=R=0;
        Z[0]=0;
        int n = str.length();
        for(int i=1;i<n;++i){
            if(i>R){
                L=R=i;
                while(R<n && str.charAt(R-L)==str.charAt(R))
                    R++;
                Z[i]=R-L;
                R--;
            }else{
                k=i-L;
                if(Z[k]<R-i+1){
                    Z[i]=Z[k];
                }else{
                    L=i;
                    while(R<n && str.charAt(R-L)==str.charAt(R))
                        R++;
                    Z[i]=R-L;
                    R--;
                }
            }
        }
        return Z;
    }

    public static int findSimilarities(String str){
        int[] Z = constructZArray(str);
        System.out.println(Arrays.toString(Z));
        int count=str.length();
        for(int i : Z){
            count+=i;
        }
        return count;
    }
}
