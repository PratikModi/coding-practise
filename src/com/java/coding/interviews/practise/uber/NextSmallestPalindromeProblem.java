package com.java.coding.interviews.practise.uber;

/**
 * Problem Description
 *
 * Given a numeric string A representing a large number you need to find the next smallest palindrome greater than this number.
 *
 *
 *
 * Problem Constraints
 * 1 <= |A| <= 100
 *
 * A doesn't start with zeroes and always contain digits from 0-9.
 *
 *
 *
 * Input Format
 * First and only argument is an string A.
 *
 *
 *
 * Output Format
 * Return a numeric string denoting the next smallest palindrome greater than A.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  A = "23545"
 * Input 2:
 *
 *  A = "999"
 *
 *
 * Example Output
 * Output 1:
 *
 *  "23632"
 * Output 2:
 *
 *  "1001"
 */
public class NextSmallestPalindromeProblem {

    public static void main(String[] args) {
        System.out.println(findNextPalindrome("199"));
        System.out.println(findNextPalindrome("2456"));
        System.out.println(findNextPalindrome("1"));
        System.out.println(findNextPalindrome("12345"));
    }

    private static String addOne(String S){
        return String.valueOf(Integer.parseInt(S)+1);
    }

    private static String reverse(String S){
        return new StringBuilder(S).reverse().toString();
    }

    private static boolean isGreater(String left, String right){
        return Integer.parseInt(left) > Integer.parseInt(right);
    }

    public static String findNextPalindrome(String S){
        if(S==null || S.length()==0)
            return S;
        if(S.length()==1){
            if(S.equalsIgnoreCase("9")) return "11";
            else return addOne(S);
        }
        String SS="";
        if(S.equalsIgnoreCase(reverse(S))){
            SS=addOne(S);
        }else{
            SS=S;
        }
        int N = SS.length();
        int mid=0;
        if(N%2==0){
            mid=N/2;
            String left = SS.substring(0,mid);
            String right = SS.substring(mid);
            if(isGreater(reverse(left),right)){
                return left + reverse(left);
            }else{
                String newLeft = addOne(left);
                return newLeft+reverse(newLeft);
            }
        }else{
            mid=N/2;
            String left = SS.substring(0,mid);
            String right = SS.substring(mid+1);
            if(isGreater(reverse(left),right)){
                return left+S.charAt(mid)+reverse(left);
            }else{
                if(SS.charAt(mid)!='9'){
                    char c = (char) (((SS.charAt(mid) - '0') + 1) + '0');
                    return left+c+reverse(left);
                }else{
                    String newLeft = addOne(left+SS.charAt(mid));
                    left = newLeft.substring(0,mid);
                    return newLeft+reverse(left);
                }
            }
        }
    }


}
