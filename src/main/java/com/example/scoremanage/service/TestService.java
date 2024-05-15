package com.example.scoremanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.scoremanage.model.Student;
import com.example.scoremanage.model.Teacher;
import com.example.scoremanage.model.Test;
import com.example.scoremanage.repository.StudentRepository;
import com.example.scoremanage.repository.TeacherRepository;
import com.example.scoremanage.repository.TestRepository;

import jakarta.transaction.Transactional;
 
@Service
@Transactional
public class TestService {
 
	@Autowired
	private TestRepository repository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private StudentRepository studentRepository;
 
	/**
	 * アドレス帳一覧の取得
	 * @return List<Test>
	 */
	public List<Student> getTestList() {
	    List<Student> entityList = this.studentRepository.findAll();
	    return entityList;
	}
	
	public List<Student> getResTestList(UserDetails user) {
        // ユーザーのユーザー名に対応する教師情報をデータベースから取得する
        Teacher teachers = this.teacherRepository.findByTeacherIdEquals(user.getUsername());
        // 教師の所属する学校コードに関連する学生エンティティのリストを取得する
        List<Student> entityList = this.studentRepository.findBySchoolCd(teachers.getSchoolCd());
        // 学生エンティティのリストを返す
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
    
    public List<Student> searchTests(Integer entYear, String classNum, String schoolCd) {
    	List<Student> students = studentRepository.findAll();
    	 
        // 入学年度で絞り込み
        if (entYear != null) {
            students = studentRepository.findByEntYear(entYear);
        }
 
        // クラス番号で絞り込み
        if (classNum != null && !classNum.isEmpty()) {
            List<Student> classNumStudents = studentRepository.findByClassNum(classNum);
            students.retainAll(classNumStudents);
        }
        
        // 学校での絞り込み（強制的に）
        List<Student> schoolCdStudents = studentRepository.findBySchoolCd(schoolCd);
        students.retainAll(schoolCdStudents);
        
        return students;
     }
    
    public List<Test> searchSubjects(/*Integer entYear, */String classNum, String subjectCd) {
    	List<Test> references = repository.findAll();
    	 
        // 入学年度で絞り込み
    	/*
        if (entYear != null) {
        	references = repository.findByEntYear(entYear);
        }
        */
 
        // クラス番号で絞り込み
        if (classNum != null) {
        	references = repository.findByClassNum(classNum);
        }
 
        // 科目で絞り込み
        if (subjectCd != null && !subjectCd.isEmpty()) {
            List<Test> subjectCdTests = repository.findBySubjectCd(subjectCd);
            references.retainAll(subjectCdTests);
        }

        return references;
     }
    
    public List<Test> searchStudentNos(String studentNo) {
    	List<Test> references = repository.findAll();
    	
    	// 学生番号で絞り込み
        if (studentNo != null && !studentNo.isEmpty()) {
            List<Test> classStudentNos = repository.findByStudentNo(studentNo);
            references.retainAll(classStudentNos);
        }

        return references;
    }
}