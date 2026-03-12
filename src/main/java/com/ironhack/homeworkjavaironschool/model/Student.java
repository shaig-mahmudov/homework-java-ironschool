package com.ironhack.homeworkjavaironschool.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class Student {
    private String studentId;
    @NotNull(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    private String address;
    private  String email;
   @Nullable private Course course;

   public Student() {}

    public Student(String name, String address ,String email){
    this.studentId=uniqueStudentId();
    this.name=name;
    this.address=address;
    this.email=email;
    }
    public String uniqueStudentId(){return UUID.randomUUID().toString();}
    //Getters
    public String getStudentId(){return studentId;}
    public String getName(){return name;}
    public String getAddress(){return  address;}
    public String getEmail(){return email;}
    @Nullable
    public Course getCourse() {
        return course;
    }

    //Setters
    public void setName(String name) {this.name = name;}
    public void setAddress(String address){this.address=address;}
    public void setEmail(String email){this.email=email;}
    public void setCourse(@Nullable Course course) {
        this.course = course;
    }
}