package ru.job4j.hashmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        int sum = 0;
        int size = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                sum += subject.score();
                size++;
            }
        }
        return (double) sum / size;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        ArrayList<Label> s = new ArrayList<>();
        for (Pupil pupil : pupils) {
            int sum = 0;
            for (Subject subject : pupil.subjects()) {
                sum += subject.score();
            }
            Label name = new Label(pupil.name(), (double) sum / pupil.subjects().size());
            s.add(name);
        }
        return s;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> sub = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                sub.merge(subject.name(), subject.score(), Integer::sum);
            }
        }
        ArrayList<Label> labels = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sub.entrySet()) {
            Label res = new Label(entry.getKey(), (double) entry.getValue() / pupils.size());
            labels.add(res);
        }
        return labels;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        LinkedList<Label> labels = new LinkedList<>();
        for (Pupil pupil : pupils) {
            int sum = 0;
            for (Subject subject : pupil.subjects()) {
                sum += subject.score();
            }
            Label name = new Label(pupil.name(), sum);
            labels.add(name);
        }
        labels.sort(Comparator.naturalOrder());
        return labels.getLast();
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> sub = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                sub.getOrDefault(subject.name(), null);
                sub.put(subject.name(), subject.score() + sub.get(subject.name()));
            }
        }
        LinkedList<Label> labels = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : sub.entrySet()) {
            Label res = new Label(entry.getKey(), entry.getValue());
            labels.add(res);
        }
        labels.sort(Comparator.naturalOrder());
        return labels.getLast();
    }
}