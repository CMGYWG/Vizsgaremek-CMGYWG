package com.example.vizsgaremek;

import com.example.vizsgaremek.model.Mentor;
import com.example.vizsgaremek.model.dto.MentorDTO;
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
public class MentorIntegrationTests {
    @Value("${local.server.port}")
    private Integer port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllMentors() {
        List mentors = restTemplate.getForObject("http://localhost:" + port + "/mentors", List.class);
        assertEquals(6, mentors.size());
    }

    @Test
    public void getMentorById() {
        final ResponseEntity<Mentor> response = restTemplate.getForEntity("http://localhost:" + port + "/mentors/" + 2L, Mentor.class);
        Mentor mentor = response.getBody();
        assertEquals(2, mentor.getId());
        assertEquals("Nóra", mentor.getName());
        assertEquals(2, mentor.getProjects().size());
    }

    @Test
    public void deleteMentorById() {
        restTemplate.delete("http://localhost:" + port + "/mentors/1");
        List mentors = restTemplate.getForObject("http://localhost:" + port + "/mentors", List.class);
        assertEquals(5, mentors.size());
    }

    @Test
    public void addMentor() {
        MentorDTO mentorDTO = new MentorDTO("Robin", List.of(1L, 2L));
        restTemplate.postForObject("http://localhost:" + port + "/mentors", mentorDTO, Mentor.class);
        List mentors = restTemplate.getForObject("http://localhost:" + port + "/mentors", List.class);
        final ResponseEntity<Mentor> response = restTemplate.getForEntity("http://localhost:" + port + "/mentors/" + 7L, Mentor.class);
        Mentor mentor = response.getBody();
        assertEquals(7, mentors.size());
        assertEquals(2, mentor.getProjects().size());
    }

    @Test
    public void updateMentor() {
        MentorDTO mentorDTO = new MentorDTO("Robin", List.of(1L, 2L));
        restTemplate.put("http://localhost:" + port + "/mentors/3", mentorDTO, Mentor.class);
        List mentors = restTemplate.getForObject("http://localhost:" + port + "/mentors", List.class);
        final ResponseEntity<Mentor> response = restTemplate.getForEntity("http://localhost:" + port + "/mentors/" + 3L, Mentor.class);
        Mentor mentor = response.getBody();
        assertEquals(6, mentors.size());
        assertEquals("Robin", mentor.getName());
        assertEquals(2, mentor.getProjects().size());
    }
}
