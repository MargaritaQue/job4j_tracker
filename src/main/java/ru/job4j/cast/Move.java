package ru.job4j.cast;

public class Move {
    public static void main(String[] args) {
        Vehicle school = new Bus();
        Vehicle sapsan = new Train();
        Vehicle aviakor = new Airplane();

        Vehicle[] vehicles = new Vehicle[]{school, sapsan, aviakor};
        for (Vehicle object : vehicles) {
            object.move();
        }
    }
}
