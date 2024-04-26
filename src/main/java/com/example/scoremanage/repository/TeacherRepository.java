package com.example.scoremanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scoremanage.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	//ログイン時に使用するteacherIdを取ってくる
    Teacher findByTeacherIdEquals(String teacherId);	
}

