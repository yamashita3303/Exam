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
@Table(name = "TEACHER")
	
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="TEACHER_ID", length = 10, nullable = false)
	private String teacherId;
	
	@Column(name="PASSWORD", length = 30, nullable = true)
	private String password;

	@Column(name="NAME", length = 10, nullable = true)
	private String name;
	
	@Column(name="SCHOOL_CD", length = 3, nullable = true)
	private String schoolCd;
}