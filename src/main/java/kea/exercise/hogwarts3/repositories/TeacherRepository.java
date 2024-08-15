package kea.exercise.hogwarts3.repositories;

import kea.exercise.hogwarts3.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
