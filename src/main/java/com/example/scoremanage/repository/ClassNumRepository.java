package com.example.scoremanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scoremanage.model.ClassNum;

@Repository
public interface ClassNumRepository extends JpaRepository<ClassNum, Long> {
	
}
