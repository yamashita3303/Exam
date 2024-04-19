package com.example.scoremanage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TEST")
	
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="STUDENT_NO", length = 10, nullable = false)
	private String studentNo;
	
	@Column(name="SUBJECT_CD", length = 3, nullable = false)
	private String subjectCd;
	
	@Column(name="SCHOOL_CD",length = 10, nullable = false)
	private String schoolCd;
	
	@Column(name="NO",length = 10, nullable = false)
	private Integer no;
	
	@Column(name="POINT",length = 10, nullable = true)
	private Integer point;
	
	@Column(name="CLASS_NUM",length = 5, nullable = true)
	private String classNum;
}