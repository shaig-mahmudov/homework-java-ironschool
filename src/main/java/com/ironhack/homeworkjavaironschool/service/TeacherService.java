package com.ironhack.homeworkjavaironschool.service;

import com.ironhack.homeworkjavaironschool.exception.ResourceNotFoundException;
import com.ironhack.homeworkjavaironschool.model.Course;
import com.ironhack.homeworkjavaironschool.model.Teacher;
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
        Teacher teacher = teachers.get(id);

        if (teacher == null) {
            throw new ResourceNotFoundException("Teacher not found with id: " + id);
        }

        return teacher;
    }

    public List<Teacher> findByName(String name) {
        return teachers.values().stream()
                .filter(c -> c.getName().toLowerCase()
                        .contains(name.toLowerCase())).toList();

    }

    public void assign(String teacherId, String courseId) {
        Teacher teacher = teachers.get(teacherId);
        Course course = courseService.findById(courseId);

        if (teacher == null) {
            throw new ResourceNotFoundException("Teacher not found with:" + teacherId);
        }
        if (course == null) {
            throw new ResourceNotFoundException("Course not found with:" + courseId);
        }

        course.setTeacher(teacher);

        assignedTeachers.put(teacher, course);

    }

    public Teacher partialUpdate(String id, String name, Double salary) {
        Teacher existingTeacher = findById(id);

        if (name != null) {
            existingTeacher.setName(name);
        }
        if (salary != null) {
            existingTeacher.setSalary(salary);
        }

        return existingTeacher;
    }

    public void delete(String id) {
        Teacher existingTeacher = findById(id);

        if (existingTeacher == null) {
            throw new ResourceNotFoundException("Teacher not found with id: " + id);
        }

        teachers.remove(id);
        assignedTeachers.remove(existingTeacher);
    }
}
