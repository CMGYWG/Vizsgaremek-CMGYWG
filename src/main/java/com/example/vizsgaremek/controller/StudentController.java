package com.example.vizsgaremek.controller;

import com.example.vizsgaremek.model.Student;
import com.example.vizsgaremek.model.dto.StudentDTO;
import com.example.vizsgaremek.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @PostMapping
    public void save(@RequestBody StudentDTO studentDTO) {
        studentService.save(studentDTO);
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable("id") Long id) {
        return studentService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        studentService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody StudentDTO studentDTO) {
        studentService.update(id, studentDTO);
    }
}
