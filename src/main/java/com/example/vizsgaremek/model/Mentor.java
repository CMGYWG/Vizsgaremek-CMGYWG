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
@NamedEntityGraph(name = "projects_mentors", attributeNodes = @NamedAttributeNode("projects"))
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "mentors")
    /*@JoinTable(
            name="projects_mentors",
            joinColumns = @JoinColumn(name = "mentors_id"),
            inverseJoinColumns = @JoinColumn(name = "projects_id"))*/
    @JsonIdentityReference(alwaysAsId = true)
    private List<Project> projects;
}
