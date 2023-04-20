package com.example.vizsgaremek.service;

import com.example.vizsgaremek.dao.MentorDAO;
import com.example.vizsgaremek.dao.ProjectDAO;
import com.example.vizsgaremek.model.Mentor;
import com.example.vizsgaremek.model.Project;
import com.example.vizsgaremek.model.dto.MentorDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MentorService {
    private final MentorDAO mentorDAO;
    private final ProjectDAO projectDAO;

    public MentorService(MentorDAO mentorDAO, ProjectDAO projectDAO) {
        this.mentorDAO = mentorDAO;
        this.projectDAO = projectDAO;
    }

    public List<Mentor> findAll() {
        return mentorDAO.findAll();
    }

    public void save(MentorDTO mentorDTO) {
        Mentor mentor = new Mentor();
        mentor.setName(mentorDTO.getName());

        List<Long> projectIds = mentorDTO.getProjectIds();
        List<Project> projects = new ArrayList<>();
        if (!projectIds.isEmpty()) {
            projects = projectDAO.findAllById(projectIds);
        }
        mentor.setProjects(projects);
        projects.forEach(a -> a.addMentor(mentor));

        mentorDAO.save(mentor);
    }

    public Mentor findById(Long id) {
        return mentorDAO.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        Mentor mentor = mentorDAO.findById(id).orElseThrow();
        mentor.getProjects()
                .forEach(mentorToUpdate -> {
                    mentorToUpdate.getMentors()
                            .removeIf(x -> x.getId() == id);
                    projectDAO.save(mentorToUpdate);
                });
        mentor.setProjects(null);
        mentorDAO.save(mentor);
        mentorDAO.deleteById(id);
    }
    public void update(Long id, MentorDTO mentorDTO){
        Mentor mentor = mentorDAO.findById(id).orElse(null);
        mentor.setName(mentorDTO.getName());

        mentor.getProjects()
                .forEach(mentorToUpdate -> {
                    mentorToUpdate.getMentors()
                            .removeIf(x -> x.getId() == id);
                    projectDAO.save(mentorToUpdate);
                });
        mentor.setProjects(null);

        List<Long> projectIds = mentorDTO.getProjectIds();
        List<Project> projects = new ArrayList<>();
        if (!projectIds.isEmpty()) {
            projects = projectDAO.findAllById(projectIds);
        }
        mentor.setProjects(projects);
        projects.forEach(a -> a.addMentor(mentor));


        mentorDAO.save(mentor);
    }
}
