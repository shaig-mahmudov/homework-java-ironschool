package com.ironhack.homeworkjavaironschool.service;

import com.ironhack.homeworkjavaironschool.model.Course;
import com.ironhack.homeworkjavaironschool.model.Student;
import com.ironhack.homeworkjavaironschool.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    private CourseService courseService;
    private final Map<String, Student> students = new HashMap<>();
    public StudentService(){
        Student student1 = new Student("Ruzi" ,"Sumqgayit","ruzi@gmail.com");
        Student student2 = new Student("Shaiq", "Baku","shaiq@gmail.com");
        students.put(student1.getStudentId(), student1);
        students.put(student2.getStudentId(),student2);

    }
    public List<Student> findAll(){
        return new ArrayList<>(students.values());
    }
    public Student findByID(String id){
        return students.get(id);
    }
    public Student create(String name, String address, String email ){
        Student student = new Student(name,address,email);
        students.put(student.getStudentId(),student);
        return student;
    }

    public void enroll(String studentId, String courseId){
        Student student=students.get(studentId);
        Course course = courseService.findById(courseId);

        if (student == null || course == null) {
            System.out.println("Error: Student or Course not found.");
            return;
        }

        student.setCourse(course);
        double budget= course.getMoney_earned();
        budget+=course.getPrice();
        course.setMoney_earned(budget);
        System.out.println("Success: Student " + studentId + " enrolled in " + course.getName());
    }

}
