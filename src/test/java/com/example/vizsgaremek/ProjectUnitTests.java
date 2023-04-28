package com.example.vizsgaremek;

import com.example.vizsgaremek.dao.MentorDAO;
import com.example.vizsgaremek.dao.StudentDAO;
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
    @Autowired
    private MentorDAO mentorDAO;
    @Autowired
    private StudentDAO studentDAO;

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

    @Test
    void deleteProjectById() {
        projectService.deleteById(1L);
        List<Project> projects = projectService.findAll();
        Assertions.assertEquals(4, projects.size());
    }

    @Test
    void testSizeOfMentorsAndStudentsNotDecreasingAfterProjectDeletion() {
        projectService.deleteById(1L);
        List<Mentor> mentors = mentorDAO.findAll();
        List<Student> students = studentDAO.findAll();
        Assertions.assertEquals(6, mentors.size());
        Assertions.assertEquals(4, students.size());
    }
}
