package com.nhnacademy.student.listener;

import com.nhnacademy.student.entity.Gender;
import com.nhnacademy.student.entity.Student;
import com.nhnacademy.student.repository.MapStudentRepository;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.time.LocalDateTime;

@WebListener
public class WebApplicationListener implements ServletContextListener {
    // Application 구동 시 student1 ~ student10 학생 등록하기
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();

        RandomDataGenerator generator = new RandomDataGenerator(); // 나이 : 20 ~ 30 랜덤 생성을 위한 클래스
        for(int i=1; i<=10; i++) {
            String id = "student" + i;
            String name = "아카데미" + i;
            int age = generator.nextInt(20, 30);
            Gender gender = (age % 2 == 0) ? Gender.M : Gender.F;
            LocalDateTime createdAt = LocalDateTime.now();

            Student student = new Student(id, name, gender, age, createdAt);
            studentRepository.save(student);
        }
        // application scope 에서 studentRepository 객체에 접근할 수 있도록 구현하기
        context.setAttribute("studentRepository", studentRepository);
    }
}
