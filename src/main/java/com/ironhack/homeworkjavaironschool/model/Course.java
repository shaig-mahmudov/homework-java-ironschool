package com.ironhack.homeworkjavaironschool.model;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class Course {
    private UUID uuid = UUID.randomUUID();
    private String courseId = uuid.toString();
    @NotNull(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    @Positive(message = "Price must be positive number")
    private double price;
    private double money_earned;
    private @Nullable Teacher teacher;

    public Course() {};

    public Course(String name, double price) {
        this.name = name;
        this.price = price;
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
