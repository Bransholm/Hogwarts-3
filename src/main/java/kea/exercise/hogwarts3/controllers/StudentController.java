package kea.exercise.hogwarts3.controllers;

import kea.exercise.hogwarts3.entities.Student;
import kea.exercise.hogwarts3.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get one student
    @GetMapping(path = "/{id}")
    public ResponseEntity<Student> getOneStudent(@PathVariable int id) {
        return studentService.getOneStudent(id);
    }

    // Create a new student
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody Student request) {
        return studentService.createStudent(request);
    }

    // Update a student
    @PutMapping(path = "/{id}")
    public Student updateStudent(@RequestBody Student request, @PathVariable int id) {
        return studentService.updateStudent(request, id);
    }

    // Delete a student
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Student> deleteOneStudent(@PathVariable int id) {
        return studentService.deleteOneStudent(id);
    }
}
