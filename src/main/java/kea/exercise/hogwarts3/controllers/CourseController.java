package kea.exercise.hogwarts3.controllers;

import kea.exercise.hogwarts3.entities.Course;
import kea.exercise.hogwarts3.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Get one course
    @GetMapping(path = "/{id}")
    public ResponseEntity<Course> getOneCourse(@PathVariable int id) {
        return courseService.getOneCourse(id);
    }

    // Create a new course
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course addCourse(@RequestBody Course request) {
        return courseService.createCourse(request);
    }

    // Update a course
    @PutMapping(path = "/{id}")
    public Course updateCourse(@RequestBody Course request, @PathVariable int id) {
        return courseService.updateCourse(request, id);
    }

    // Delete a course
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Course> deleteOneCourse(@PathVariable int id) {
        return courseService.deleteOneCourse(id);
    }
}
