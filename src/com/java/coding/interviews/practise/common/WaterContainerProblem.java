package com.java.coding.interviews.practise.common;

/**
 * Created by Pratik1 on 20-04-2020.
 */
public class WaterContainerProblem {

    public static void main(String[] args) {
        int[] A = {1,8,6,2,5,4,8,3,7};
        System.out.println(findMaxArea(A));
    }

    public static int findMaxArea(int[] A){
        if(A==null || A.length==0)
            return 0;
        int low=0;
        int high=A.length-1;
        int max_area=0;
        while (low<=high){
            if(A[low]<=A[high]){
                max_area=Math.max(max_area,A[low]*(high-low));
                low++;
            }else{
                max_area=Math.max(max_area,A[high]*(high-low));
                high--;
            }
        }
        return max_area;
    }
}
