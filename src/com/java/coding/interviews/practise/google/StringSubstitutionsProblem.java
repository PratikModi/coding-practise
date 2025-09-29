package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3481. Apply Substitutions
 * You are given a replacements mapping and a text string that may contain placeholders formatted as %var%, where each var corresponds to a key in the replacements mapping. Each replacement value may itself contain one or more such placeholders. Each placeholder is replaced by the value associated with its corresponding replacement key.
 *
 * Return the fully substituted text string which does not contain any placeholders.
 *
 *
 *
 * Example 1:
 *
 * Input: replacements = [["A","abc"],["B","def"]], text = "%A%_%B%"
 *
 * Output: "abc_def"
 *
 * Explanation:
 *
 * The mapping associates "A" with "abc" and "B" with "def".
 * Replace %A% with "abc" and %B% with "def" in the text.
 * The final text becomes "abc_def".
 * Example 2:
 *
 * Input: replacements = [["A","bce"],["B","ace"],["C","abc%B%"]], text = "%A%_%B%_%C%"
 *
 * Output: "bce_ace_abcace"
 *
 * Explanation:
 *
 * The mapping associates "A" with "bce", "B" with "ace", and "C" with "abc%B%".
 * Replace %A% with "bce" and %B% with "ace" in the text.
 * Then, for %C%, substitute %B% in "abc%B%" with "ace" to obtain "abcace".
 * The final text becomes "bce_ace_abcace".
 */
public class StringSubstitutionsProblem {

    public static void main(String[] args) {
        List<List<String>> replacements = new ArrayList<>();
        replacements.add(List.of("A","abc"));
        replacements.add(List.of("B","def"));
        replacements.add(List.of("C","ghi%B"));
        String text = "%A%_%B%_%C%";
        System.out.println(applySubstitutions(replacements,text));
    }

    public static String applySubstitutions(List<List<String>> replacements, String text) {
        Map<String,String> replacementMap = new HashMap();
        for(List<String> list : replacements){
            replacementMap.put(list.get(0),list.get(1));
        }
        while(text.contains("%")){
            StringBuilder output = new StringBuilder();
            String[] split = text.split("%");
            for(int i=0;i<split.length;i++){
                if(i%2==0){
                    output.append(split[i]);
                }else{
                    output.append(replacementMap.get(split[i]));
                }
            }
            text=output.toString();
        };
        return text;
    }

}
