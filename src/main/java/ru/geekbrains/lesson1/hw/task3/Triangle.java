package ru.geekbrains.lesson1.hw.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Triangle implements Measurable {
    private final double base;
    private final double height;

    @Override
    public double area() {
        return base * height / 2;
    }
}
