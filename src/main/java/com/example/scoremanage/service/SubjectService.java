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
	public List<Subject> getSubjectList() {
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
	
	// 受け取ったidからデータを取得して、Formを返却する
    public Subject getOneBook(Long id) {
		
        // idを指定して本の情報を取得する
    	Subject subject = repository.findById(id).orElseThrow();
		
        // 画面返却用のFormに値を設定する
        /*
        Student editstudent = new Student();
        editstudent.setId(student.getId());
        editstudent.setNAME(student.getNAME());
        editstudent.setCLASS_NUM(student.getCLASS_NUM());
        */
		
        return subject;
    }
    
 // 本を更新する
    public void update(Subject editsubject) {
		
        // データベースに登録する値を保持するインスタンスの作成
    	//Student student = new Student();
		
        // 画面から受け取った値を設定する
    	/*
    	student.setId(editstudent.getId());
    	student.setNAME(editstudent.getNAME());
    	student.setCLASS_NUM(editstudent.getCLASS_NUM());
    	*/
        // データベースを更新する
        repository.save(editsubject);
    }
}