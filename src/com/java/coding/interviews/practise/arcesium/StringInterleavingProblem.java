package com.java.coding.interviews.practise.arcesium;

/**
 * /**
 *  * Given A, B, C, find whether C is formed by the interleaving of A and B.
 *  *
 *  * Input Format:*
 *  *
 *  * The first argument of input contains a string, A.
 *  * The second argument of input contains a string, B.
 *  * The third argument of input contains a string, C.
 *  * Output Format:
 *  *
 *  * Return an integer, 0 or 1:
 *  *     => 0 : False
 *  *     => 1 : True
 *  * Constraints:
 *  *
 *  * 1 <= length(A), length(B), length(C) <= 150
 *  * Examples:
 *  *
 *  * Input 1:
 *  *     A = "aabcc"
 *  *     B = "dbbca"
 *  *     C = "aadbbcbcac"
 *  *
 *  * Output 1:
 *  *     1
 *  *
 *  * Explanation 1:
 *  *     "aa" (from A) + "dbbc" (from B) + "bc" (from A) + "a" (from B) + "c" (from A)
 *
 */
public class StringInterleavingProblem {

    public static void main(String[] args) {

        String A = "aabcc";
        String B = "dbbca";
        String C = "aadbbcbcac";
        System.out.println(isInterleave(A,B,C));
        //System.out.println(isInterleaveDP( A,B,C));
        //System.out.println(isInterleaveDP( "aabc","abad","aabadabc"));
    }
    //Time Complexity:: O(N*M)
    //Space Complexity:: O(N*M)
    public static boolean isInterleave(String A, String B, String C){
        int n1=A.length();
        int n2=B.length();
        int n3=C.length();
        if(n1+n2!=n3) return false;
        Boolean[][] memo = new Boolean[n1+1][n2+1];
        return isInterleave(A,B,C,0,0,0,memo);
    }

    private static boolean isInterleave(String A, String B, String C, int i, int j, int k, Boolean[][] memo) {
        if(k==C.length()) return true;
        if(memo[i][j]!=null){
            return memo[i][j];
        }
        boolean res=false;
        if(i<A.length()&&j<B.length()&&A.charAt(i)==C.charAt(k)&&B.charAt(j)==C.charAt(k)){
            res=isInterleave(A,B,C,i+1,j,k+1,memo) || isInterleave(A,B,C,i,j+1,k+1,memo);
        }else if(i<A.length()&&A.charAt(i)==C.charAt(k)){
            res=isInterleave(A,B,C,i+1,j,k+1,memo);
        }else if(j<B.length()&&B.charAt(j)==C.charAt(k)){
            res=isInterleave(A,B,C,i,j+1,k+1,memo);
        }
        memo[i][j]=res;
        return res;
    }

}
