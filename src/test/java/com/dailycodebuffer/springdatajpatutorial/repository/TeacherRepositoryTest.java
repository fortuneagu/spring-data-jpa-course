package com.dailycodebuffer.springdatajpatutorial.repository;

import com.dailycodebuffer.springdatajpatutorial.entity.Course;
import com.dailycodebuffer.springdatajpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){
        Course course = Course.builder()
                .title("DBA")
                .credit(5)
                .build();

        Course course2 = Course.builder()
                .title("Java")
                .credit(6)
                .build();


        Teacher teacher = Teacher.builder()
                .firstName("Usman")
                .lastName("Black")
//                .courses(List.of(course, course2))
                .build();

        teacherRepository.save(teacher);
    }

}