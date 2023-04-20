package com.example.vizsgaremek.dao;

import com.example.vizsgaremek.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorDAO extends JpaRepository<Mentor, Long> {
}
