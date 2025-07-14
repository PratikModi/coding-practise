package com.java.coding.interviews.practise.google;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SessionizeLogs {

    static class LogEntry{
        LocalDateTime timestamp;
        String userId;
        String action;

        LogEntry(String timestamp, String userId, String action) {
            this.timestamp = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            this.userId = userId;
            this.action = action;
        }
    }

    public static Map<String,List<List<LogEntry>>> sessionizedLogs(List<LogEntry> logs, int sessionTime){
        Map<String,List<List<LogEntry>>> userSessions = new HashMap<>();
        Map<String,List<LogEntry>> logMap = new HashMap<>();

        for(LogEntry log: logs){
            logMap.putIfAbsent(log.userId,new ArrayList<>());
            logMap.get(log.userId).add(log);
        }

        for(var entry : logMap.entrySet()){
            Collections.sort(entry.getValue(), Comparator.comparing(e->e.timestamp));

            List<List<LogEntry>> sessions = new ArrayList<>();
            List<LogEntry> currentSession = new ArrayList<>();
            for(LogEntry log: entry.getValue()){
            if(currentSession.isEmpty()){
                currentSession.add(log);
            }else{
                LogEntry previous =  currentSession.get(currentSession.size() - 1);
                long gap = Duration.between(previous.timestamp, log.timestamp).toMinutes();
                if(gap <= sessionTime){
                currentSession.add(log);
                }else{
                    sessions.add(new ArrayList<>(currentSession));
                    currentSession.clear();
                    currentSession.add(log);
                }
            }
            }
            if(!currentSession.isEmpty()){
                sessions.add(currentSession);
            }
            userSessions.put(entry.getKey(),sessions);
        }
        return userSessions;
    }

    public static void main(String[] args) {
        List<LogEntry> logs = Arrays.asList(
                new LogEntry("2025-07-12T10:00:00", "user1", "/home"),
                new LogEntry("2025-07-12T10:05:00", "user1", "/search"),
                new LogEntry("2025-07-12T11:00:00", "user1", "/logout"),
                new LogEntry("2025-07-12T10:01:00", "user2", "/home"),
                new LogEntry("2025-07-12T10:40:00", "user2", "/cart"),
                new LogEntry("2025-07-12T11:20:00", "user2", "/checkout")
        );

        int sessionTimeout = 30; // ✅ You can change this to 10, 45, etc.

        Map<String, List<List<LogEntry>>> sessions = sessionizedLogs(logs, sessionTimeout);

        for (String user : sessions.keySet()) {
            System.out.println("User: " + user);
            List<List<LogEntry>> sessionList = sessions.get(user);
            for (int i = 0; i < sessionList.size(); i++) {
                System.out.println("  Session " + (i + 1) + ":");
                for (LogEntry entry : sessionList.get(i)) {
                    System.out.println("    " + entry.action);
                }
            }
        }
    }
}
