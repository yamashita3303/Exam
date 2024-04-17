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
@Table(name = "SUBJECT")
	
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="SCHOOL_CD", length = 3, nullable = false)
	private String schoolCd;
	
	@Column(name="CD", length = 3, nullable = false)
	private String cd;

	@Column(name="NAME", length = 20, nullable = true)
	private String name;
}