package com.ironhack.homeworkjavaironbattle.controlller;

import com.ironhack.homeworkjavaironbattle.model.Teacher;
import com.ironhack.homeworkjavaironbattle.service.TeacherService;
import org.springframework.http.HttpStatus;
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
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Assign denied");
        }
    }
}