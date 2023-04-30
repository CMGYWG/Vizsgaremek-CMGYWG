package com.example.vizsgaremek;

import com.example.vizsgaremek.dao.ProjectDAO;
import com.example.vizsgaremek.dao.StudentDAO;
import com.example.vizsgaremek.model.Project;
import com.example.vizsgaremek.model.Student;
import com.example.vizsgaremek.model.dto.StudentDTO;
import com.example.vizsgaremek.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class StudentUnitTests {
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private ProjectDAO projectDAO;
    @Autowired
    private StudentService studentService = new StudentService(studentDAO, projectDAO);

    @Test
    void getAllStudents() {
        List<Student> students = studentService.findAll();
        Assertions.assertEquals(5, students.size());
    }

    @Test
    void findStudentById() {
        Student actualStudent = studentService.findById(1L);
        Project project = projectDAO.findById(actualStudent.getProject().getId()).orElseThrow();
        Student expectedStudent = new Student(1L, "Matyi", 21, "Engineer", project);
        Assertions.assertEquals(expectedStudent.getId(), actualStudent.getId());
        Assertions.assertEquals(expectedStudent.getAge(), actualStudent.getAge());
        Assertions.assertEquals(expectedStudent.getName(), actualStudent.getName());
        Assertions.assertEquals(expectedStudent.getCourse(), actualStudent.getCourse());
    }

    @Test
    void deleteStudentById() {
        studentService.deleteById(1L);
        List<Student> students = studentService.findAll();
        Assertions.assertEquals(4, students.size());
    }

    @Test
    void saveStudent() {
        studentService.save(new StudentDTO("John", 11, "IT", 1L));
        List<Student> students = studentService.findAll();
        Assertions.assertEquals(6, students.size());

        Student student = studentService.findById(6L);
        Assertions.assertEquals("Testing", student.getProject().getProjectName());
        Assertions.assertEquals(3, student.getProject().getStudents().size());
    }

    @Test
    void updateStudent() {
        studentService.update(1L, new StudentDTO("John", 11, "IT", 1L));
        List<Student> students = studentService.findAll();
        Assertions.assertEquals(5, students.size());

        Student student = studentService.findById(1L);
        Assertions.assertEquals("John", student.getName());
        Assertions.assertEquals("Testing", student.getProject().getProjectName());
    }
}
