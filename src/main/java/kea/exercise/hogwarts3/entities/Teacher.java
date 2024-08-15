package kea.exercise.hogwarts3.entities;

import jakarta.persistence.*;
import kea.exercise.hogwarts3.enums.EmploymentEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;
    @ManyToOne
    private House house;
    private boolean headOfHouse;
    private EmploymentEnum employment;
    private Date employmentStart;
    private Date employmentEnd;

    public Teacher(String firstName, String middleName, String lastName, Date dateOfBirth, House house, boolean headOfHouse, EmploymentEnum employment, Date employmentStart, Date employmentEnd) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.headOfHouse = headOfHouse;
        this.employment = employment;
        this.employmentStart = employmentStart;
        this.employmentEnd = employmentEnd;
    }
}
