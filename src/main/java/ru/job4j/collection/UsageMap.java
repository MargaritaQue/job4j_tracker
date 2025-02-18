package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("margarita.que@mail.ru", "Margarita Korol");
        map.put("parsentev@yandex.ru", "Petr Arsentev");
        map.put("margarita.que@mail.ru", "Margarita Fedorchuk");

        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(value);
        }
    }
}
