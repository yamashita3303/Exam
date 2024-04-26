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
@Table(name = "CLASSNUM")
	
public class ClassNum {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="SCHOOL_CD", length = 3, nullable = false)
	private String schoolCd;

	@Column(name="CLASS_NUM", length = 5, nullable = false)
	private String classNum;
	
	/**
	 * クラスを返す
	 * @return classNum
	 */
	public String getClassNum() {
	    // ユーザー名を返す
	    return this.classNum;
	}
}