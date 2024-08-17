package kea.exercise.hogwarts3.services;

import kea.exercise.hogwarts3.entities.Teacher;
import kea.exercise.hogwarts3.repositories.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // Get all teachers
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // Get one teacher
    public ResponseEntity<Teacher> getOneTeacher(int id) {
        return ResponseEntity.of(teacherRepository.findById(id));
    }

    // Create a new teacher that returns the created teacher object
    public Teacher createTeacher(Teacher request) {
        Teacher teacher = new Teacher();
        setTeacherAttributes(teacher, request);
        return teacherRepository.save(teacher);
    }

    // Sets the attributes of a Teacher object by copying the relevant details from the request object
    private void setTeacherAttributes(Teacher teacherToEdit, Teacher request) {
        teacherToEdit.setFirstName(request.getFirstName());
        teacherToEdit.setMiddleName(request.getMiddleName());
        teacherToEdit.setLastName(request.getLastName());
        teacherToEdit.setDateOfBirth(request.getDateOfBirth());
        teacherToEdit.setHouse(request.getHouse());
        teacherToEdit.setHeadOfHouse(request.isHeadOfHouse());
        teacherToEdit.setEmployment(request.getEmployment());
        teacherToEdit.setEmploymentStart(request.getEmploymentStart());
        teacherToEdit.setEmploymentEnd(request.getEmploymentEnd());
    }

    // Update teacher by id and return the updated teacher object or throw a 404 error if the teacher is not found
    public Teacher updateTeacher(Teacher request, int id) {
        Teacher teacherToEdit = teacherRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found, can't update"));
        setTeacherAttributes(teacherToEdit, request);
        return teacherRepository.save(teacherToEdit);
    }

    // Delete a teacher by id and return the deleted teacher object or throw a 404 error if the teacher is not found
    public ResponseEntity<Teacher> deleteOneTeacher(int id) {
        Optional<Teacher> teacherToDelete = Optional.ofNullable(teacherRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found, can't delete")));
        teacherRepository.deleteById(id);
        return ResponseEntity.of(teacherToDelete);
    }
}