package com.example.scoremanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.scoremanage.model.ClassNum;
import com.example.scoremanage.repository.ClassNumRepository;

import jakarta.transaction.Transactional;
 
@Service
@Transactional
public class ClassNumService {
 
	@Autowired
	private ClassNumRepository repository;
 
	/**
	 * アドレス帳一覧の取得
	 * @return List<ClassNum>
	 */
	public List<ClassNum> getClassNumList() {
	    List<ClassNum> entityList = this.repository.findAll();
	    return entityList;
	}
 
	/**
	 * 詳細データの取得
	 * @param @NonNull Long index
	 * @return  ClassNum
	 */
	public ClassNum get(@NonNull Long index) {
		ClassNum classNum = this.repository.findById(index).orElse(new ClassNum());
		return classNum;
	}
 
	/**
	 * アドレス帳一覧の取得
	 * @param ClassNum ClassNum
	 */
	public void save(@NonNull ClassNum classNum) {
		this.repository.save(classNum);
	}
 
	/**
	 * アドレス帳データの削除
	 * @param @NonNull Long index
	 */
	public void delete(@NonNull Long index) {
		this.repository.deleteById(index);
	}
}