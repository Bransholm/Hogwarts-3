package kea.exercise.hogwarts3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String founder;
    private String color1;
    private String color2;

    public House(String name, String founder, String color1, String color2) {
        this.name = name;
        this.founder = founder;
        this.color1 = color1;
        this.color2 = color2;
    }
}
