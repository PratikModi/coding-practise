package com.java.coding.interviews.practise.google;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class GenerateSequenceProblem {

    public static void main(String[] args) {
        int target =3;
        System.out.println(generateSequence(target));
        System.out.println(getSequence(target));
    }

    public static String generateSequence(int target){
        if(target==1)
            return "";
        Set<Integer> visited = new HashSet<>();
        Queue<SequencePair> Q = new LinkedList<>();
        Q.add(new SequencePair(1,""));
        visited.add(1);
        while (!Q.isEmpty()){
            SequencePair pair = Q.remove();
            int divideBy3 = pair.number/3;
            if(!visited.contains(divideBy3)){
                String divideSequence = pair.sequence+"/";
                if(divideBy3==target)
                    return divideSequence;
                else{
                    visited.add(divideBy3);
                    Q.add(new SequencePair(divideBy3,divideSequence));
                }
            }
            int multiplyBy2 = pair.number*2;
            if(!visited.contains(multiplyBy2)){
                String multiplySequence = pair.sequence+"*";
                if(multiplyBy2==target)
                    return multiplySequence;
                else{
                    visited.add(multiplyBy2);
                    Q.add(new SequencePair(multiplyBy2,multiplySequence));
                }
            }

        }
        return null;
    }

    private static String getSequence(int target) {
        handleValidation(target >= 0, "target cannot be negative");

        if (target == 1) {
            return "";
        }

        HashSet<Integer> visitedNumbers = new HashSet<>();
        visitedNumbers.add(1);

        Queue<SequencePair> queue = new LinkedList<>();
        queue.add(new SequencePair(1, ""));

        while (!queue.isEmpty()) {
            SequencePair currentResult = queue.remove();
            int currentNumber = currentResult.number;
            String currentSequence = currentResult.sequence;

            int dividedNumber = currentNumber / 3;
            if (!visitedNumbers.contains(dividedNumber)) {
                String dividedSequence = currentSequence + "/";

                if (dividedNumber == target) {
                    return dividedSequence;
                } else {
                    queue.add(new SequencePair(dividedNumber, dividedSequence));
                    visitedNumbers.add(dividedNumber);
                }
            }

            int multiplicationNumber = currentNumber * 2;
            if (!visitedNumbers.contains(multiplicationNumber)) {
                String multiplicationSequence = currentSequence + "*";

                if (multiplicationNumber == target) {
                    return multiplicationSequence;
                } else {
                    queue.add(new SequencePair(multiplicationNumber, multiplicationSequence));
                    visitedNumbers.add(multiplicationNumber);
                }
            }


        }

        throw new IllegalStateException("Could not compute the requested sequence");
    }

    private static void handleValidation(boolean passed, String errorMessage) {
        if (!passed) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}

class SequencePair{
    int number;
    String sequence;

    public SequencePair(int number, String sequence) {
        this.number = number;
        this.sequence = sequence;
    }
}
