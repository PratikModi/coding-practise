package com.java.coding.interviews.practise.common;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        Map<String,String> M1 = new HashMap<>();
        Map<String,String> M2 = new HashMap<>();
        Set<String> S = M1.keySet();
        S.addAll(M2.keySet());
    }
}
