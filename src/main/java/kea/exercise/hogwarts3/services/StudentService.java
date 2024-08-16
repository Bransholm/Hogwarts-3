package kea.exercise.hogwarts3.services;

import kea.exercise.hogwarts3.entities.Student;
import kea.exercise.hogwarts3.repositories.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get one student
    public ResponseEntity<Student> getOneStudent(int id) {
        return ResponseEntity.of(studentRepository.findById(id));
    }

    // Create a new student that returns the created student object
    public Student createStudent(Student request) {
        Student student = new Student();
        setCreateStudentAttributes(student, request);
        return studentRepository.save(student);
    }

    // Sets the attributes of a new Student object during its creation by copying the relevant details from the request object.
    private void setCreateStudentAttributes(Student student, Student request) {
        student.setFirstName(request.getFirstName());
        student.setMiddleName(request.getMiddleName());
        student.setLastName(request.getLastName());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setHouse(request.getHouse());
        student.setPrefect(request.isPrefect());
        student.setEnrollmentYear(request.getEnrollmentYear());
        student.setGraduationYear(request.getGraduationYear());
        student.setGraduated(request.isGraduated());
    }



}
