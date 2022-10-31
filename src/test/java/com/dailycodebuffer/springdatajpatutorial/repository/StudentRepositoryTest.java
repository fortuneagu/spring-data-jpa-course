package com.dailycodebuffer.springdatajpatutorial.repository;

import com.dailycodebuffer.springdatajpatutorial.entity.Guardian;
import com.dailycodebuffer.springdatajpatutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@DataJpaTest The @SpringBootTest saves data to the database during testing however, the @DataJpaTest only saves the
// data during the testing and deletes the data after testing has been completed
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("human@gmail.com")
                .firstName("human")
                .lastName("person")
//                .guardianName("Fortune")
//                .guardianMobile("876589940")
//                .guardianEmail("fortune@gmail.com")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .email("fortune@gmail.com")
                .name("Fortune")
                .mobile("123456789")
                .build();
        Student student = Student.builder()
                .firstName("shivam")
                .emailId("shivam@gmail.com")
                .lastName("kumar")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("student List " + studentList);
    }

    @Test
    public void updateStudent(){
        Optional<Student> student = studentRepository.findById(Long.valueOf(3));
        if (student.isPresent()){
            Student studentFromDb = student.get();
            studentFromDb.setFirstName("Jamil");
            studentRepository.save(studentFromDb);
        }
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("shivam");
        System.out.println(students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("sh");
        System.out.println(students);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("demy");
        System.out.println("students = " + students);
    }

    @Test
    public void printGetStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("fahamag@gmail.com");
        System.out.println(student);
    }

    @Test
    public void printGetStudentFirstNameByEmailAddress(){
        String studentFirstName = studentRepository.getStudentFirstNameByEmailAddress("fahamag@gmail.com");
        System.out.println(studentFirstName);
    }

    @Test
    public void printGetStudentFirstNameByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("fahamag@gmail.com");
        System.out.println(student);
    }

    @Test
    public void printGetStudentFirstNameByEmailAddressNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNamedParam("fahamag@gmail.com");
        System.out.println(student);
    }

    @Test
    public void updateStudentNameByEmailIdTest(){
        studentRepository.updateStudentNameByEmailId("humanMale", "fahamag@gmail.com");
    }
}