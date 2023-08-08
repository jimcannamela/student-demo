package com.galvanize.students;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassController {
    StudentRepository students;;
    CourseRepository courses;

    public ClassController(StudentRepository students, CourseRepository courses) {
        this.students = students;
        this.courses = courses;
    }

    @PatchMapping("/students/{id}/courses")
    public Student addCourseToStudent(@PathVariable Long id, @RequestBody Course course) {
        Student student = students.findById(id).orElse(null);
        if (student == null) return null;
        student.getLikedCourses().add(course);
        return students.save(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return students.findAll();
    }
    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return students.save(student);
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courses.findAll();
    }
    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        return courses.save(course);
    }
    @PostMapping("/courses/addAll")
    public List<Course> createCourses(@RequestBody List<Course> courses) {
        return this.courses.saveAll(courses);
    }
}
