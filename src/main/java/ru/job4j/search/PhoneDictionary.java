package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        ArrayList<Person> result = new ArrayList<>();
        Predicate<Person> o1 = (f) -> f.getName().contains(key);
        Predicate<Person> o2 = (f) -> f.getSurname().contains(key);
        Predicate<Person> o3 = (f) -> f.getPhone().contains(key);
        Predicate<Person> o4 = (f) -> f.getAddress().contains(key);
        Predicate<Person> o5 = (f) -> o1.test(f) || o2.test(f) || o3.test(f) || o4.test(f);
        for (Person person : persons) {
              if (o5.test(person)) {
                  result.add(person);
              }
        }
        return result;
    }
}