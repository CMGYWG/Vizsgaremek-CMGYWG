package com.example.vizsgaremek.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    @NotBlank(message = "Name may not be blank")
    private String name;
    @NotNull(message = "Age should not be null")
    private int age;
    @NotBlank(message = "Course may not be blank")
    private String course;
    private Long projectId;
}
