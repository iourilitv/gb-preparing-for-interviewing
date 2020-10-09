package ru.geekbrains.lesson1.hw.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Circle implements Measurable {
    private final double radius;

    @Override
    public double area() {
        return Math.round(Math.PI * radius * radius / 2);
    }
}
