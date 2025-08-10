package ru.job4j.tracker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenReplaceExistingItemThenReturnTrueAndItemUpdated() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("original");
        tracker.add(item);
        Item newItem = new Item("updated");
        boolean replaced = tracker.replace(item.getId(), newItem);
        assertThat(replaced).isTrue();
        Item retrieved = tracker.findById(item.getId());
        assertThat(retrieved.getName()).isEqualTo("updated");
    }

    @Test
    public void whenReplaceNonExistingItemThenReturnFalse() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("nonexistent");
        boolean result = tracker.replace(9999, item);
        assertThat(result).isFalse();
    }

    @Test
    public void whenDeleteExistingItemThenItIsRemoved() throws SQLException {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("to be deleted");
        tracker.add(item);
        tracker.delete(item.getId());
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenDeleteNonExistingItemThenNothingHappens() throws SQLException {
        SqlTracker tracker = new SqlTracker(connection);
        assertThatCode(() -> tracker.delete(9999)).doesNotThrowAnyException();
    }

    @Test
    public void whenFindAllThenReturnAllItems() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        tracker.add(item1);
        tracker.add(item2);
        List<Item> items = tracker.findAll();
        assertThat(items).hasSize(2)
                .extracting(Item::getName)
                .containsExactlyInAnyOrder("item1", "item2");
    }

    @Test
    public void whenFindByNameThenReturnMatchingItems() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("commonName");
        Item item2 = new Item("commonName");
        Item item3 = new Item("otherName");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> foundItems = tracker.findByName("commonName");
        assertThat(foundItems).hasSize(2)
                .allMatch(i -> i.getName().equals("commonName"));
    }

    @Test
    public void whenFindByIdThenReturnCorrectItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("testItem");
        tracker.add(item);
        Item foundItem = tracker.findById(item.getId());
        assertThat(foundItem).isNotNull()
                .extracting(Item::getName).isEqualTo("testItem");
    }
}