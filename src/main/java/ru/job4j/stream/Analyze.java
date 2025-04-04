package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Analyze {
    public static double averageScore(Stream<Pupil> stream) {
        return stream.flatMap(pupilStream -> pupilStream.subjects().stream())
                .mapToInt(Subject::score)
                .average()
                .orElse(0d);
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream.map(pupil -> new Tuple(pupil.name(), pupil.subjects()
                .stream().mapToInt(Subject::score).average().orElse(0d)))
                .collect(Collectors.toList());
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream.flatMap(pupilStream -> pupilStream.subjects().stream())
                .collect(groupingBy(Subject::name, averagingDouble(Subject::score)))
                .entrySet()
                .stream()
                .map(subject -> new Tuple(subject.getKey(), subject.getValue()))
                .collect(Collectors.toList());
    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream.map(pupil -> new Tuple(pupil.name(), pupil.subjects()
                        .stream().mapToInt(Subject::score).sum()))
                .max(Comparator.comparing(Tuple::score))
                .orElse(null);
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream.flatMap(pupilStream -> pupilStream.subjects().stream())
                .collect(groupingBy(Subject::name, summingDouble(Subject::score)))
                .entrySet()
                .stream()
                .map(subject -> new Tuple(subject.getKey(), subject.getValue()))
                .max(Comparator.comparing(Tuple::score))
                .orElse(null);
    }
}