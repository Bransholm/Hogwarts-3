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

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Student> getOneStudent(@PathVariable int id) {
        return studentService.getOneStudent(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody Student request) {
        return studentService.createStudent(request);
    }


}
