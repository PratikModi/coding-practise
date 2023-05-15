package com.java.coding.interviews.practise.uber;

import java.util.Objects;

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

    }

//    public List

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


