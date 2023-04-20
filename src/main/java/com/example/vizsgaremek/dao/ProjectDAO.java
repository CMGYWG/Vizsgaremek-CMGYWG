package com.example.vizsgaremek.dao;

import com.example.vizsgaremek.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDAO extends JpaRepository<Project, Long> {
}
