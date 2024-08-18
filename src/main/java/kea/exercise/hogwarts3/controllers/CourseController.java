package kea.exercise.hogwarts3.controllers;

import kea.exercise.hogwarts3.entities.Course;
import kea.exercise.hogwarts3.entities.Student;
import kea.exercise.hogwarts3.entities.Teacher;
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

    // Get Course/:id/teacher to get the teacher object of a course
    @GetMapping(path = "/{id}/teacher")
    public ResponseEntity<Teacher> getCourseTeacher(@PathVariable int id) {
        return courseService.getCourseTeacher(id);
    }

    // Get Course/:id/students to get a list of the students objects of a course
    @GetMapping(path = "/{id}/students")
    public ResponseEntity<List<Student>> getCourseStudents(@PathVariable int id) {
        return courseService.getCourseStudents(id);
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

    // Update a course's teacher by teacher id
    @PutMapping(path = "/{courseId}/teacher/{teacherId}")
    public Course updateCourseTeacher(@PathVariable int courseId, @PathVariable int teacherId) {
        return courseService.updateCourseTeacher(courseId, teacherId);
    }



    // Delete a course
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Course> deleteOneCourse(@PathVariable int id) {
        return courseService.deleteOneCourse(id);
    }
}
