package kea.exercise.hogwarts3.config;

import kea.exercise.hogwarts3.entities.Course;
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

    private House gryffindor;
    private House slytherin;
    private House hufflepuff;
    private House ravenclaw;

    private Teacher severus;
    private Teacher minerva;

    private Student harry;
    private Student hermione;
    private Student ron;
    private Student neville;
    private Student ginny;
    private Student fred;
    private Student george;
    private Student percy;
    private Student draco;
    private Student cedric;
    private Student luna;
    private Student cho;

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
        createCourses();
        System.out.println("InitData has been created");
    }

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

        severus = new Teacher("Severus", "Prince", "Snape", LocalDate.of(1981, 11, 1), slytherin, true, Temporary, LocalDate.of(1996, 9, 1), LocalDate.of(1997, 6, 30));
        minerva = new Teacher("Minerva", "", "McGonagall", LocalDate.of(1935, 10, 4), gryffindor, true, Tenured, LocalDate.of(1956, 12, 1), null);

        existingTeachers.addAll(List.of(severus, minerva));
        teacherRepository.saveAll(existingTeachers);
    }

    private void createStudents() {

        Set<Student> existingStudents = new HashSet<>();
        existingStudents.addAll(studentRepository.findAll());

        harry = new Student("Harry", "James", "Potter", LocalDate.of(1980, 7, 31), gryffindor, false, 1991, 1997, false);
        hermione = new Student("Hermione", "Jean", "Granger", LocalDate.of(1979, 9, 19), gryffindor, true, 1991, 1997, false);
        ron = new Student("Ron", "Bilius", "Weasley", LocalDate.of(1980, 3, 1), gryffindor, false, 1991, 1997, false);
        neville = new Student("Neville", "Frank", "Longbottom", LocalDate.of(1980, 7, 30), gryffindor, false, 1991, 1997, false);
        ginny = new Student("Ginny", "Molly", "Weasley", LocalDate.of(1981, 8, 11), gryffindor, false, 1992, 1998, false);
        fred = new Student("Fred", "Gideon", "Weasley", LocalDate.of(1978, 4, 1), gryffindor, false, 1989, 1995, true);
        george = new Student("George", "Fabian", "Weasley", LocalDate.of(1978, 4, 1), gryffindor, false, 1989, 1995, true);
        percy = new Student("Percy", "Ignatius", "Weasley", LocalDate.of(1976, 8, 22), gryffindor, true, 1987, 1993, true);
        draco = new Student("Draco", "", "Malfoy", LocalDate.of(1980, 6, 5), slytherin, false, 1991, 1997, false);
        cedric = new Student("Cedric", "", "Diggory", LocalDate.of(1977, 9, 23), hufflepuff, true, 1989, 1995, true);
        luna = new Student("Luna", "", "Lovegood", LocalDate.of(1981, 2, 13), ravenclaw, false, 1992, 1998, false);
        cho = new Student("Cho", "", "Chang", LocalDate.of(1979, 6, 15), ravenclaw, false, 1991, 1997, false);

        existingStudents.addAll(List.of(harry, hermione, ron, neville, ginny, fred, george, percy, draco, cedric, luna, cho));
        studentRepository.saveAll(existingStudents);
    }

    private void createCourses() {
        Set<Course> existingCourses = new HashSet<>();
        existingCourses.addAll(courseRepository.findAll());

        Course potions = new Course("Potions", 5, true, severus, List.of(harry, hermione, ron, neville, ginny, fred, george, percy, draco, cedric, luna));
        Course transfiguration = new Course("Transfiguration", 5, true, minerva, List.of(harry, hermione, ron, neville, ginny, fred, george));

        existingCourses.addAll(List.of(potions, transfiguration));
        courseRepository.saveAll(existingCourses);
    }

}
