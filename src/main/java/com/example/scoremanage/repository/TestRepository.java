package com.example.scoremanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scoremanage.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
	
}
