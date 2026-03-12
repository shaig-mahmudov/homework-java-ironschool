package com.ironhack.homeworkjavaironschool.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

    public class Teacher {
    private String teacherId;
    @NotNull(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    @Positive(message = "Salary must be positive")
    private double salary;

    public Teacher(){}

    public Teacher(String name , double salary){
    this.teacherId=generateUniqueId();
    this.name=name;
    this.salary=salary;
    }
    public String getTeacherId(){return teacherId;}
    private String generateUniqueId() {return UUID.randomUUID().toString();}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public double getSalary(){return salary;}
    public void setSalary(double salary){this.salary=salary;}

    }
