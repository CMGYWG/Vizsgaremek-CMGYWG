package com.example.vizsgaremek.service;

import com.example.vizsgaremek.dao.MentorDAO;
import com.example.vizsgaremek.dao.ProjectDAO;
import com.example.vizsgaremek.dao.StudentDAO;
import com.example.vizsgaremek.model.Mentor;
import com.example.vizsgaremek.model.Project;
import com.example.vizsgaremek.model.Student;
import com.example.vizsgaremek.model.dto.ProjectDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectDAO projectDAO;
    private final MentorDAO mentorDAO;
    private final StudentDAO studentDAO;

    public ProjectService(ProjectDAO projectDAO, MentorDAO mentorDAO, StudentDAO studentDAO) {
        this.projectDAO = projectDAO;
        this.mentorDAO = mentorDAO;
        this.studentDAO = studentDAO;
    }

    public List<Project> findAll() {
        return projectDAO.findAll();
    }

    public void save(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectName(projectDTO.getProjectName());
        project.setDescription(projectDTO.getDescription());
        project.setTeamName(projectDTO.getTeamName());

        List<Long> mentorIds = projectDTO.getMentorIds();
        List<Long> studentIds = projectDTO.getStudentIds();
        List<Mentor> mentors = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        if (!mentorIds.isEmpty()) {
            mentors = mentorDAO.findAllById(mentorIds);
        }
        if (!studentIds.isEmpty()) {
            students = studentDAO.findAllById(studentIds);
        }
        project.setStudents(students);
        project.setMentors(mentors);

        students.forEach(x->x.setProject(project));
        mentors.forEach(a -> a.getProjects().add(project));
        projectDAO.save(project);
    }

    public Project findById(Long id) {
        return projectDAO.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        Project project = projectDAO.findById(id).orElseThrow();
        project.getStudents()
                .forEach(studentToUpdate -> {
                    studentToUpdate.setProject(null);
                    studentDAO.save(studentToUpdate);
                });
        project.getMentors()
                .forEach(mentorToUpdate -> {
                    mentorToUpdate.getProjects()
                            .removeIf(x -> x.getId() == id);
                    mentorDAO.save(mentorToUpdate);
                });
        project.setStudents(null);
        project.setMentors(null);
        projectDAO.save(project);
        projectDAO.deleteById(id);
    }
    public void update(Long id, ProjectDTO projectDTO){
        Project project = projectDAO.findById(id).orElseThrow();
        List<Mentor> mentors = project.getMentors();
        List<Student> students = project.getStudents();
        project.getMentors()
                .forEach(mentorToUpdate -> {
                    mentorToUpdate.getProjects()
                            .removeIf(x -> x.getId() == id);
                    mentorDAO.save(mentorToUpdate);
                });
        project.getStudents()
                .forEach(studentToUpdate -> {
                    studentToUpdate.setProject(null);
                    studentDAO.save(studentToUpdate);
                });
        project.setStudents(null);
        project.setMentors(null);
        projectDAO.save(project);

        List<Long> mentorIds = projectDTO.getMentorIds();
        List<Long> studentIds = projectDTO.getStudentIds();
        if (!mentorIds.isEmpty()) {
            mentors = mentorDAO.findAllById(mentorIds);
        }
        if (!studentIds.isEmpty()) {
            students = studentDAO.findAllById(studentIds);
        }
        project.setStudents(students);
        project.setMentors(mentors);
        project.setProjectName(projectDTO.getProjectName());
        project.setDescription(projectDTO.getDescription());
        project.setTeamName(projectDTO.getTeamName());

        students.forEach(x->x.setProject(project));
        mentors.forEach(a -> a.getProjects().add(project));
        projectDAO.save(project);
    }
}
