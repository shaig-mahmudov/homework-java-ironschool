package com.ironhack.homeworkjavaironschool.controller;

import com.ironhack.homeworkjavaironschool.model.Course;
import com.ironhack.homeworkjavaironschool.model.Teacher;
import com.ironhack.homeworkjavaironschool.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Teacher> getAllTeachers() {
        return teacherService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Teacher getTeacherById(@PathVariable String id) {
        Teacher teacher = teacherService.findById(id);
        if (teacher == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found with ID: " + id);
        }
        return teacher;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.create(teacher.getName(), teacher.getSalary());
    }

    @PostMapping("/assign/{teacherId}/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public void assignTeacher(@PathVariable String teacherId, @PathVariable String courseId) {
        try {
            teacherService.assign(teacherId, courseId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Assign denied: " + e.getMessage(), e);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher or course not found for provided IDs", e);
        }
    }

    @PatchMapping("/{id}")
    public Teacher partialUpdateCourse(@PathVariable String id, @RequestBody Teacher teacher) {
        return teacherService.partialUpdate(id, teacher.getName(), teacher.getSalary());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
