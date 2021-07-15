package com.java.coding.interviews.practise.amazon;

import java.util.*;

/**
 * You are given an array of logs. Each log is a space-delimited string of words, where the first word is the identifier.
 *
 * There are two types of logs:
 *
 * Letter-logs: All words (except the identifier) consist of lowercase English letters.
 * Digit-logs: All words (except the identifier) consist of digits.
 * Reorder these logs so that:
 *
 * The letter-logs come before all digit-logs.
 * The letter-logs are sorted lexicographically by their contents. If their contents are the same, then sort them lexicographically by their identifiers.
 * The digit-logs maintain their relative ordering.
 * Return the final order of the logs.
 *
 *
 *
 * Example 1:
 *
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 * Explanation:
 * The letter-log contents are all different, so their ordering is "art can", "art zero", "own kit dig".
 * The digit-logs have a relative order of "dig1 8 1 5 1", "dig2 3 6".
 * Example 2:
 *
 * Input: logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 * Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 */
public class ReOrderLogFileProblem {
    public static void main(String[] args) {
        String[] logs = new String[]{"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        System.out.println(Arrays.toString(reOrderLogFiles(logs)));
    }

    public static String[] reOrderLogFiles(String[] logs){
        Set<LogPair> pairs = new TreeSet<>(new LogComparator());
        List<String> digitLogs = new ArrayList<>();
        for(String log : logs){
            String key = log.substring(0,log.indexOf(" "));
            String value = log.substring(log.indexOf(" ")+1);
            if(Character.isLetter(value.charAt(0))){
                pairs.add(new LogPair(key,value,log));
            }else{
                digitLogs.add(log);
            }
        }
        String[] result = new String[pairs.size()+digitLogs.size()];
        int index=0;
        for(LogPair log : pairs){
            result[index++] = log.S;
        }
        for(String log : digitLogs){
            result[index++] = log;
        }
        return result;
    }
}

class LogComparator implements Comparator<LogPair>{
    @Override
    public int compare(LogPair logPair, LogPair t1) {
        int value = logPair.value.compareTo(t1.value);
        if(value==0)
            return logPair.key.compareTo(t1.key);
        return value;
    }
}

class LogPair {
    String key;
    String value;
    String S;

    public LogPair(String key, String value, String s) {
        this.key = key;
        this.value = value;
        S = s;
    }

    @Override
    public String toString() {
        return "LogPair{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", S='" + S + '\'' +
                '}';
    }
}
