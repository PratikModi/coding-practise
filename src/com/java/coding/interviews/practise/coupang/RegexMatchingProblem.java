package com.java.coding.interviews.practise.coupang;

/**
 * Suppose: s = “aa” and p = “aa”, since all the characters in both s and p are the same, it’s a match.
 * Suppose:  s = “aabb” and p = “aab*”, We know that substrings bb and b* match because * can be replaced by one b.
 * Since we already know that the remaining substrings “aa” and “aa” are matched, the whole strings are also a match.
 * What can we infer from this? Right, if we have a solution of part of a problem, we can use that partial result and can go forward.
 * Also, we can use the already calculated result without calculating it again.
 *
 * Does this ring a bell ????? Yes, this problem satisfies the following two properties –
 *
 * Optimal Substructure — Any problem has optimal substructure property if its overall optimal solution can be constructed from the optimal solutions of its subproblems.
 * Overlapping Subproblems — Any problem has overlapping sub-problems if finding its solution involves solving the same subproblem multiple times.
 *
 * Create a boolean 2D dp array with sizes as boolean[][] dp = new boolean[s.length() + 1][p.length() + 1].
 * We are adding extra 1 to incorporate the case in case either or both of the strings are empty.
 * If both strings are empty, then it’s a match, thus, dp[0][0] = true.
 * Let’s take an example s = "aab" and p = "c*a*b" and create a DP table.
 *
 *
 * First column — it means p is empty and it will match to s only if s is also empty which we have stored in dp[0][0]. Thus, remaining values of dp[0][i] will be false.
 * First row — this is not so easy. It means which p matches empty s.
 * The answer is either an empty pattern or a pattern that represents an empty string such as "a*", "x*y*", "l*m*n*" and so on.
 * In the above example, if s = "" and p = "c*", then due to *, c can be replaced by 0 cs which gives us an empty string. Hence, dp[0][2] = true.
 */


public class RegexMatchingProblem {
    public static void main(String[] args) {
        System.out.println(isMatch("aa","a"));
        System.out.println(isMatch("aa","a*"));
        System.out.println(isMatch("ab",".*"));
    }

    //Time Complexity: O(s*p)
    //Space Complexity: O(s*p)
    public static boolean isMatch(String s, String p){
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0]=true;

        for(int i=1;i<=m;i++){
            if(p.charAt(i-1)=='*'){
                dp[0][i]=dp[0][i-2];
            }
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(p.charAt(j-1)=='.' || p.charAt(j-1)==s.charAt(i-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else if(p.charAt(j-1)=='*'){
                    dp[i][j] = dp[i][j-2];
                    if(s.charAt(i-1)==p.charAt(j-2) || p.charAt(j-2)=='.'){
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }else{
                    dp[i][j]=false;
                }
            }
        }
        return dp[n][m];
    }

}
