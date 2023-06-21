package com.java.coding.interviews.practise.uber;

import java.util.*;

/**
 * A rule looks like this:
 *
 * A NE B
 *
 * This means this means point A is located northeast of point B.
 *
 * A SW C
 *
 * means that point A is southwest of C.
 *
 * Given a list of rules, check if the sum of the rules validate. For example:
 *
 * A N B
 * B NE C
 * C N A
 * does not validate, since A cannot be both north and south of C.
 *
 * A NW B
 * A N B
 * is considered valid.
 */
//Time complexity: O(N * |V|) = O(N^2), where N is the number of rules.

//Space complexity: O(|V| + |E|) = O(|V| + |V|^2) = O(N^2), since we are creating a densely-connected graph.
public class DirectionRuleValidationProblem {
    private static final int N = 0;
    private static final int E = 1;
    private static final int S = 2;
    private static final int W = 3;
    private static final int[] DIRECTIONS = new int[]{N,E,S,W};
    private static final Map<Character,Integer> charToDir = new HashMap<>();
    static{
        charToDir.put('N',N);
        charToDir.put('E',E);
        charToDir.put('S',S);
        charToDir.put('W',W);
    }

    public static void main(String[] args) {
        String[] rules = new String[]{"A N B","B NE C","C N A"};
        System.out.println(validate(rules));
    }

    public static boolean validate(String[] rules){
        Map<Character,GraphNode> map = new HashMap<>();
        for(String rule : rules){
            String[] line = rule.split(" ");
            char fromVal = line[2].charAt(0);
            char toVal = line[0].charAt(0);
            if(!map.containsKey(fromVal)){
                map.put(fromVal,new GraphNode(fromVal));
            }
            if(!map.containsKey(toVal)){
                map.put(toVal,new GraphNode(toVal));
            }
            GraphNode from = map.get(fromVal);
            GraphNode to = map.get(toVal);
            for(char dirChar : line[1].toCharArray()){
                int dir = charToDir.get(dirChar);
                if(!isValid(from,to,dir))
                    return false;
                addEdges(from,to,dir);
            }
        }
        System.out.println(map);
        return true;
    }

    private static int opposite(int dir){
        return (dir+2)%4;
    }
    private static boolean isValid(GraphNode from, GraphNode to, int dir){
        int opposite = opposite(dir);
        if(from.edges.get(opposite).contains(to))
            return false;
        return true;
    }

    private static void addEdges(GraphNode from, GraphNode to, int newDir){
        int opposite = opposite(newDir);
        from.edges.get(newDir).add(to);
        to.edges.get(opposite).add(from);
        for(int dir : DIRECTIONS){
            if(dir == newDir)
                continue;
            for(GraphNode neighbour : from.edges.get(dir)){
                if(neighbour==to)
                    continue;
                neighbour.edges.get(dir).add(to);
                to.edges.get(opposite).add(neighbour);
            }
        }
    }

}
class GraphNode{
    char value;
    List<Set<GraphNode>> edges = new ArrayList<>();

    public GraphNode(char value) {
        this.value = value;
        for(int i=0;i<4;i++){
            edges.add(new HashSet<>());
        }
    }


}
