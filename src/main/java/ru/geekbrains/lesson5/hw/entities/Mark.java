package ru.geekbrains.lesson5.hw.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Column(name = "title")
    private String title;

    @Tolerate
    public Mark() {}

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
