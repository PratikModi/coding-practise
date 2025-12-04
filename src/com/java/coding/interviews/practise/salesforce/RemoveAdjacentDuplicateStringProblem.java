package com.java.coding.interviews.practise.salesforce;

import java.util.Stack;

/**
 * LeetCode: 1209. Remove All Adjacent Duplicates in String II
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and
 * removing them, causing the left and the right side of the deleted substring to concatenate together.
 *
 * We repeatedly make k duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 * Example 2:
 *
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 * Example 3:
 *
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * 2 <= k <= 104
 * s only contains lowercase English letters.
 *
 * ✅ Solution (Stack of Pairs)
 *
 * Idea
 * 	•	Use a stack where each entry stores:
 * 	•	the character
 * 	•	the number of consecutive times it has appeared so far
 * 	•	When count reaches k, pop it (remove the block).
 * 	•	Finally, rebuild the answer from the stack.
 *
 * Approach                    Time Complexity                  Space Complexity
 * Stack of pairs                    O(n)                                     O(n)
 */
public class RemoveAdjacentDuplicateStringProblem {
    public static void main(String[] args) {
        System.out.println(removeDuplicates("abcd",2));
    }

    public static String removeDuplicates(String s, int k) {
        Stack<Pair> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(!stack.isEmpty() && stack.peek().ch==c){
                stack.peek().count++;
                if(stack.peek().count==k){
                    stack.pop();
                }
            }else{
                stack.push(new Pair(c,1));
            }
        }
        StringBuilder sb = new StringBuilder();
        for(Pair p : stack){
            for(int i=0;i<p.count;i++){
                sb.append(p.ch);
            }
        }
        return sb.toString();
    }

}
class Pair{
    char ch;
    int count;
    Pair(char ch, int count){
        this.ch=ch;
        this.count=count;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Pair["+this.ch+","+this.count+"]";
    }
}
