package com.example.scoremanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.scoremanage.model.Teacher;
import com.example.scoremanage.repository.TeacherRepository;

import jakarta.transaction.Transactional;
 
@Service
@Transactional
public class TeacherService {
 
	@Autowired
	private TeacherRepository repository;
 
	/**
	 * アドレス帳一覧の取得
	 * @return List<Teacher>
	 */
	public List<Teacher> getTeacherList() {
	    List<Teacher> entityList = this.repository.findAll();
	    return entityList;
	}
 
	/**
	 * 詳細データの取得
	 * @param @NonNull Long index
	 * @return  TEACHER
	 */
	public Teacher get(@NonNull Long index) {
		Teacher teacher = this.repository.findById(index).orElse(new Teacher());
		return teacher;
	}
 
	/**
	 * アドレス帳一覧の取得
	 * @param TEACHER TEACHER
	 */
	public void save(@NonNull Teacher teacher) {
		this.repository.save(teacher);
	}
 
	/**
	 * アドレス帳データの削除
	 * @param @NonNull Long index
	 */
	public void delete(@NonNull Long index) {
		this.repository.deleteById(index);
	}
}