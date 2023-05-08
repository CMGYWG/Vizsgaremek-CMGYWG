package com.example.vizsgaremek.service;

import com.example.vizsgaremek.dao.ProjectDAO;
import com.example.vizsgaremek.dao.StudentDAO;
import com.example.vizsgaremek.model.Project;
import com.example.vizsgaremek.model.Student;
import com.example.vizsgaremek.model.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentDAO studentDAO;
    private final ProjectDAO projectDAO;

    public StudentService(StudentDAO studentDAO, ProjectDAO projectDAO) {
        this.studentDAO = studentDAO;
        this.projectDAO = projectDAO;
    }

    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    public void save(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setCourse(studentDTO.getCourse());

        Project project = projectDAO.findById(studentDTO.getProjectId()).orElse(null);

        student.setProject(project);
        project.addStudent(student);
        studentDAO.save(student);
        projectDAO.save(project);
    }

    public Student findById(Long id) {
        return studentDAO.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        Student student = studentDAO.findById(id).orElseThrow();
        Project project = student.getProject();
        List<Student> students = student.getProject().getStudents();
        students.removeIf(x->x.getId()==id);
        project.setStudents(students);
        projectDAO.save(project);
        student.setProject(null);
        studentDAO.save(student);
        studentDAO.deleteById(id);
    }

    public void update(Long id, StudentDTO studentDTO){
        Student student = studentDAO.findById(id).orElseThrow();
        Project project = student.getProject();
        List<Student> students = student.getProject().getStudents();
        students.removeIf(x->x.getId()==id);
        project.setStudents(students);
        projectDAO.save(project);
        student.setProject(null);

        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setCourse(studentDTO.getCourse());


        student.setProject(projectDAO.findById(studentDTO.getProjectId()).orElseThrow());
        project.getStudents().add(student);
        studentDAO.save(student);
        projectDAO.save(project);
    }
}
