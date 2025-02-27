package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.output.ItemDescByName;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {

    @Test
    public void whenItemAscByName() {
        Item item1 = new Item("Ivan Ivanov", 10);
        Item item2 = new Item("Mark Markov", 3);
        Item item3 = new Item("Petr Arsentev", 12);
        Item item4 = new Item("Petr Petrov", 17);
        List<Item> items = Arrays.asList(
                item2, item1, item4, item3
        );
        items.sort(new ItemAscByName());
        List<Item> expected = Arrays.asList(
                item1, item2, item3, item4
        );
        assertThat(items).isEqualTo(expected);
    }

    @Test
    public void whenDescByName() {
        Item item1 = new Item("Ivan Ivanov", 10);
        Item item2 = new Item("Mark Markov", 3);
        Item item3 = new Item("Petr Arsentev", 12);
        Item item4 = new Item("Petr Petrov", 17);
        List<Item> items = Arrays.asList(
                item2, item1, item4, item3
        );
        items.sort(new ItemDescByName());
        List<Item> expected = Arrays.asList(
                item4, item3, item2, item1
        );
        assertThat(items).isEqualTo(expected);
    }
}