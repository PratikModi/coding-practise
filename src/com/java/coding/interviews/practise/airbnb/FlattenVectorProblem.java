package com.java.coding.interviews.practise.airbnb;

import java.util.NoSuchElementException;

/**
 * 251. Flatten 2D Vector
 * Medium
 *
 * 456
 *
 * 263
 *
 * Add to List
 *
 * Share
 * Design an iterator to flatten a 2D vector. It should support the next and hasNext operations.
 *
 * Implement the Vector2D class:
 *
 * Vector2D(int[][] vec) initializes the object with the 2D vector vec.
 * next() returns the next element from the 2D vector and moves the pointer one step forward. You may assume that all the calls to next are valid.
 * hasNext() returns true if there are still some elements in the vector, and false otherwise.
 *
 *
 * Example 1:
 *
 * Input
 * ["Vector2D", "next", "next", "next", "hasNext", "hasNext", "next", "hasNext"]
 * [[[[1, 2], [3], [4]]], [], [], [], [], [], [], []]
 * Output
 * [null, 1, 2, 3, true, true, 4, false]
 *
 * Explanation
 * Vector2D vector2D = new Vector2D([[1, 2], [3], [4]]);
 * vector2D.next();    // return 1
 * vector2D.next();    // return 2
 * vector2D.next();    // return 3
 * vector2D.hasNext(); // return True
 * vector2D.hasNext(); // return True
 * vector2D.next();    // return 4
 */
//"next", "next", "next", "hasNext", "hasNext", "next", "hasNext"
public class FlattenVectorProblem {

    private int[][] vector;
    private int outer;
    private int inner;
    public static void main(String[] args) {
        int[][] vector = {{1, 2}, {3}, {4}, {}, {}, {}, {}, {}, {}, {}};
        FlattenVectorProblem vectorProblem = new FlattenVectorProblem(vector);
        System.out.println(vectorProblem.next());
        System.out.println(vectorProblem.next());
        System.out.println(vectorProblem.next());
        System.out.println(vectorProblem.hasNext());
        System.out.println(vectorProblem.hasNext());
        System.out.println(vectorProblem.next());
        System.out.println(vectorProblem.hasNext());
    }

    public FlattenVectorProblem(int[][] vector) {
        this.vector = vector;
    }

    public void advanceToNext(){
        while(outer<vector.length && inner == vector[outer].length){
            outer++;
            inner=0;
        }
    }

    public int next(){
        if(!hasNext()) throw new NoSuchElementException();
        return vector[outer][inner++];
    }

    public boolean hasNext(){
        advanceToNext();
        return outer < vector.length;
    }
}
