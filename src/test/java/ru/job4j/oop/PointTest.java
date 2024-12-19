package ru.job4j.oop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class PointTest {
    @Test
    void when00to20then2() {
        double expected = 2;
        Point a = new Point(0, 0);
        Point b = new Point(2, 0);
        double output = a.distance(b);
        assertThat(output).isEqualTo(expected, withPrecision(0.01));
    }

    @Test
    void when01toMinus20then2() {
        double expected = 2;
        Point a = new Point(0, 1);
        Point b = new Point(-2, 1);
        double output = a.distance(b);
        assertThat(output).isEqualTo(expected, withPrecision(0.01));
    }

    @Test
    void whenMinus13to22then3dot16() {
        double expected = 3.16;
        Point a = new Point(-1, 3);
        Point b = new Point(2, 2);
        double output = a.distance(b);
        assertThat(output).isEqualTo(expected, withPrecision(0.01));
    }

}