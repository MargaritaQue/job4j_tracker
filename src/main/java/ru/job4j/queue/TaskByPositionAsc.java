package ru.job4j.queue;

import java.util.Comparator;

public class TaskByPositionAsc implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        return Integer.compare(o1.position().compareTo(Position.DIRECTOR),
                o2.position().compareTo(Position.DIRECTOR));
    }
}