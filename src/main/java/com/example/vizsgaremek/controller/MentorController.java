package com.example.vizsgaremek.controller;

import com.example.vizsgaremek.model.Mentor;
import com.example.vizsgaremek.model.dto.MentorDTO;
import com.example.vizsgaremek.service.MentorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentors")
public class MentorController {
    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }
    @Operation(
            summary = "Find all mentors",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Responds with a list of Mentor entities."),
            }
    )
    @GetMapping
    public List<Mentor> findAll() {
        return mentorService.findAll();
    }

    @Operation(
            summary = "Post a mentor",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Mentor has been successfully posted."),
                    @ApiResponse(responseCode = "400", description = "Wrong mentor object has been posted.")
            }
    )
    @PostMapping
    public void save(@Valid @RequestBody MentorDTO mentorDTO) {
        mentorService.save(mentorDTO);
    }

    @Operation(
            summary = "Find mentor by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Mentor has been found."),
                    @ApiResponse(responseCode = "500", description = "No value present.")
            }
    )
    @GetMapping("/{id}")
    public Mentor findById(@PathVariable("id") Long id) {
        return mentorService.findById(id);
    }

    @Operation(
            summary = "Delete mentor by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Mentor has been deleted."),
                    @ApiResponse(responseCode = "500", description = "No value present.")
            }
    )
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        mentorService.deleteById(id);
    }

    @Operation(
            summary = "Update mentor by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Mentor has been updated."),
                    @ApiResponse(responseCode = "500", description = "No value present.")
            }
    )
    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id,@Valid @RequestBody MentorDTO mentorDTO) {
        mentorService.update(id, mentorDTO);
    }
}
