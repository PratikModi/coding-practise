package com.java.coding.interviews.practise.uber;


import java.util.Arrays;

/**
 * Sort an array of T-shirts based on their sizes.
 * The sizes are represented as integers:
 * S for Small, M for Medium, and L for Large.
 *
 * Example:
 * Input: {2, 0, 1, 2, 0, 1}
 * Output: {0, 0, 1, 1, 2, 2}
 *
 * Questions:
 * What is the size range of the input array?”
 * This helps you assess performance and memory impact.
 * “Can the array have only one or two elements?”
 * Useful to confirm if such edge cases matter in your solution.
 * “Is there a specific time complexity requirement?”
 * You might be expected to do this in O(n) time instead of O(n log n).
 *
 * Taking Point:
 * 'S' is a primitive char — a 16-bit (2-byte) value.
 * 	"S" is a String object — a full object in Java memory.
 *
 * 	String s = "S";
 *
 * 	It may look small, but internally it’s structured like this:
 *
 * 1. Object Header (12–16 bytes)
 *
 * Every object in Java (except primitives) has a header that stores:
 * 	•	Object metadata (e.g., hash code, locking info).
 * 	•	Class pointer.
 *
 * 	Extra Fields
 * 	•	length (implicitly known from char[])
 * 	•	Cached hashCode field (used in hashing, like in HashMap)
 * 	•	Other fields (in newer versions: coder, offset, etc. for compact strings)
 *
 * Counting sort...
 * it will take extra space to store the count of each size.
 */

// Time Complexity: O(n)
// Space Complexity: O(1) (if we ignore the input array size, as we are sorting in place)
public class TShirtArraySorting {

    public static void main(String[] args) {
        char[] array= {'M', 'S', 'L', 'M', 'S', 'L'};
        sortTShirtSIze(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sortTShirtSIze(char[] size){
        if(size==null || size.length<2)
            return;
        int L=0; // Pointer for 'S'
        int H=size.length-1; // Pointer for 'L'
        int M=0; // current element being evaluated
        while(M<=H){
            if(size[M]=='S'){
                char temp = size[L];
                size[L] = size[M];
                size[M] = temp;
                L++;
                M++;
            }else if(size[M]== 'M'){
                M++;
            }else{
                char temp = size[M];
                size[M] = size[H];
                size[H] = temp;
                H--; //Don't increment M here because we need to be evaluated`
            }
        }
    }

}
