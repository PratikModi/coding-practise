package com.java.coding.interviews.practise;

import java.util.*;

/**
 * Created by Pratik1 on 14-03-2020.
 */
public class TopViewTreeProblem {

    public static void printTopView(Tree root){
        if(root==null)
            return;
        Queue<Tree> queue = new LinkedList<>();
        Map<Integer,Tree> map = new TreeMap();
        int hd = 0;
        root.hd =0;
        queue.add(root);
        while(!queue.isEmpty()){
            Tree temp = queue.remove();
            hd = temp.hd;
            if(!map.containsKey(hd)){
                map.put(hd,temp);
            }
            if(temp.left!=null){
                temp.left.hd = hd-1;
                queue.add(temp.left);
            }
            if(temp.right!=null){
                temp.right.hd=hd+1;
                queue.add(temp.right);
            }
        }

        Iterator<Tree> itr = map.values().iterator();
        while(itr.hasNext()){
            System.out.print(itr.next().value+",");
        }
    }

    public static void main(String[] args) {
        Tree node = new Tree(1);
        node.left = new Tree(2);
        node.right = new Tree(3);
        node.left.left = new Tree(4);
        node.left.right = new Tree(5);
        node.right.left = new Tree(6);
        node.right.right = new Tree(7);
        printTopView(node);
    }
}
