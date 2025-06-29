package com.java.coding.interviews.practise.uber;

import java.util.Set;

public interface SocialMediaService {
    void createProfile(String user);
    void deleteProfile(String user);
    void follow(String fromUser, String toUser);
    Set<String> getSecondDegreeConnections(String user);
}
