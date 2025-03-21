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
        Predicate<Person> predicateName = (f) -> f.getName().contains(key);
        Predicate<Person> predicateSurname = (f) -> f.getSurname().contains(key);
        Predicate<Person> predicatePhone = (f) -> f.getPhone().contains(key);
        Predicate<Person> predicateAddress = (f) -> f.getAddress().contains(key);
        Predicate<Person> predicateAll = predicateName.or(predicateSurname).or(predicatePhone).or(predicateAddress);
        for (Person person : persons) {
              if (predicateAll.test(person)) {
                  result.add(person);
              }
        }
        return result;
    }
}