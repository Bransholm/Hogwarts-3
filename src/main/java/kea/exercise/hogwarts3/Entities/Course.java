package kea.exercise.hogwarts3.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Course {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String subject;
    private int schoolYear;
    private boolean current;
    @ManyToOne
    private Teacher teacher;
    @ManyToMany
    private List<Student> students;

    public Course(int id, String subject, int schoolYear, boolean current, Teacher teacher, List<Student> students) {
        this.subject = subject;
        this.schoolYear = schoolYear;
        this.current = current;
        this.teacher = teacher;
        this.students = students;
    }
}

