package kea.exercise.hogwarts3.services;

import kea.exercise.hogwarts3.entities.Student;
import kea.exercise.hogwarts3.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        setStudentAttributes(student, request);
        return studentRepository.save(student);
    }

    // Sets the attributes of a Student object by copying the relevant details from the request object
    private void setStudentAttributes(Student studentToEdit, Student request) {
        studentToEdit.setFirstName(request.getFirstName());
        studentToEdit.setMiddleName(request.getMiddleName());
        studentToEdit.setLastName(request.getLastName());
        studentToEdit.setDateOfBirth(request.getDateOfBirth());
        studentToEdit.setHouse(request.getHouse());
        studentToEdit.setPrefect(request.isPrefect());
        studentToEdit.setEnrollmentYear(request.getEnrollmentYear());
        studentToEdit.setGraduationYear(request.getGraduationYear());
        studentToEdit.setGraduated(request.isGraduated());
    }

    // Update student by id and return the updated student object or throw a 404 error if the student is not found
    public Student updateStudent(Student request, int id) {
        Student studentToEdit = studentRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found, can't update"));
        setStudentAttributes(studentToEdit, request);
        return studentRepository.save(studentToEdit);
    }

    // Delete a student by id and return the deleted student object or throw a 404 error if the student is not found
    public ResponseEntity<Student> deleteOneStudent(int id) {
        Optional<Student> studentToDelete = Optional.ofNullable(studentRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found, can't delete")));
        studentRepository.deleteById(id);
        return ResponseEntity.of(studentToDelete);
    }
}