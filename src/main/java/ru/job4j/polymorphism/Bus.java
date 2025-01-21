package ru.job4j.polymorphism;

public class Bus implements Transport {
    @Override
    public void move() {
        System.out.println("Едет автобус");
    }

    @Override
    public void passengers(int count) {
        System.out.println("Количество пассажиров " + count);
    }

    @Override
    public int refuel(int fuel) {
        fuel = 5;
        int price = 3;
        return price * fuel;
    }
}
