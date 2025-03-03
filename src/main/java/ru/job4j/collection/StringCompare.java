package ru.job4j.collection;

import java.util.Comparator;

import static java.lang.Math.min;

public class StringCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        for (int i = 0; i < min(left.length(), right.length()); i++) {
            char l = left.charAt(i);
            char r = right.charAt(i);
            if (l != r) {
                return Character.compare(l, r);
            }
        }
        return Integer.compare(left.length(), right.length());
    }
}
