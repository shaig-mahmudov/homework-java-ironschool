package com.ironhack.homeworkjavaironschool.cli;

import com.ironhack.homeworkjavaironschool.model.Course;
import com.ironhack.homeworkjavaironschool.model.Student;
import com.ironhack.homeworkjavaironschool.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Component
public class CliRunner {
    private final SchoolCliFacade facade;
    private Scanner scanner;

    public CliRunner(SchoolCliFacade facade) {
        this.facade = facade;
    }

    public void run() {
        this.scanner = new Scanner(System.in);

        printBanner();
        printStartupMessage();
        printHelp();

        while (true) {
            System.out.print("\ncli> ");
            if (!scanner.hasNextLine()) {
                System.out.println("Exiting CLI.");
                break;
            }

            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            if (isExitCommand(line)) {
                System.out.println("Exiting CLI.");
                break;
            }

            try {
                handle(line);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void printBanner() {
        System.out.println("====================================");
        System.out.println("       School Management CLI        ");
        System.out.println("====================================");
    }

    private void printStartupMessage() {
        System.out.println("CLI is ready.");
        System.out.println("Add teachers, courses, and students later with ADD commands.");
    }

    private void handle(String line) {
        String normalized = line.trim();
        String upper = normalized.toUpperCase(Locale.ROOT);

        if (upper.equals("SHOW COURSES")) {
            printCourses(facade.showCourses());
            return;
        }
        if (upper.equals("SHOW STUDENTS")) {
            printStudents(facade.showStudents());
            return;
        }
        if (upper.equals("SHOW TEACHERS")) {
            printTeachers(facade.showTeachers());
            return;
        }
        if (upper.equals("SHOW PROFIT")) {
            System.out.println("Profit: " + facade.showProfit());
            return;
        }
        if (upper.equals("ADD TEACHER")) {
            createTeacherFlow("Add teacher");
            return;
        }
        if (upper.equals("ADD COURSE")) {
            createCourseFlow("Add course");
            return;
        }
        if (upper.equals("ADD STUDENT")) {
            createStudentFlow("Add student");
            return;
        }
        if (upper.equals("HELP")) {
            printHelp();
            return;
        }

        if (upper.startsWith("LOOKUP COURSE ")) {
            String id = normalized.substring("LOOKUP COURSE ".length()).trim();
            printCourse(facade.lookupCourse(id));
            return;
        }
        if (upper.startsWith("LOOKUP STUDENT ")) {
            String id = normalized.substring("LOOKUP STUDENT ".length()).trim();
            printStudent(facade.lookupStudent(id));
            return;
        }
        if (upper.startsWith("LOOKUP TEACHER ")) {
            String id = normalized.substring("LOOKUP TEACHER ".length()).trim();
            printTeacher(facade.lookupTeacher(id));
            return;
        }
        if (upper.startsWith("ENROLL ")) {
            String[] parts = normalized.split("\\s+");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Usage: ENROLL [STUDENT_ID] [COURSE_ID]");
            }
            facade.enroll(parts[1], parts[2]);
            System.out.println("Student enrolled successfully.");
            return;
        }
        if (upper.startsWith("ASSIGN ")) {
            String[] parts = normalized.split("\\s+");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Usage: ASSIGN [TEACHER_ID] [COURSE_ID]");
            }
            facade.assign(parts[1], parts[2]);
            System.out.println("Teacher assigned successfully.");
            return;
        }
        if (upper.startsWith("SHOW MONEY EARNED ")) {
            String id = normalized.substring("SHOW MONEY EARNED ".length()).trim();
            System.out.println("Money earned: " + facade.showMoneyEarned(id));
            return;
        }

        throw new IllegalArgumentException("Unknown command. Type HELP to see supported commands.");
    }

    private boolean isExitCommand(String line) {
        String upper = line.trim().toUpperCase(Locale.ROOT);
        return upper.equals("EXIT") || upper.equals("QUIT");
    }

    private String prompt(String label) {
        while (true) {
            System.out.print(label + ": ");
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Value cannot be empty.");
        }
    }

    private int promptInt(String label) {
        while (true) {
            System.out.print(label + ": ");
            String raw = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(raw);
                if (value < 0) {
                    throw new NumberFormatException();
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid non-negative integer.");
            }
        }
    }

    private double promptDouble(String label) {
        while (true) {
            System.out.print(label + ": ");
            String raw = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(raw);
                if (Double.isNaN(value) || Double.isInfinite(value) || value < 0) {
                    throw new NumberFormatException();
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid non-negative number.");
            }
        }
    }

    private void createTeacherFlow(String title) {
        System.out.println(title);
        String name = prompt("  Name");
        double salary = promptDouble("  Salary");
        Teacher teacher = facade.createTeacher(name, salary);
        System.out.println("  Created teacher with ID: " + teacher.getTeacherId());
    }

    private void createCourseFlow(String title) {
        System.out.println(title);
        String name = prompt("  Name");
        double price = promptDouble("  Price");
        Course course = facade.createCourse(name, price);
        System.out.println("  Created course with ID: " + course.getCourseId());
    }

    private void createStudentFlow(String title) {
        System.out.println(title);
        String name = prompt("  Name");
        String address = prompt("  Address");
        String email = promptEmail("  Email");
        Student student = facade.createStudent(name, address, email);
        System.out.println("  Created student with ID: " + student.getStudentId());
    }

    private String promptEmail(String label) {
        while (true) {
            String email = prompt(label);
            if (email.contains("@") && !email.startsWith("@") && !email.endsWith("@")) {
                return email;
            }
            System.out.println("Please enter a valid email address.");
        }
    }

    private void printHelp() {
        System.out.println("\nSupported commands:");
        System.out.println("  SHOW COURSES");
        System.out.println("  LOOKUP COURSE [COURSE_ID]");
        System.out.println("  SHOW STUDENTS");
        System.out.println("  LOOKUP STUDENT [STUDENT_ID]");
        System.out.println("  SHOW TEACHERS");
        System.out.println("  LOOKUP TEACHER [TEACHER_ID]");
        System.out.println("  ADD TEACHER");
        System.out.println("  ADD COURSE");
        System.out.println("  ADD STUDENT");
        System.out.println("  ENROLL [STUDENT_ID] [COURSE_ID]");
        System.out.println("  ASSIGN [TEACHER_ID] [COURSE_ID]");
        System.out.println("  SHOW PROFIT");
        System.out.println("  SHOW MONEY EARNED [COURSE_ID]");
        System.out.println("  HELP");
        System.out.println("  EXIT");
    }

    private void printCourses(List<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        for (Course course : courses) {
            printCourse(course);
        }
    }

    private void printStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student student : students) {
            printStudent(student);
        }
    }

    private void printTeachers(List<Teacher> teachers) {
        if (teachers.isEmpty()) {
            System.out.println("No teachers found.");
            return;
        }
        for (Teacher teacher : teachers) {
            printTeacher(teacher);
        }
    }

    private void printCourse(Course course) {
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        String teacherName = course.getTeacher() != null ? course.getTeacher().getName() : "Unassigned";
        System.out.println("Course{id='" + course.getCourseId() + "', name='" + course.getName() + "', price=" + course.getPrice() + ", moneyEarned=" + course.getMoney_earned() + ", teacher='" + teacherName + "'}");
    }

    private void printStudent(Student student) {
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        String courseName = student.getCourse() != null ? student.getCourse().getName() : "Not enrolled";
        System.out.println("Student{id='" + student.getStudentId() + "', name='" + student.getName() + "', address='" + student.getAddress() + "', email='" + student.getEmail() + "', course='" + courseName + "'}");
    }

    private void printTeacher(Teacher teacher) {
        if (teacher == null) {
            System.out.println("Teacher not found.");
            return;
        }
        System.out.println("Teacher{id='" + teacher.getTeacherId() + "', name='" + teacher.getName() + "', salary=" + teacher.getSalary() + "'}");
    }
}