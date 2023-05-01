package com.example.vizsgaremek;

import com.example.vizsgaremek.model.Student;
import com.example.vizsgaremek.model.dto.StudentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StudentIntegrationTests {
    @Value("${local.server.port}")
    private Integer port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllStudents() {
        List students = restTemplate.getForObject("http://localhost:" + port + "/students", List.class);
        assertEquals(5, students.size());
    }

    @Test
    public void getStudentById() {
        final ResponseEntity<Student> response = restTemplate.getForEntity("http://localhost:" + port + "/students/" + 2L, Student.class);
        Student student = response.getBody();
        assertEquals(2, student.getId());
        assertEquals("Attila", student.getName());
        assertEquals(49, student.getAge());
        assertEquals(1L, student.getProject().getId());
    }

    @Test
    public void deleteStudentById() {
        restTemplate.delete("http://localhost:" + port + "/students/1");
        List students = restTemplate.getForObject("http://localhost:" + port + "/students", List.class);
        assertEquals(4, students.size());
    }

    @Test
    public void addStudent() {
        StudentDTO studentDTO = new StudentDTO("Robin", 20, "Math", 1L);
        restTemplate.postForObject("http://localhost:" + port + "/students", studentDTO, Student.class);
        List students = restTemplate.getForObject("http://localhost:" + port + "/students", List.class);
        Student student = restTemplate.getForObject("http://localhost:" + port + "/students/6", Student.class);
        assertEquals(6, students.size());
        assertEquals(3, student.getProject().getStudents().size());
    }

    @Test
    public void updateStudent() {
        StudentDTO studentDTO = new StudentDTO("Robin", 20, "Math", 2L);
        restTemplate.put("http://localhost:" + port + "/students/3", studentDTO, Student.class);
        List students = restTemplate.getForObject("http://localhost:" + port + "/students", List.class);
        Student student = restTemplate.getForObject("http://localhost:" + port + "/students/3", Student.class);
        assertEquals(5, students.size());
        assertEquals("Robin", student.getName());
        assertEquals(3, student.getProject().getStudents().size());
    }
}
