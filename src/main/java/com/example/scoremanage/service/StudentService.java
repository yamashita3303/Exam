package com.example.scoremanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.scoremanage.model.Student;
import com.example.scoremanage.repository.StudentRepository;

import jakarta.transaction.Transactional;
 
@Service
@Transactional
public class StudentService {
 
	@Autowired
	private StudentRepository repository;
 
	/**
	 * アドレス帳一覧の取得
	 * @return List<Student>
	 */
	public List<Student> getStudentList() {
	    List<Student> entityList = this.repository.findAll();
	    return entityList;
	}
 
	/**
	 * 詳細データの取得
	 * @param @NonNull Long index
	 * @return  Student
	 */
	public Student get(@NonNull Long index) {
		Student student = this.repository.findById(index).orElse(new Student());
		return student;
	}
 
	/**
	 * アドレス帳一覧の取得
	 * @param Student Student
	 */
	public void save(@NonNull Student student) {
		// フラグの初期値にtrueを設定する
		student.setIsAttend(true);
		this.repository.save(student);
	}
 
	/**
	 * アドレス帳データの削除
	 * @param @NonNull Long index
	 */
	public void delete(@NonNull Long index) {
		this.repository.deleteById(index);
	}
	
	
	// 受け取ったidからデータを取得して、Formを返却する
    public Student getOneBook(Long id) {
		
        // idを指定して本の情報を取得する
        Student student = repository.findById(id).orElseThrow();
		
        // 画面返却用のFormに値を設定する
        /*
        Student editstudent = new Student();
        editstudent.setId(student.getId());
        editstudent.setNAME(student.getNAME());
        editstudent.setCLASS_NUM(student.getCLASS_NUM());
        */
		
        return student;
    }
    
 // 本を更新する
    public void update(Student editstudent) {
		
        // データベースに登録する値を保持するインスタンスの作成
    	//Student student = new Student();
		
        // 画面から受け取った値を設定する
    	/*
    	student.setId(editstudent.getId());
    	student.setNAME(editstudent.getNAME());
    	student.setCLASS_NUM(editstudent.getCLASS_NUM());
    	*/
        // データベースを更新する
        repository.save(editstudent);
    }
    public List<Student> searchStudents(Integer entYear, String classNum, Boolean isAttend) {
    	List<Student> students = repository.findAll();
    	 
        // 入学年度で絞り込み
        if (entYear != null) {
            students = repository.findByEntYear(entYear);
        }
 
        // クラス番号で絞り込み
        if (classNum != null && !classNum.isEmpty()) {
            List<Student> classNumStudents = repository.findByClassNum(classNum);
            students.retainAll(classNumStudents);
        }
 
        // 在学フラグで絞り込み
        if (isAttend != null) {
            List<Student> isAttendStudents = repository.findByIsAttend(isAttend);
            students.retainAll(isAttendStudents);
        }
 
        return students;
     }
    
    
}