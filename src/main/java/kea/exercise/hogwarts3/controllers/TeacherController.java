package kea.exercise.hogwarts3.controllers;

import kea.exercise.hogwarts3.entities.Teacher;
import kea.exercise.hogwarts3.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // Get all teachers
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    // Get one teacher
    @GetMapping(path = "/{id}")
    public ResponseEntity<Teacher> getOneTeacher(@PathVariable int id) {
        return teacherService.getOneTeacher(id);
    }

    // Create a new teacher
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher addTeacher(@RequestBody Teacher request) {
        return teacherService.createTeacher(request);
    }

    // Update a teacher
    @PutMapping(path = "/{id}")
    public Teacher updateTeacher(@RequestBody Teacher request, @PathVariable int id) {
        return teacherService.updateTeacher(request, id);
    }

    // Delete a teacher
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Teacher> deleteOneTeacher(@PathVariable int id) {
        return teacherService.deleteOneTeacher(id);
    }
}