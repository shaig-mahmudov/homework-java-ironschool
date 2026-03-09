package com.ironhack.homeworkjavaironschool.controller;


import com.ironhack.homeworkjavaironschool.model.Student;
import com.ironhack.homeworkjavaironschool.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService){this.studentService=studentService;}

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents(){return studentService.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentById(@PathVariable String id){
        Student student= studentService.findByID(id);
        if (student == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Studnet not found with ID: " + id);
        }
        return student;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        return studentService.create(student.getName(),student.getAddress(),student.getEmail());
    }
    @PostMapping("/enroll/{studentId}/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public void enrollCourse(@PathVariable String studentId, @PathVariable String courseId){
        try{
            studentService.enroll(studentId,courseId);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Enroll denied");
        }
    }

}