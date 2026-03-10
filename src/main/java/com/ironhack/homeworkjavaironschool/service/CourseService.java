package com.ironhack.homeworkjavaironschool.service;

import com.ironhack.homeworkjavaironschool.model.Course;
import com.ironhack.homeworkjavaironschool.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {
    private final Map<String, Course> courses = new HashMap<>();

    public CourseService() {
        Course course = new Course("Ironhack", 44544.59);
        courses.put(course.getCourseId(), course);
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses.values());
    }

    public Course findById(String id) {
        return courses.get(id);
    }

    public Course create(String name, double price) {
        Course course = new Course(name, price);
        courses.put(course.getCourseId(), course);
        return course;
    }

    public double showProfit() {
        double sum = 0;

        for (Map.Entry<String, Course> courseEntry : courses.entrySet()) {
            double price = courseEntry.getValue().getPrice();
            sum += price;
        }

        return sum;
    }

    public double showMoneyEarned(String id) {
        Course course = courses.get(id);
        if (course != null) {
            return course.getMoney_earned();
        }
        throw new IllegalArgumentException("Course Not Found with id: " + id);
    }

    public Course partialUpdate(String id, String name, Double price, Double moneyEarned, Teacher teacher) {
        Course existingCourse = findById(id);

        if (name != null) {
            existingCourse.setName(name);
        }
        if (price != null) {
            existingCourse.setPrice(price);
        }
        if (moneyEarned != null) {
            existingCourse.setMoney_earned(moneyEarned);
        }
        if (teacher.getTeacherId() != null) {
            existingCourse.setTeacher(teacher);
        }

        return existingCourse;
    }

    public void delete(String id) {
        findById(id);
        courses.remove(id);
    }
}
