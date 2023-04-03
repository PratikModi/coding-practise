package com.java.coding.interviews.practise.jpmc;

import java.util.*;

/**
 * Finding friends of friends. Graph problem BFS or DFS.
 * Given that A is friend of B and B is friend of C and D.
 * Find if A and D are friends. Traversal problem.
 */
public class FriendOfFriendsProblem {

    public static void main(String[] args) {
        Map<String,List<String>> friends = new HashMap<>();
        friends.put("A",List.of("B"));
        friends.put("B",List.of("C","D"));
        friends.put("D",List.of("E","F"));
        String F1 = "A";
        String F2 = "E";
        System.out.println(findIfFriends(friends,F1,F2));
    }

    public static boolean findIfFriends(Map<String, List<String>> friends, String F1, String F2){
        if(friends==null || friends.isEmpty())
            return false;
        Queue<String> queue = new LinkedList<>();
        queue.add(F1);
        while(!queue.isEmpty()){
            String friend = queue.poll();
            List<String> frnds = friends.get(friend);
            if(frnds!=null){
                Iterator<String> iterator = frnds.iterator();
                while(iterator.hasNext()){
                    String fof = iterator.next();
                    if(fof.equalsIgnoreCase(F2)) {
                        return true;
                    }
                    else{
                        queue.add(fof);
                    }
                }
            }
        }
        return false;
    }


}
