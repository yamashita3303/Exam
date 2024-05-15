package com.example.scoremanage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scoremanage.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
	List<Test> findByClassNum(String classNum);
	List<Test> findBySubjectCd(String subjectCd);
	List<Test> findByStudentNo(String studentNo);
	List<Test> findBySchoolCd(String schoolCd);
}
