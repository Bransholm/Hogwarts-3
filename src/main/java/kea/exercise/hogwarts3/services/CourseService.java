package kea.exercise.hogwarts3.services;

import kea.exercise.hogwarts3.entities.Course;
import kea.exercise.hogwarts3.entities.Student;
import kea.exercise.hogwarts3.entities.Teacher;
import kea.exercise.hogwarts3.repositories.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get one course
    public ResponseEntity<Course> getOneCourse(int id) {
        return ResponseEntity.of(courseRepository.findById(id));
    }

    // Get Course/:id/teacher to get the teacher object of a course
    public ResponseEntity<Teacher> getCourseTeacher(int id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            Teacher teacher = course.get().getTeacher();
            return ResponseEntity.of(Optional.of(teacher));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found, can't get teacher");
        }
    }

    // Get Course/:id/students to get a list of the students objects of a course
    public ResponseEntity<List<Student>> getCourseStudents(int id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            List<Student> students = course.get().getStudents();
            return ResponseEntity.of(Optional.of(students));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found, can't get students");
        }
    }

    // Create a new course that returns the created course object
    public Course createCourse(Course request) {
        Course course = new Course();
        setCourseAttributes(course, request);
        return courseRepository.save(course);
    }

    // Sets the attributes of a Course object by copying the relevant details from the request object
    private void setCourseAttributes(Course courseToEdit, Course request) {
        courseToEdit.setSubject(request.getSubject());
        courseToEdit.setSchoolYear(request.getSchoolYear());
        courseToEdit.setCurrent(request.isCurrent());
        courseToEdit.setTeacher(request.getTeacher());
        courseToEdit.setStudents(request.getStudents());

    }

    // Update course by id and return the updated course object or throw a 404 error if the course is not found
    public Course updateCourse(Course request, int id) {
        Course courseToEdit = courseRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found, can't update"));
        setCourseAttributes(courseToEdit, request);
        return courseRepository.save(courseToEdit);
    }

    // Delete a course by id and return the deleted course object or throw a 404 error if the course is not found
    public ResponseEntity<Course> deleteOneCourse(int id) {
        Optional<Course> courseToDelete = Optional.ofNullable(courseRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found, can't delete")));
        courseRepository.deleteById(id);
        return ResponseEntity.of(courseToDelete);
    }

    // Update a course's teacher by teacher id
    public Course updateCourseTeacher(int courseId, int teacherId) {
        Course courseToEdit = courseRepository.findById(courseId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found, can't update teacher"));
        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        courseToEdit.setTeacher(teacher);
        return courseRepository.save(courseToEdit);
    }

    public Course addStudentToCourse(int courseId, int studentId) {
        Course courseToEdit = courseRepository.findById(courseId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found, can't add student"));
        boolean studentExists = courseToEdit.getStudents().stream()
                .anyMatch(s -> s.getId() == studentId);
        if (!studentExists) {
            Student student = new Student();
            student.setId(studentId);
            courseToEdit.getStudents().add(student);
            return courseRepository.save(courseToEdit);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Student already enrolled in the course");
        }
    }

    // Remove a student from a course
    public Course removeStudentFromCourse(int courseId, int studentId) {
        Course courseToEdit = courseRepository.findById(courseId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found, can't remove student"));
        boolean studentExists = courseToEdit.getStudents().removeIf(s -> s.getId() == studentId);
        if (studentExists) {
            return courseRepository.save(courseToEdit);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not enrolled in the course");
        }
    }

    // Remove a teacher from a course
    public Course removeTeacherFromCourse(int courseId) {
        Course courseToEdit = courseRepository.findById(courseId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found, can't remove teacher"));
        courseToEdit.setTeacher(null);
        return courseRepository.save(courseToEdit);
    }
}
