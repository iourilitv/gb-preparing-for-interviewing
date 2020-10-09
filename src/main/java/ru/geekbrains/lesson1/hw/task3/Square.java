package ru.geekbrains.lesson1.hw.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Square implements Measurable {
    private final double edge;

    @Override
    public double area() {
        return edge * edge;
    }
}
