package com.example.scoremanage.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.scoremanage.model.Teacher;
 
public interface LoginRepository extends JpaRepository<Teacher, Long> {
 
	Teacher findByTeacherIdAndPassword(String teacherId, String password);
	Teacher findByTeacherIdEquals(String teacherId);
}