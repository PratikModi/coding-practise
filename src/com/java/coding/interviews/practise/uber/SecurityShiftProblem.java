package com.java.coding.interviews.practise.uber;

import java.util.*;

/**
 * Consolidate Security Gaurd Shifts
 *
 * A list of individual security guard shifts will be provided as input in the format [tStart, tEnd, name].
 *
 * Return a consolidated list in such a way that no timestamp is overlapped.
 *
 * Input
 * 1 - 10 | S 1
 * 1 - 15 | S 2
 * 1 - 13 | S 5
 * 3 - 7 | S 3
 * 12 - 15 | S 4
 * Output
 * 1 - 3 | S 1, S 2, S 5
 * 3 - 7 | S 1, S 2, S 5, S 3
 * 7 - 10 | S 1, S 2, S 5
 * 10 - 12 | S 5, S 2
 * 12 - 13 | S 5, S 2, S 4
 * 13 - 15 | S 4, S 2
 */
public class SecurityShiftProblem {

    public static void main(String[] args) {
        Security S1 = new Security("S1",new Timing(1,10));
        Security S2 = new Security("S2",new Timing(1,15));
        Security S3 = new Security("S5",new Timing(1,13));
        Security S4 = new Security("S3",new Timing(3,7));
        Security S5 = new Security("S4",new Timing(12,15));
        List<Security> securities = List.of(S1,S2,S3,S4,S5);
        consolidateSecurities(securities);
    }

    public static void consolidateSecurities(List<Security> securities){
        Map<Timing,List<String>> availabilityMap = new LinkedHashMap<>();
        for(Security security : securities){
            for(int i=security.timing.start;i<security.timing.end;i++){
                int start=i, end=i+1;
                availabilityMap.putIfAbsent(new Timing(start,end),new ArrayList<>());
                availabilityMap.get(new Timing(start,end)).add(security.name);
            }
        }
        //System.out.println(availabilityMap);
        Map<Timing,List<String>> consolidateSecurity = new LinkedHashMap<>();
        Map.Entry<Timing,List<String>> previousEntry = null;
        Map.Entry<Timing,List<String>> nextEntry = null;
        for(Map.Entry<Timing,List<String>> entry : availabilityMap.entrySet()){
          /*  System.out.println("Previous Entry");
            System.out.println(previousEntry);
            System.out.println("Entry");
            System.out.println(entry);*/
            if(previousEntry==null){
                previousEntry=entry;
                continue;
            }
            if(!previousEntry.getValue().equals(entry.getValue())){
                consolidateSecurity.put(new Timing(previousEntry.getKey().start,entry.getKey().start),previousEntry.getValue());
                previousEntry=entry;
            }else{
                if(nextEntry!=null){
                    nextEntry.getKey().start=previousEntry.getKey().start;
                    nextEntry.getKey().end = entry.getKey().end;
                    nextEntry.setValue(entry.getValue());
                }else{
                    nextEntry=entry;
                }
            }
        }
        if(nextEntry!=null){
            consolidateSecurity.put(new Timing(nextEntry.getKey().start,nextEntry.getKey().end),nextEntry.getValue());
        }
        System.out.println(consolidateSecurity);
    }

}

class Security{
    String name;
    Timing timing;

    public Security(String name, Timing timing) {
        this.name = name;
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "Security{" +
                "name='" + name + '\'' +
                ", timing=" + timing +
                '}';
    }
}
class Timing{
    int start;
    int end;

    public Timing(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timing timing = (Timing) o;
        return start == timing.start && end == timing.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "Timing{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}


