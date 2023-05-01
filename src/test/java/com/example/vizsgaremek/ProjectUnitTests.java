package com.example.vizsgaremek;

import com.example.vizsgaremek.dao.MentorDAO;
import com.example.vizsgaremek.dao.StudentDAO;
import com.example.vizsgaremek.model.Mentor;
import com.example.vizsgaremek.model.Project;
import com.example.vizsgaremek.model.Student;
import com.example.vizsgaremek.model.dto.ProjectDTO;
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
        Assertions.assertEquals("Testing", project.getProjectName());
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
        Assertions.assertEquals(5, students.size());
    }

    @Test
    void saveProject() {
        projectService.save(new ProjectDTO("Vizsgaremek", "Winners", "It is a project that is obligatory", List.of(1L, 2L, 3L), List.of(4L,5L)));
        List<Project> projects = projectService.findAll();
        Assertions.assertEquals(6, projects.size());

        Project project = projectService.findById(6L);
        Assertions.assertEquals("Vizsgaremek", project.getProjectName());
        Assertions.assertEquals("Winners", project.getTeamName());

        List<Mentor> mentors = project.getMentors();
        List<Student> students = project.getStudents();
        Assertions.assertEquals(3, mentors.size());
        Assertions.assertEquals(2, students.size());
    }

    @Test
    void updateProject() {
        projectService.update(1L, new ProjectDTO("Vizsgaremek", "Winners", "It is a project that is obligatory", List.of(1L, 2L, 3L), List.of(4L,5L)));
        List<Project> projects = projectService.findAll();
        Assertions.assertEquals(5, projects.size());

        Project project = projectService.findById(1L);
        Assertions.assertEquals("Vizsgaremek", project.getProjectName());
        Assertions.assertEquals("Winners", project.getTeamName());

        List<Mentor> mentors = project.getMentors();
        List<Student> students = project.getStudents();
        Assertions.assertEquals(3, mentors.size());
        Assertions.assertEquals(2, students.size());
    }
}
