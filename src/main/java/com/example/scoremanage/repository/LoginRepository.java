package com.example.scoremanage.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.scoremanage.model.Teacher;
 
public interface LoginRepository extends JpaRepository<Teacher, String> {
 
	Teacher findByIdAndPassword(String id, String password);
	Teacher findByIdEquals(String id);
}