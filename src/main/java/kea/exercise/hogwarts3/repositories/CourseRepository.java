package kea.exercise.hogwarts3.repositories;

import kea.exercise.hogwarts3.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
