package com.java.coding.interviews.practise.uber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


//Space Complexity: O(N) where N is the number of users in the system.
/*
	•	For each user: O(f) — where f is number of users they follow
	•	For n users with m total follow relationships:
	•	Map stores: O(n + m) total space
 */

/**
 * Questions:
 * 1.	Is the connection between users unidirectional (like Twitter) or bidirectional (like Facebook friends)?
 * 2. Can users follow themselves? Should we guard against it?
 * 3. For 2nd-degree connections, do we include only indirect connections, or should we include direct ones as well?
 * 4. Approximately how many users and connections should we expect in the system?
 * 5. What happens if a user is deleted — should they be removed from other users’ follow lists?
 */
public class SocialMediaServiceImpl implements SocialMediaService{

    Map<String, Set<String>> followsMap = new HashMap<>(); // Maps user to their followers

    //Time Complexity: O(1)
    @Override
    public void createProfile(String user) {
        // Implementation for creating a user profile
        if(user == null || user.isEmpty())
            throw new IllegalArgumentException("User cannot be null or empty");
        if(!followsMap.containsKey(user)) {
            followsMap.put(user,new HashSet<>());
            System.out.println("Profile created for user: " + user);
        }
    }

    //Time Complexity: O(N) where N is the number of followers
    @Override
    public void deleteProfile(String user) {
        // Implementation for deleting a user profile
        if(user == null || user.isEmpty())
            throw new IllegalArgumentException("User cannot be null or empty");
        if(!followsMap.containsKey(user))
            throw new IllegalArgumentException("Profile does not exist for user: " + user);
        followsMap.remove(user);
        // Remove the user from all followers' lists
        for(Set<String> followers : followsMap.values()) {
            followers.remove(user);
        }
        System.out.println("Profile deleted for user: " + user);
    }

    //Time Complexity: O(1) for adding a follow relationship
    @Override
    public void follow(String fromUser, String toUser) {
        // Implementation for following another user
        if(fromUser == null || fromUser.isEmpty() || toUser == null || toUser.isEmpty())
            throw new IllegalArgumentException("Users cannot be null or empty");
        if(!followsMap.containsKey(fromUser) || !followsMap.containsKey(toUser)) {
            throw new IllegalArgumentException("Both users must have profiles");
        }
        followsMap.get(fromUser).add(toUser);
        System.out.println(fromUser + " is now following " + toUser);
    }

    /**
     * Time Complexity: O(N * M) where N is the number of first-degree connections and M is the average number of second-degree connections per first-degree connection.
     *  If all users follow each other, this could be O(N^2) in the worst case.
     */
    @Override
    public Set<String> getSecondDegreeConnections(String user) {
        // Implementation for retrieving second-degree connections
        Set<String> secondDegreeConnections = new HashSet<>();
        if(!followsMap.containsKey(user)){
            System.out.println("User profile does not exist for: " + user);
            return secondDegreeConnections;
        }
        Set<String> firstDegreeConnections = followsMap.get(user);
        for(String firstDegreeUser : firstDegreeConnections){
            Set<String> secondDegree = followsMap.get(firstDegreeUser);
            if(secondDegree!=null){
                for(String secondDegreeUser : secondDegree){
                    // Add second-degree connections only if they are not already first-degree connections and not the user themselves
                    if(!firstDegreeConnections.contains(secondDegreeUser) && !secondDegreeUser.equals(user)){
                        secondDegreeConnections.add(secondDegreeUser);
                    }
                }
            }
        }
        return secondDegreeConnections; // Placeholder return statement
    }
}
