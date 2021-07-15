package com.java.coding.interviews.practise.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Create data structure to support set(int index,int value), get(int index), setAll(int value) operation in O(1) time.
 */
public class ArrayO1Problem {

    public static void main(String[] args) {
        ArrayO1Problem arrayO1Problem = new ArrayO1Problem();
        arrayO1Problem.set(0,1);
        System.out.println(arrayO1Problem.get(0));
        arrayO1Problem.setAll(2);
        System.out.println(arrayO1Problem.get(0));
        arrayO1Problem.set(1,3);
        System.out.println(arrayO1Problem.get(0));
    }
    Map<Integer, Integer> values = new HashMap<>();
    boolean setAllFlag = false;
    int setAllValue=0;

    public boolean set(int index,int value){
        boolean flag=false;
        if(values.containsKey(index)){
            values.put(index,value);
        }else{
            values.put(index,value);
            flag=true;
        }
        setAllFlag=false;
        return flag;
    }

    public void setAll(int value){
        setAllFlag=true;
        setAllValue=value;
    }

    public int get(int index){
        if(values.containsKey(index)){
            if(setAllFlag){
                return setAllValue;
            }else{
                return values.get(index);
            }
        }
        return -1;
    }


}
