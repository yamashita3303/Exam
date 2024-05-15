package com.example.scoremanage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scoremanage.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
	List<Subject> findBySchoolCd(String schoolCd);
	Subject findByName(String subjectCds);
	Subject findByCd(String subjectCd);
}
