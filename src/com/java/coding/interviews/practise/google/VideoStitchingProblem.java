package com.java.coding.interviews.practise.google;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1024. Video Stitching
 * Medium
 *
 * 766
 *
 * 38
 *
 * Add to List
 *
 * Share
 * You are given a series of video clips from a sporting event that lasted time seconds. These video clips can be overlapping with each other and have varying lengths.
 *
 * Each video clip is described by an array clips where clips[i] = [starti, endi] indicates that the ith clip started at starti and ended at endi.
 *
 * We can cut these clips into segments freely.
 *
 * For example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
 * Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event [0, time]. If the task is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
 * Output: 3
 * Explanation:
 * We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
 * Then, we can reconstruct the sporting event as follows:
 * We cut [1,9] into segments [1,2] + [2,8] + [8,9].
 * Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].
 * Example 2:
 *
 * Input: clips = [[0,1],[1,2]], time = 5
 * Output: -1
 * Explanation: We can't cover [0,5] with only [0,1] and [1,2].
 * Example 3:
 *
 * Input: clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], time = 9
 * Output: 3
 * Explanation: We can take clips [0,4], [4,7], and [6,9].
 * Example 4:
 *
 * Input: clips = [[0,4],[2,8]], time = 5
 * Output: 2
 * Explanation: Notice you can have extra video after the event ends.
 */
public class VideoStitchingProblem {

    public static void main(String[] args) {
        int[][] clips = new int[][]{{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
        int time = 10;
        System.out.println(videoStitching(clips,time));
    }

    public static int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips,new Comp());
        if(clips[0][0]!=0) return -1;
        int max=0;
        int min=0;
        int total=0;

        while(max<time){
            for(int i=0;i<clips.length;i++){
                int start = clips[i][0];
                int end = clips[i][1];
                if(start<=min && end>max){
                    max=end;
                }
            }
            if(min==max) return -1;
            min=max;
            total++;
        }
        return total;
    }

}

class Comp implements Comparator<int[]> {

    @Override
    public int compare(int[] ints, int[] t1) {
        int value = ints[0]-t1[0];
        if(value==0)
            return t1[1]-ints[1];
        return value;
    }
}
