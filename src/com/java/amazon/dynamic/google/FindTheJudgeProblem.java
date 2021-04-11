package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 02-06-2020.
 */
/**
 * In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.

 If the town judge exists, then:

 The town judge trusts nobody.
 Everybody (except for the town judge) trusts the town judge.
 There is exactly one person that satisfies properties 1 and 2.
 You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.

 If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.



 Example 1:

 Input: N = 2, trust = [[1,2]]
 Output: 2
 Example 2:

 Input: N = 3, trust = [[1,3],[2,3]]
 Output: 3
 Example 3:

 Input: N = 3, trust = [[1,3],[2,3],[3,1]]
 Output: -1
 */
public class FindTheJudgeProblem {

    public static void main(String[] args) {
        int[][] P = new int[][]{{1,3},{2,3},{3,1}};
        System.out.println(findJudge(3,P));
        P = new int[][]{{1,3},{2,3}};
        System.out.println(findJudge(3,P));
    }

    public static int findJudge(int N, int[][] P){
        if(N==0 || P==null || P.length==0)
            return -1;
        int[] whoTrust= new int[N];
        int[] whomTrust = new int[N];
        for (int i=0;i<P.length;i++) {
            for(int j=0;j<P[i].length;j++){
                whoTrust[P[i][j]-1]++;
                whomTrust[P[i][++j]-1]++;
            }
        }
        for(int i=0;i<N;i++){
            if(whoTrust[i]==0 && whomTrust[i]==N-1){
                return i+1;
            }
        }
        return -1;
    }
}
