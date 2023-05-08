package com.example.vizsgaremek.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Mentor.class)
@NamedEntityGraph(name = "projects_mentors", attributeNodes = @NamedAttributeNode("projects"))
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name may not be blank")
    private String name;
    @ManyToMany(mappedBy = "mentors", fetch = FetchType.EAGER)

    //@JsonIdentityReference(alwaysAsId = true)
    private List<Project> projects;

    public void addProject(Project project){
        projects.add(project);
    }
}
