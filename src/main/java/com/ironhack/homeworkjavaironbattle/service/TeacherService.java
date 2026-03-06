package com.ironhack.homeworkjavaironbattle.service;

import com.ironhack.homeworkjavaironbattle.model.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherService {
    private final Map<String, Teacher> teachers = new HashMap<>();

    public TeacherService() {
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
}
