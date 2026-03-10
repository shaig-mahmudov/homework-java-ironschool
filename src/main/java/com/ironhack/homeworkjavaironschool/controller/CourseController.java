package com.ironhack.homeworkjavaironschool.controller;

import com.ironhack.homeworkjavaironschool.model.Course;
import com.ironhack.homeworkjavaironschool.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {

    private final CourseService courseService;

    public record CourseCreateRequest(String name, double price) {}

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        Course course = courseService.findById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@RequestBody CourseCreateRequest request) {
        return courseService.create(request.name(), request.price());
    }

    @GetMapping("/profit")
    public double getProfit() {
        return courseService.showProfit();
    }

    @GetMapping("/{id}/money-earned")
    public ResponseEntity<Double> getMoneyEarned(@PathVariable String id) {
        try {
            double moneyEarned = courseService.showMoneyEarned(id);
            return ResponseEntity.ok(moneyEarned);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public Course partialUpdateCourse(@PathVariable String id, @RequestBody Course course) {
        return courseService.partialUpdate(id, course.getName(), course.getPrice(), course.getMoney_earned(), course.getTeacher());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}