package com.java.coding.interviews.practise.datadog;

import java.util.*;
import java.util.stream.Collectors;

/**
 * There is a stream that has coming tags and also has a list of keywords, design a high performance filter to output
 * these keywords remaining tags.
 * For example: given stream ['apple, facebook, google', 'banana, facebook', 'facebook, google, tesla', 'intuit, google, facebook'],
 * if the keyword is ['apple'] the output should ['facebook', 'google'] because only 'apple, facebook, google' has apple.
 * Simillarly if the keyword is ['facebook', 'google'], the output should ['apple', 'tesla', 'intuit'].
 * The output can be in any order and can be put into a single list/array.
 *
 * I was not sure how to handle these:
 *
 * High performance filter.
 * The tags are coming as in a stream.
 */
public class TagSearchProblem {

    private Map<String, Set<Set<String>>> invertedIndex = new HashMap<>();

    public static void main(String[] args) {
        List<String> tagStream = List.of("apple, facebook, google", "banana, facebook", "facebook, google, tesla", "intuit, google, facebook");
        TagSearchProblem searchProblem = new TagSearchProblem();
        searchProblem.pushTag("apple, facebook, google");
        searchProblem.pushTag("banana, facebook");
        searchProblem.pushTag("facebook, google, tesla");
        searchProblem.pushTag("intuit, google, facebook");
        System.out.println(searchProblem.invertedIndex);
        System.out.println(searchProblem.searchTags(List.of("apple")));
        System.out.println(searchProblem.searchTags(List.of("google","facebook")));
        System.out.println(searchProblem.searchTags(List.of("apple","intuit")));
    }

    private void pushTag(String tags){
        Set<String> tagSet = new HashSet<>();
        for (String t : tags.split(",")) {
            tagSet.add(t.trim());
        }
        for(String t : tagSet){
            var indexedTagSet = invertedIndex.getOrDefault(t,new HashSet<>());
            indexedTagSet.add(tagSet);
            invertedIndex.put(t,indexedTagSet);
        }
    }

    public Set<String> searchTags(List<String> tags){
        Set<String> result = searchTag(tags.get(0)).stream()
                .filter(e->e.containsAll(tags))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        tags.forEach(result::remove);
        return result;
    }

    private Set<Set<String>> searchTag(String tag){
        return invertedIndex.getOrDefault(tag,new HashSet<>());
    }


}
