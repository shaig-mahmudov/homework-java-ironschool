package com.ironhack.homeworkjavaironschool.service;

import com.ironhack.homeworkjavaironschool.exception.ResourceNotFoundException;
import com.ironhack.homeworkjavaironschool.model.Course;
import com.ironhack.homeworkjavaironschool.model.Student;
import com.ironhack.homeworkjavaironschool.model.Teacher;
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
    public StudentService(CourseService courseService){
        this.courseService = courseService;

        Student student1 = new Student("Ruzi" ,"Sumqgayit","ruzi@gmail.com");
        Student student2 = new Student("Shaig", "Baku","shaig@gmail.com");
        students.put(student1.getStudentId(), student1);
        students.put(student2.getStudentId(),student2);

    }
    public List<Student> findAll(){
        return new ArrayList<>(students.values());
    }

    public Student findByID(String id){
        Student student = students.get(id);

        if (student == null) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }

        return student;
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

    public Student partialUpdate(String id, String name, String address, String email, Course course) {
        Student existingStudent = findByID(id);

        if (name != null) {
            existingStudent.setName(name);
        }
        if (address != null) {
            existingStudent.setAddress(address);
        }
        if (email != null) {
            existingStudent.setEmail(email);
        }
        if (course.getCourseId() != null) {
            existingStudent.setCourse(course);
        }

        return existingStudent;
    }

    public void delete(String id) {
        Student existingStudent = findByID(id);

        if (existingStudent == null) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }

        students.remove(id);
    }

}
