package com.java.coding.interviews.practise.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitArray {

    public static void main(String[] args) {
        int[] array = new int[] {1,23,67,89,90,26,89,6};
        var result = splitArray(array);
        result.stream().forEach(e-> System.out.print(Arrays.toString(e)));
    }

    public static List<int[]> splitArray(int[] array){
        List<int[]> result = new ArrayList<>();
        int[] split=null;
        int index=0;
        for(int i=0;i<array.length;i++){
            if(i%2==0) {
                if(split!=null)
                    result.add(split);
                split = new int[2];
                index=0;
            }
            split[index++]=array[i];
        }
        result.add(split);
        return result;
    }

}
