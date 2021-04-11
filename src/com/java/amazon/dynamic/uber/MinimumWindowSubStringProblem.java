package com.java.amazon.dynamic.uber;

/**
 * Given two strings s and t, return the minimum window in s which will contain all the characters in t.
 * If there is no such window in s that covers all characters in t, return the empty string "".
 *
 * Note that If there is such a window, it is guaranteed that there will always be only one unique minimum window in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 *
 * https://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/
 *
 */
public class MinimumWindowSubStringProblem {
    private static int NO_OF_CHARS=26;
    public static void main(String[] args) {
        System.out.println(findMinimumWindow("ADOBECODEBANC","ABC"));
    }

    public static String findMinimumWindow(String S,String P){
        if(S==null || P==null || S.length()==0 || P.length()==0)
            return "";
        int L1 = S.length();
        int L2 = P.length();
        if(L1<L2){
            System.out.println("No Such Window Exists");
            return "";
        }
        int[] pattern_chars = new int[NO_OF_CHARS];
        int[] string_chars = new int[NO_OF_CHARS];
        for(int i=0;i<L2;i++){
            pattern_chars[P.charAt(i)-'A']++;
        }
        int count=0;
        int start=0,start_index=-1,MIN_LENGTH=Integer.MAX_VALUE;
        for(int j=0;j<L1;j++){
            string_chars[S.charAt(j)-'A']++;
            if(pattern_chars[S.charAt(j)-'A']>=string_chars[S.charAt(j)-'A']) {
                System.out.println("Count++");
                count++;
            }
            System.out.println("J==>"+j+"=="+count);
            if(count==L2){
                System.out.println("IN COUNT==L2");
                while(string_chars[S.charAt(start)-'A']>pattern_chars[S.charAt(start)-'A'] || pattern_chars[S.charAt(start)-'A']==0){
                    if(string_chars[S.charAt(start)-'A']>pattern_chars[S.charAt(start)-'A'])
                        string_chars[S.charAt(start)-'A']--;
                    start++;
                }

                int window = j-start+1;
                //System.out.println("WINDOW==>"+window);
                if(MIN_LENGTH>window){
                    MIN_LENGTH=window;
                    start_index=start;
                   // System.out.println("SI==>"+start_index+" ML==>"+MIN_LENGTH);
                }
            }
        }
        System.out.println(start_index+"-->"+MIN_LENGTH);
        if(start_index==-1){
            System.out.println("NO SUCH WINDOW");
            return "";
        }
        return S.substring(start_index,start_index+MIN_LENGTH);
    }
}
