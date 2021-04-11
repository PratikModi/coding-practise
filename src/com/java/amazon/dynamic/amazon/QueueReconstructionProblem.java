package com.java.amazon.dynamic.amazon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),
 * where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h.
 * Write an algorithm to reconstruct the queue.
 *
 * Note:
 * The number of people is less than 1,100.
 *
 *
 * Example
 *
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */

public class QueueReconstructionProblem {

    public static void main(String[] args) {
        int[][] people = new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] result = reconstructQueue(people);
        Arrays.stream(result).forEach(e-> System.out.print(Arrays.toString(e)));
        System.out.println();
        result = reconstructQueueSmart(people);
        Arrays.stream(result).forEach(e-> System.out.print(Arrays.toString(e)));
    }

    public static int[][] reconstructQueue(int[][] people) {
        if(people==null || people.length<2){
            return people;
        }
        int N = people.length;
        int[][] result = new int[N][2];
        Arrays.stream(result).forEach(e->e[0]=-1);
        people=Arrays.asList(people).stream().sorted(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        }).collect(Collectors.toList()).toArray(people);
        //Arrays.stream(people).forEach(e-> System.out.println(Arrays.toString(e)));
        for(int i=0;i<N;i++){
            int count = people[i][1];
            for(int j=0;j<N;j++){
                if(result[j][0]==-1 && count==0){
                    result[j][0]=people[i][0];
                    result[j][1]=people[i][1];
                    break;
                }else if(result[j][0]==-1 || result[j][0]>=people[i][0]){
                    count-=1;
                }
            }
        }
        return result;
    }

    public static int[][] reconstructQueueSmart(int[][] people) {
        if(people==null || people.length<2)
            return people;
        Arrays.sort(people,(o1,o2)->(o1[0]==o2[0])?o1[1]-o2[1]:o2[0]-o1[0]);
        LinkedList<int[]>result = new LinkedList();
        for(int[]p:people)
            result.add(p[1],p);
        return result.toArray(new int[people.length][2]);
    }

}
