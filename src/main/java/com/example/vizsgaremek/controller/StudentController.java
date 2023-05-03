package com.example.vizsgaremek.controller;

import com.example.vizsgaremek.model.Student;
import com.example.vizsgaremek.model.dto.StudentDTO;
import com.example.vizsgaremek.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(
            summary = "Find all students",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Responds with a list of Student entities."),
            }
    )
    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @Operation(
            summary = "Post a student",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student has been successfully posted."),
                    @ApiResponse(responseCode = "400", description = "Wrong student object has been posted.")
            }
    )
    @PostMapping
    public void save(@Valid @RequestBody StudentDTO studentDTO) {
        studentService.save(studentDTO);
    }

    @Operation(
            summary = "Find student by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student has been found."),
                    @ApiResponse(responseCode = "500", description = "No value present.")
            }
    )
    @GetMapping("/{id}")
    public Student findById(@PathVariable("id") Long id) {
        return studentService.findById(id);
    }

    @Operation(
            summary = "Delete student by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student has been deleted."),
                    @ApiResponse(responseCode = "500", description = "No value present.")
            }
    )
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        studentService.deleteById(id);
    }

    @Operation(
            summary = "Update student by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student has been updated."),
                    @ApiResponse(responseCode = "500", description = "No value present.")
            }
    )
    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id,@Valid @RequestBody StudentDTO studentDTO) {
        studentService.update(id, studentDTO);
    }
}
