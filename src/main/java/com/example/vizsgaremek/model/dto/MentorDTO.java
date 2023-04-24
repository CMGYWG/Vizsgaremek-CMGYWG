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
public class MentorDTO {
    @NotBlank(message = "Name may not be blank")
    private String name;
    private List<Long> projectIds;
}
