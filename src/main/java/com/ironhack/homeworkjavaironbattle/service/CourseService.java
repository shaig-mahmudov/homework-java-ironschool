package com.ironhack.homeworkjavaironbattle.service;

import com.ironhack.homeworkjavaironbattle.model.Course;
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
}
