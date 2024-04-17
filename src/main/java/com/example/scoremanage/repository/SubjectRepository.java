package com.example.scoremanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scoremanage.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
	
}
