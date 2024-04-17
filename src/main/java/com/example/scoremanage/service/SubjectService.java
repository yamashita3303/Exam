package com.example.scoremanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.scoremanage.model.Subject;
import com.example.scoremanage.repository.SubjectRepository;

import jakarta.transaction.Transactional;
 
@Service
@Transactional
public class SubjectService {
 
	@Autowired
	private SubjectRepository repository;
 
	/**
	 * アドレス帳一覧の取得
	 * @return List<Subject>
	 */
	public List<Subject> getScoreManageList() {
	    List<Subject> entityList = this.repository.findAll();
	    return entityList;
	}
 
	/**
	 * 詳細データの取得
	 * @param @NonNull Long index
	 * @return  SUBJECT
	 */
	public Subject get(@NonNull Long index) {
		Subject subject = this.repository.findById(index).orElse(new Subject());
		return subject;
	}
 
	/**
	 * アドレス帳一覧の取得
	 * @param SUBJECT SUBJECT
	 */
	public void save(@NonNull Subject subject) {
		this.repository.save(subject);
	}
 
	/**
	 * アドレス帳データの削除
	 * @param @NonNull Long index
	 */
	public void delete(@NonNull Long index) {
		this.repository.deleteById(index);
	}
}