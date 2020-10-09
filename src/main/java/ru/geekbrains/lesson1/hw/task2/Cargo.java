package ru.geekbrains.lesson1.hw.task2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//added a new class
@Getter
@Setter
@AllArgsConstructor
public class Cargo {
    private double weight;
    private double volume;

    @Override
    public String toString() {
        return "Cargo{" +
                "weight=" + weight +
                ", volume=" + volume +
                '}';
    }
}
