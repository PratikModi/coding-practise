package com.java.coding.interviews.practise.second.uber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static int skipChar(String s, int col, String regex) {
        if (s == null || s.isEmpty()) {
            return col;
        }
        int j = col;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s.substring(j));
        while (j < s.length() && matcher.lookingAt()) {
            j += matcher.end();
            matcher.reset(s.substring(j));
        }
        return j;
    }

    private static int parse(String s, int col) throws ValueError {
        int j = skipChar(s, 0, "\\s");
        if (j >= s.length()) {
            return j;
        }
        //add(sub(3,4), )
        if (s.substring(j, Math.min(j + 3, s.length())).equals("add") ||
                s.substring(j, Math.min(j + 3, s.length())).equals("sub")) {
            j = skipChar(s, j + 3, "\\s");
            if (s.charAt(j) != '(') {
                throw new ValueError("No braces starting at col " + (col + j));
            }
            j += parse(s.substring(j + 1), col + j + 1);
            j = skipChar(s, j, "\\s");
            if (j >= s.length() || (j < s.length() && s.charAt(j) != ',')) {
                throw new ValueError("Missing 1 , at col " + (col + j));
            }
            j += parse(s.substring(j + 1), col + j + 1);
        } else if (Character.isDigit(s.charAt(j))) {
            j = skipChar(s, j, "\\d");
            j = skipChar(s, j, "\\s");

            if (j >= s.length() || s.charAt(j) == ',' || s.charAt(j) == ')') {
                return j + 1;
            } else {
                throw new ValueError("Inval char at col " + (col + j));
            }
        } else {
            throw new ValueError("Bad op at col " + (col + j));
        }

        j = skipChar(s, j, "\\s");
        if (j >= s.length() || s.charAt(j) != ')') {
            throw new ValueError("Missing ) at col " + (col + j));
        }
        return j + 2;
    }

    public static void parseStr(String s) {
        try {
            int ret = parse(s, 0);
            if (ret < s.length()) {
                throw new ValueError("Extra chars at col " + ret);
            }
            System.out.println("Parsed successfully.");
        } catch (ValueError ve) {
            System.out.println(ve);
        }
    }

    public static void main(String[] args) {
        parseStr("  add(sub(3,4), ) ");
        //parseStr(" sub(add(238943, 2343), add(1, sub(323, 43))) ");
        //parseStr(" sub(add(238943a, 2343), add(1, sub(323, 43))) ");  // Inval char at col 15
        //parseStr(" sub(add(238943, 2343, sub(1,2)), add(1, sub(323, 43)))) ");  // Missing ) at col 21
        //parseStr(" sub(add(238943, 2343), add(1, sub(323, 43)))) ");  // Extra chars at col 46
        //parseStr("foo(1, 2)");  // Bad op at col 0
        //parseStr("add(a, 3)");  // Bad op at col 4
    }

    static class ValueError extends Exception {
        public ValueError(String message) {
            super(message);
        }
    }
}