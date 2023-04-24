package com.example.vizsgaremek.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    @NotBlank(message = "Project name may not be blank")
    private String projectName;
    @NotBlank(message = "Team name may not be blank")
    private String teamName;
    @NotBlank(message = "Description may not be blank")
    private String description;
    private List<Long> mentorIds;
    private List<Long> studentIds;
}
