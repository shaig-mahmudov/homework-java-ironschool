package com.ironhack.homeworkjavaironbattle.model;
import jakarta.annotation.Nullable;

import java.util.UUID;

public class Course {
    private UUID uuid = UUID.randomUUID();
    private String courseId;
    private String name;
    private double price;
    private double money_earned;
    private @Nullable Teacher teacher;

    public Course(String name, double price) {
        this.name = name;
        this.price = price;
        this.courseId = uuid.toString();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(@Nullable Teacher teacher) {
        this.teacher = teacher;
    }

    public double getMoney_earned() {
        return money_earned;
    }

    public void setMoney_earned(double money_earned) {
        this.money_earned = money_earned;
    }
}
