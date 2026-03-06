package com.ironhack.homeworkjavaironbattle.service;

import com.ironhack.homeworkjavaironbattle.model.Course;
import com.ironhack.homeworkjavaironbattle.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherService {
    private final CourseService courseService;

    private final Map<String, Teacher> teachers = new HashMap<>();
    private final Map<Teacher, Course> assignedTeachers = new HashMap<>();

    public TeacherService(CourseService courseService) {
        this.courseService = courseService;

        Teacher teacher = new Teacher("Joao", 45.59);
        teachers.put(teacher.getTeacherId(), teacher);
    }

    public List<Teacher> findAll() {
        return new ArrayList<>(teachers.values());
    }

    public Teacher create(String name, double price) {
        Teacher teacher = new Teacher(name, price);
        teachers.put(teacher.getTeacherId(), teacher);
        return teacher;
    }

    public Teacher findById(String id) {
        return teachers.get(id);
    }

    public List<Teacher> findByName(String name) {
        return teachers.values().stream()
                .filter(c -> c.getName().toLowerCase()
                        .contains(name.toLowerCase())).toList();
    }

    public void assign(String teacherId, String courseId) {
        Teacher teacher = teachers.get(teacherId);
        Course course = courseService.findById(courseId);

        course.setTeacher(teacher);

        assignedTeachers.put(teacher, course);

    }
}
