package kea.exercise.hogwarts3.config;

import kea.exercise.hogwarts3.entities.House;
import kea.exercise.hogwarts3.entities.Student;
import kea.exercise.hogwarts3.entities.Teacher;
import kea.exercise.hogwarts3.repositories.CourseRepository;
import kea.exercise.hogwarts3.repositories.HouseRepository;
import kea.exercise.hogwarts3.repositories.StudentRepository;
import kea.exercise.hogwarts3.repositories.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static kea.exercise.hogwarts3.enums.EmploymentEnum.Temporary;
import static kea.exercise.hogwarts3.enums.EmploymentEnum.Tenured;

@Component
public class InitData implements CommandLineRunner {

    private final HouseRepository houseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public InitData(HouseRepository houseRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.houseRepository = houseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createHouses();
        createTeachers();
        createStudents();
        System.out.println("InitData have been created");
    }

    private House gryffindor;
    private House slytherin;
    private House hufflepuff;
    private House ravenclaw;

    private void createHouses() {
        gryffindor = new House("Gryffindor", "Godric Gryffindor", "Red", "Gold");
        slytherin = new House("Slytherin", "Salazar Slytherin", "Green", "Silver");
        hufflepuff = new House("Hufflepuff", "Helga Hufflepuff", "Yellow", "Black");
        ravenclaw = new House("Ravenclaw", "Rowena Ravenclaw", "Blue", "Bronze");

        houseRepository.save(gryffindor);
        houseRepository.save(slytherin);
        houseRepository.save(hufflepuff);
        houseRepository.save(ravenclaw);
    }

    private void createTeachers() {
        Set<Teacher> existingTeachers = new HashSet<>();
        existingTeachers.addAll(teacherRepository.findAll());

        Teacher severus = new Teacher("Severus", "Prince", "Snape", LocalDate.of(1981, 11, 1), slytherin, true, Temporary, LocalDate.of(1996, 9, 1), LocalDate.of(1997, 6, 30));
        Teacher minerva = new Teacher("Minerva", "", "McGonagall", LocalDate.of(1935, 10, 4), gryffindor, true, Tenured, LocalDate.of(1956, 12, 1), null);

        existingTeachers.addAll(List.of(severus, minerva));
        teacherRepository.saveAll(existingTeachers);
    }

    private void createStudents() {

        Set<Student> existingStudents = new HashSet<>();
        existingStudents.addAll(studentRepository.findAll());

        Student harry = new Student("Harry", "James", "Potter", null, gryffindor, false, 1991, 1997, false);
        Student hermione = new Student("Hermione", "Jean", "Granger", null, gryffindor, true, 1991, 1997, false);
        Student ron = new Student("Ron", "Bilius", "Weasley", null, gryffindor, false, 1991, 1997, false);
        Student neville = new Student("Neville", "Frank", "Longbottom", null, gryffindor, false, 1991, 1997, false);
        Student ginny = new Student("Ginny", "Molly", "Weasley", null, gryffindor, false, 1992, 1998, false);
        Student fred = new Student("Fred", "Gideon", "Weasley", null, gryffindor, false, 1989, 1995, true);
        Student george = new Student("George", "Fabian", "Weasley", null, gryffindor, false, 1989, 1995, true);
        Student percy = new Student("Percy", "Ignatius", "Weasley", null, gryffindor, true, 1987, 1993, true);
        Student draco = new Student("Draco", "", "Malfoy", null, slytherin, false, 1991, 1997, false);
        Student cedric = new Student("Cedric", "", "Diggory", null, hufflepuff, true, 1989, 1995, true);
        Student luna = new Student("Luna", "", "Lovegood", null, ravenclaw, false, 1992, 1998, false);
        Student cho = new Student("Cho", "", "Chang", null, ravenclaw, false, 1991, 1997, false);

        existingStudents.addAll(List.of(harry, hermione, ron, neville, ginny, fred, george, percy, draco, cedric, luna, cho));
        studentRepository.saveAll(existingStudents);
    }








}
