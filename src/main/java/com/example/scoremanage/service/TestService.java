package com.example.scoremanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.scoremanage.model.Test;
import com.example.scoremanage.repository.TestRepository;

import jakarta.transaction.Transactional;
 
@Service
@Transactional
public class TestService {
 
	@Autowired
	private TestRepository repository;
 
	/**
	 * アドレス帳一覧の取得
	 * @return List<Test>
	 */
	public List<Test> getTestList() {
	    List<Test> entityList = this.repository.findAll();
	    return entityList;
	}
 
	/**
	 * 詳細データの取得
	 * @param @NonNull Long index
	 * @return  TEST
	 */
	public Test get(@NonNull Long index) {
		Test test = this.repository.findById(index).orElse(new Test());
		return test;
	}
 
	/**
	 * アドレス帳一覧の取得
	 * @param TEST TEST
	 */
	public void save(@NonNull Test test) {
		this.repository.save(test);
	}
 
	/**
	 * アドレス帳データの削除
	 * @param @NonNull Long index
	 */
	public void delete(@NonNull Long index) {
		this.repository.deleteById(index);
	}
	
	// 受け取ったidからデータを取得して、Formを返却する
    public Test getOneBook(Long id) {
		
        // idを指定して本の情報を取得する
    	Test test = repository.findById(id).orElseThrow();
		
        // 画面返却用のFormに値を設定する
        /*
        Student editstudent = new Student();
        editstudent.setId(student.getId());
        editstudent.setNAME(student.getNAME());
        editstudent.setCLASS_NUM(student.getCLASS_NUM());
        */
		
        return test;
    }
    
 // 本を更新する
    public void update(Test edittest) {
		
        // データベースに登録する値を保持するインスタンスの作成
    	//Student student = new Student();
		
        // 画面から受け取った値を設定する
    	/*
    	student.setId(editstudent.getId());
    	student.setNAME(editstudent.getNAME());
    	student.setCLASS_NUM(editstudent.getCLASS_NUM());
    	*/
        // データベースを更新する
        repository.save(edittest);
    }
    
    public List<Test> searchTests(/*Integer entYear, */String classNum, String subjectCd, Integer no) {
    	List<Test> tests = repository.findAll();
    	 
        // 入学年度で絞り込み
    	/*
        if (entYear != null) {
        	tests = repository.findByEntYear(entYear);
        }
        */
 
        // クラス番号で絞り込み
        if (classNum != null && !classNum.isEmpty()) {
            List<Test> classNumTests = repository.findByClassNum(classNum);
            tests.retainAll(classNumTests);
        }
 
        // 科目で絞り込み
        if (subjectCd != null && !subjectCd.isEmpty()) {
            List<Test> subjectCdTests = repository.findBySubjectCd(subjectCd);
            tests.retainAll(subjectCdTests);
        }
        
        // 回数で絞り込み
        if (no != null) {
        	tests = repository.findByNo(no);
        }
 
        return tests;
     }
    
    public List<Test> searchReferences(/*Integer entYear, */String classNum, String subjectCd, String studentNo) {
    	List<Test> references = repository.findAll();
    	 
        // 入学年度で絞り込み
    	/*
        if (entYear != null) {
        	references = repository.findByEntYear(entYear);
        }
        */
 
        // クラス番号で絞り込み
        if (classNum != null && !classNum.isEmpty()) {
            List<Test> classNumTests = repository.findByClassNum(classNum);
            references.retainAll(classNumTests);
        }
 
        // 科目で絞り込み
        if (subjectCd != null && !subjectCd.isEmpty()) {
            List<Test> subjectCdTests = repository.findBySubjectCd(subjectCd);
            references.retainAll(subjectCdTests);
        }
 
        // 学生番号で絞り込み
        if (studentNo != null && !studentNo.isEmpty()) {
            List<Test> classStudentNos = repository.findByStudentNo(studentNo);
            references.retainAll(classStudentNos);
        }

        return references;
     }
}