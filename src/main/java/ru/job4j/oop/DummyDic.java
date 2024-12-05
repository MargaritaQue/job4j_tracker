package ru.job4j.oop;

public class DummyDic {
    public String engToRus(String eng) {
        String translation = "Неизвестное слово. " + eng;
        return translation;
    }

    public static void main(String[] args) {
        DummyDic dic = new DummyDic();
        String word = dic.engToRus("Hello");
        System.out.println(word);

    }
}
