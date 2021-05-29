package com.java.coding.interviews.practise;

import java.util.HashMap;
import java.util.Map;

/**
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 *
 * You start at any tree of your choice, then repeatedly perform the following steps:
 *
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 *
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2,
 * then back to step 1, then step 2, and so on until you stop.
 *
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
 *
 * What is the total amount of fruit you can collect with this procedure?
 */
public class FruitBasketProblem {

    public static int findMaxFruitOptimized(int[] tree){
        if(tree==null || tree.length==0)
            return 0;
        int last_fruit=-1;
        int last_second_fruit=-1;
        int last_fruit_count=0;
        int current_max=0;
        int max=0;

        for(int fruit: tree){
            if(fruit==last_fruit || fruit==last_second_fruit){
                current_max+=1;
            }else{
                current_max=last_fruit_count+1;
            }

            if(fruit==last_fruit){
                last_fruit_count++;
            }else{
                last_fruit_count=1;
            }

            if(fruit!=last_fruit){
                last_second_fruit = last_fruit;
                last_fruit=fruit;
            }
            max = Math.max(current_max,max);
        }
        return max;
    }

    public static int findMaxFruitUsingMap(int[] tree){
        if(tree==null || tree.length==0)
            return 0;
        int max=1;
        int i=0,j=0;
        Map<Integer,Integer> map = new HashMap<>();
        while(j<tree.length){
            if(map.size()<=2){
                map.put(tree[j],j++);
            }
            if(map.size()>2){
                int min = tree.length-1;
                for(int k:map.values()){
                    min=Math.min(min,k);
                }
                i=min+1;
                map.remove(tree[min]);
            }
            max=Math.max(max,j-i);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(findMaxFruitOptimized(new int[]{1,2,1}));
        System.out.println(findMaxFruitUsingMap(new int[]{1,2,3}));
    }
}
