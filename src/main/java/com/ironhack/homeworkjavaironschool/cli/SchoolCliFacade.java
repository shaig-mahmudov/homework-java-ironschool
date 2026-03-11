package com.ironhack.homeworkjavaironschool.cli;

import com.ironhack.homeworkjavaironschool.model.Course;
import com.ironhack.homeworkjavaironschool.model.Student;
import com.ironhack.homeworkjavaironschool.model.Teacher;
import com.ironhack.homeworkjavaironschool.service.CourseService;
import com.ironhack.homeworkjavaironschool.service.StudentService;
import com.ironhack.homeworkjavaironschool.service.TeacherService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchoolCliFacade {
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final CourseService courseService;

    public SchoolCliFacade(TeacherService teacherService, StudentService studentService, CourseService courseService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Teacher createTeacher(String name, double salary) {
        return teacherService.create(name, salary);
    }

    public Course createCourse(String name, double price) {
        return courseService.create(name, price);
    }

    public Student createStudent(String name, String address, String email) {
        return studentService.create(name, address, email);
    }

    public List<Teacher> showTeachers() {
        return teacherService.findAll();
    }

    public List<Student> showStudents() {
        return studentService.findAll();
    }

    public List<Course> showCourses() {
        return courseService.findAll();
    }

    public Teacher lookupTeacher(String teacherId) {
        return teacherService.findById(teacherId);
    }

    public Student lookupStudent(String studentId) {
        return studentService.findByID(studentId);
    }

    public Course lookupCourse(String courseId) {
        return courseService.findById(courseId);
    }

    public void assign(String teacherId, String courseId) {
        Teacher teacher = teacherService.findById(teacherId);
        Course course = courseService.findById(courseId);

        if (teacher == null || course == null) {
            throw new IllegalArgumentException("Teacher or course not found.");
        }

        teacherService.assign(teacherId, courseId);
    }

    public void enroll(String studentId, String courseId) {
        Student student = studentService.findByID(studentId);
        Course course = courseService.findById(courseId);

        if (student == null || course == null) {
            throw new IllegalArgumentException("Student or course not found.");
        }

        student.setCourse(course);
        course.setMoney_earned(course.getMoney_earned() + course.getPrice());
    }

    public double showProfit() {
        return courseService.showProfit();
    }

    public double showMoneyEarned(String courseId) {
        return courseService.showMoneyEarned(courseId);
    }
}
