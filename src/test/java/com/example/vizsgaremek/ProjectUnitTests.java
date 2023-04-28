package com.example.vizsgaremek;

import com.example.vizsgaremek.model.Mentor;
import com.example.vizsgaremek.model.Project;
import com.example.vizsgaremek.model.Student;
import com.example.vizsgaremek.service.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProjectUnitTests {
    @Autowired
    private ProjectService projectService;

    @Test
    void getAllProjects() {
        List<Project> projects = projectService.findAll();
        Assertions.assertEquals(5, projects.size());
    }

    @Test
    void findProjectById() {
        Project project = projectService.findById(1L);
        List<Mentor> mentors = project.getMentors();
        List<Student> students = project.getStudents();
        Assertions.assertEquals("asd", project.getProjectName());
        Assertions.assertEquals(4, mentors.size());
        Assertions.assertEquals(2, students.size());
    }
}
