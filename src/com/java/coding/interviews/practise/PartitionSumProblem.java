package com.java.coding.interviews.practise;

/**
 * Created by Pratik1 on 08-03-2020.
 */
public class PartitionSumProblem {

    public static boolean canPartition(int[] nums){
        if(nums==null && nums.length==0)
            return false;
        int total_sum=0;
        for(int i:nums){
            total_sum+=i;
        }
        if(total_sum%2!=0)
            return false;
        return canPartition(nums,0,total_sum,0);
    }

    private static boolean canPartition(int[] nums, int current_sum, int total_sum, int index){
        if(current_sum==total_sum/2)
            return true;
        if(current_sum>total_sum/2 || index >=nums.length)
            return false;
        for(int i=index;i<nums.length;i++){
            return canPartition(nums,current_sum,total_sum,index+1)||
                    canPartition(nums,current_sum+nums[i],total_sum,index+1);
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(canPartition(new int[] {1,7,7,11}));
    }

}
