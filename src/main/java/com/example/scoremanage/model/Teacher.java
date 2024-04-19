package com.example.scoremanage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "TEACHER")
	
public class Teacher {
	@Column(name="ID", length = 10, nullable = false)
	private String id;
	
	@Column(name="PASSWORD", length = 30, nullable = true)
	private String password;

	@Column(name="NAME", length = 10, nullable = true)
	private String name;
	
	@Column(name="SCHOOL_CD", length = 3, nullable = true)
	private String schoolCd;
}