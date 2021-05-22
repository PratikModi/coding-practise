package com.java.amazon.dynamic.twilio;

import java.util.*;

/**
 * Search for a pattern 'TWLO' which maps to a number (T9 keyboard) in list of phone numbers
 * input:
 * vanityCodes: ['TWLO','FLOWERS',.....]
 * phoneNums: ['12345678',....]
 * output: return list of numbers which have at least one vanity code in them.
 */
public class PatternToPhoneMapProblem {

    private static final Map<Character,Integer> MAP = new HashMap<>();

    static{
        MAP.put('A',2);
        MAP.put('B',2);
        MAP.put('C',2);
        MAP.put('D',3);
        MAP.put('E',3);
        MAP.put('F',3);
        MAP.put('G',4);
        MAP.put('H',4);
        MAP.put('I',4);
        MAP.put('J',5);
        MAP.put('K',5);
        MAP.put('L',5);
        MAP.put('M',6);
        MAP.put('N',6);
        MAP.put('O',6);
        MAP.put('P',7);
        MAP.put('Q',7);
        MAP.put('R',7);
        MAP.put('S',8);
        MAP.put('T',8);
        MAP.put('U',8);
        MAP.put('V',8);
        MAP.put('W',9);
        MAP.put('X',9);
        MAP.put('Y',9);
        MAP.put('Z',9);
    }

    public static void main(String[] args) {
        List<String> pattern = Arrays.asList("TWLO", "CODE", "HTCH");
        List<String> numbers = Arrays.asList("+17474824380", "+14157088956", "+919810155555", "+15109926333", "+1415123456");
        System.out.println(findMatchingNumbers(numbers,pattern));
    }

    public static Set<String> findMatchingNumbers(List<String> numbers, List<String> pattern){
        Set<String> result = new HashSet<>();
        for(String p : pattern){
            StringBuilder SB = new StringBuilder();
            for(char c : p.toCharArray()){
                SB.append(MAP.get(c));
            }
            for(String N : numbers){
                if(!result.contains(N)){
                    if(N.contains(SB.toString()))
                        result.add(N);
                }
            }
        }
        return result;
    }

}
