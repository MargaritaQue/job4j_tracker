package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
//- метод split() класса String - необходим для того, чтобы отделить часть строки, которая будет содержать номер задачи;
//
//- метод Integer.parseInt() - позволит преобразовать строку, содержащее номер задачи в число типа Integer;
//
//- метод Integer.compare() - позволит корректно сравнить номера задач.
    @Override
    public int compare(String left, String right) {
        String[] l = left.split("\\.");
        String[] r = right.split("\\.");
        int ll = Integer.parseInt(l[0]);
        int rr = Integer.parseInt(r[0]);
        return Integer.compare(ll, rr);
    }
}