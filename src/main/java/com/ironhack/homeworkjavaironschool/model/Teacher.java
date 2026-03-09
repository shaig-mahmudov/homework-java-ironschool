package com.ironhack.homeworkjavaironschool.model;

import java.util.UUID;

    public class Teacher {
    private String teacherId;
    private String name;
    private double salary;

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
