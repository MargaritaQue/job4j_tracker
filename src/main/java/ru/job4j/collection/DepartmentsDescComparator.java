package ru.job4j.collection;

import java.util.Comparator;

public class DepartmentsDescComparator implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int s = (right.split("/")[0]).compareTo((left.split("/"))[0]);
        return (s == 0 ? left.compareTo(right) : s);
    }
}