package kea.exercise.hogwarts3.repositories;

import kea.exercise.hogwarts3.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
