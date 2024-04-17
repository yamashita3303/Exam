package com.example.scoremanage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scoremanage.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	 List<Student> findByEntYearAndClassNumAndIsAttend(Integer entYear, String classNum, Boolean isAttend);
}