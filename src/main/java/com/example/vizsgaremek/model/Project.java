package com.example.vizsgaremek.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private String teamName;
    private String description;
    @ManyToMany
    @JoinTable(
            name="projects_mentors",
            joinColumns = @JoinColumn(name = "projects_id"),
            inverseJoinColumns = @JoinColumn(name = "mentors_id"))
    @JsonIdentityReference(alwaysAsId = true)
    private List<Mentor> mentors;
    @OneToMany(mappedBy = "project")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Student> students;

    public void addMentor(Mentor mentor){
        mentors.add(mentor);
    }
}
