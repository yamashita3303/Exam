package com.example.scoremanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scoremanage.model.School;


@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
	
}

