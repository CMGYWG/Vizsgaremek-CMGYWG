package com.example.vizsgaremek.controller;

import com.example.vizsgaremek.model.Mentor;
import com.example.vizsgaremek.model.dto.MentorDTO;
import com.example.vizsgaremek.service.MentorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentors")
public class MentorController {
    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping
    public List<Mentor> findAll() {
        return mentorService.findAll();
    }

    @PostMapping
    public void save(@RequestBody MentorDTO mentorDTO) {
        mentorService.save(mentorDTO);
    }

    @GetMapping("/{id}")
    public Mentor findById(@PathVariable("id") Long id) {
        return mentorService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        mentorService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody MentorDTO mentorDTO) {
        mentorService.update(id, mentorDTO);
    }
}
