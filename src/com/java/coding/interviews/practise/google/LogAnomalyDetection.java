package com.java.coding.interviews.practise.google;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class LogAnomalyDetection {
    static class LogEntry{
        LocalDateTime timestamp;
        String level;
        String message;

        LogEntry(String timestamp, String level, String message) {
            this.timestamp = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            this.level = level;
            this.message = message;
        }
    }

    public static boolean detectAnomaly(List<LogEntry> logs, LocalDateTime currentTime) {
        TreeMap<Long, Integer> logMap = new TreeMap<>();
        for (LogEntry log : logs) {
            if(!log.level.equals("ERROR")) continue;
            long minutesAgo = Duration.between(log.timestamp, currentTime).toMinutes();
            if(minutesAgo<0 || minutesAgo>60) continue;
            long bucket = 60 - minutesAgo;
            logMap.put(bucket,logMap.getOrDefault(bucket, 0) + 1);
        }
        int currentWindowErrors=0;
        for(long key =51;key<60;key++){
            currentWindowErrors+=logMap.getOrDefault(key,0);
        }
        int pastErrors = 0;
        for(long key=1;key<=50;key++){
            pastErrors+=logMap.getOrDefault(key,0);
        }
        double pastAverage = pastErrors/5.0;
        return currentWindowErrors > 2*pastAverage && pastAverage>0;
    }

    public static void main(String[] args) {
        List<LogEntry> logs = new ArrayList<>();
        LocalDateTime now = LocalDateTime.of(2025, 7, 12, 11, 0); // Simulated current time

        // Normal behavior: 1 error every 10 mins in previous hour
        logs.add(new LogEntry("2025-07-12T10:00:00", "ERROR", "Timeout"));
        logs.add(new LogEntry("2025-07-12T10:10:00", "ERROR", "Disk error"));
        logs.add(new LogEntry("2025-07-12T10:20:00", "ERROR", "DB error"));
        logs.add(new LogEntry("2025-07-12T10:30:00", "ERROR", "OutOfMemory"));
        logs.add(new LogEntry("2025-07-12T10:40:00", "ERROR", "Network failure"));

        // Spike in the last 10 minutes
        logs.add(new LogEntry("2025-07-12T10:51:00", "ERROR", "Crash"));
        logs.add(new LogEntry("2025-07-12T10:55:00", "ERROR", "Crash"));
        logs.add(new LogEntry("2025-07-12T10:56:00", "ERROR", "Crash"));
        logs.add(new LogEntry("2025-07-12T10:57:00", "ERROR", "Crash"));
        logs.add(new LogEntry("2025-07-12T10:58:00", "ERROR", "Crash"));

        boolean anomaly = detectAnomaly(logs, now);
        System.out.println("🚨 Anomaly Detected==> " + anomaly);
    }
}


