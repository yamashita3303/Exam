package com.example.scoremanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.scoremanage.model.Subject;
import com.example.scoremanage.model.Teacher;
import com.example.scoremanage.repository.SubjectRepository;
import com.example.scoremanage.repository.TeacherRepository;

import jakarta.transaction.Transactional;
 
@Service
@Transactional
public class SubjectService {
 
	@Autowired
	private SubjectRepository repository;
	@Autowired
	private TeacherRepository teacherRepository;
 
	/**
	 * アドレス帳一覧の取得
	 * @return List<Subject>
	 */
	/*
	public List<Subject> getSubjectList() {
	    List<Subject> entityList = this.repository.findAll();
	    return entityList;
	}
	*/
	/**
	 * 学校ごとの一覧の取得
     * (ユーザーに関連する学生のリストを取得するメソッド)
     *
     * @param user ユーザーの詳細情報
     * @return ユーザーに関連する学生のリスト
     */
    public List<Subject> getResSubjectList(UserDetails user) {
        // ユーザーのユーザー名に対応する教師情報をデータベースから取得する
        Teacher teachers = this.teacherRepository.findByTeacherIdEquals(user.getUsername());
        // 教師の所属する学校コードに関連する学生エンティティのリストを取得する
        List<Subject> entityList = this.repository.findBySchoolCd(teachers.getSchoolCd());
        // 学生エンティティのリストを返す
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
	
	public List<Subject> getAll() {
		return this.repository.findAll();
	}
 
	/**
	 * アドレス帳一覧の取得
	 * @param SUBJECT SUBJECT
	 */
	public void save(@NonNull Subject subject, UserDetails user) {
		// ユーザーのユーザー名に対応する教師情報をデータベースから取得する
        Teacher teachers = this.teacherRepository.findByTeacherIdEquals(user.getUsername());
        // TeacherIdから取得したSchoolCdを初期値に設定する
        subject.setSchoolCd(teachers.getSchoolCd());
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