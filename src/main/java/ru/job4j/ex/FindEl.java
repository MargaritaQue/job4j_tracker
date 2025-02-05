package ru.job4j.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int result = -1;
            for (int i = 0; i < value.length - 1; i++) {
                if (value[i].equals(key)) {
                    result = i;
                }
            }
        if (result == -1) {
            throw new ElementNotFoundException("Key not found");
        }
        return result;
    }

    public static void main(String[] args) {
        String[] value = {"a", "b"};
        try {
            indexOf(value, "c");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}