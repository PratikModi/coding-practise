package com.java.amazon.dynamic.adobe;

import java.util.Arrays;
import java.util.Random;

/**
 * Shuffle a deck of cards
 * Difficulty Level : Easy
 * Last Updated : 17 Apr, 2019
 * Given a deck of cards, the task is to shuffle them.
 * Algorithm:
 *
 * 1. First, fill the array with the values in order.
 * 2. Go through the array and exchange each element
 *    with the randomly chosen element in the range
 *    from itself to the end.
 *
 * // It is possible that an element will be swap
 * // with itself, but there is no problem with that.
 */
public class DeckCardShuffleProblem {

    public static void main(String[] args) {
        int[] cards = new int[52];
        for(int i=0;i<52;i++){
            cards[i]=i;
        }
        System.out.println(Arrays.toString(cards));
        shuffle(cards,52);
        System.out.println(Arrays.toString(cards));
    }

    public static void shuffle(int[] cards, int N){
        Random random = new Random();
        for(int i=0;i<N;i++){
            int R = i+random.nextInt(N-i);
            int temp = cards[R];
            cards[R]=cards[i];
            cards[i]=temp;
        }
    }
}
