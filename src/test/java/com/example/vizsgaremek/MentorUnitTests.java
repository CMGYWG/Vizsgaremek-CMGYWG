package com.example.vizsgaremek;

import com.example.vizsgaremek.dao.ProjectDAO;
import com.example.vizsgaremek.model.Mentor;
import com.example.vizsgaremek.model.Project;
import com.example.vizsgaremek.model.Student;
import com.example.vizsgaremek.model.dto.MentorDTO;
import com.example.vizsgaremek.model.dto.StudentDTO;
import com.example.vizsgaremek.service.MentorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MentorUnitTests {
    @Autowired
    private MentorService mentorService;
    @Autowired
    private ProjectDAO projectDAO;

    @Test
    void getAllMentors() {
        List<Mentor> mentors = mentorService.findAll();
        Assertions.assertEquals(6, mentors.size());
    }

    @Test
    void findMentorById() {
        Mentor mentor = mentorService.findById(1L);
        List<Project> projects = mentor.getProjects();
        Assertions.assertEquals(1L, mentor.getId());
        Assertions.assertEquals("Éva", mentor.getName());
        Assertions.assertEquals(2, projects.size());
    }

    @Test
    void deleteMentorById() {
        mentorService.deleteById(1L);
        List<Mentor> mentors = mentorService.findAll();
        Assertions.assertEquals(5, mentors.size());
    }

    @Test
    void saveMentor() {
        List<Long> longs = new ArrayList<>();
        longs.add(1L);
        longs.add(2L);
        MentorDTO mentorDTO = new MentorDTO("Andrew",longs);
        mentorService.save(mentorDTO);
        List<Mentor> mentors = mentorService.findAll();
        Assertions.assertEquals(7, mentors.size());

        Mentor mentor = mentorService.findById(7L);
        Assertions.assertEquals("Andrew", mentor.getName());
        Assertions.assertEquals(2, mentor.getProjects().size());
    }
}
