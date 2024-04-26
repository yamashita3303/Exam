package com.example.scoremanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.scoremanage.model.Student;
import com.example.scoremanage.model.Teacher;
import com.example.scoremanage.repository.ClassNumRepository;
import com.example.scoremanage.repository.StudentRepository;
import com.example.scoremanage.repository.TeacherRepository;

import jakarta.transaction.Transactional;
 
@Service
@Transactional
public class StudentService {
 
	@Autowired
	private StudentRepository repository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private ClassNumRepository classNumRepository;
 
	/**
	 * アドレス帳一覧の取得
	 * @return List<Student>
	 */
	/*
	public List<Student> getStudentList() {
	    List<Student> entityList = this.repository.findAll();
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
    public List<Student> getResStudentList(UserDetails user) {
        // ユーザーのユーザー名に対応する教師情報をデータベースから取得する
        Teacher teachers = this.teacherRepository.findByTeacherIdEquals(user.getUsername());
        // 教師の所属する学校コードに関連する学生エンティティのリストを取得する
        List<Student> entityList = this.repository.findBySchoolCd(teachers.getSchoolCd());
        // 学生エンティティのリストを返す
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
	public void save(@NonNull Student student, UserDetails user) {
		// フラグの初期値にtrueを設定する
		student.setIsAttend(true);
		// ユーザーのユーザー名に対応する教師情報をデータベースから取得する
        Teacher teachers = this.teacherRepository.findByTeacherIdEquals(user.getUsername());
        // TeacherIdから取得したSchoolCdを初期値に設定する
        student.setSchoolCd(teachers.getSchoolCd());
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
    public List<Student> searchStudents(Integer entYear, String classNum, Boolean isAttend,String schoolCd) {
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
        
        List<Student> schoolCdStudents = repository.findBySchoolCd(schoolCd);
        students.retainAll(schoolCdStudents);
 
        return students;
     }
    
    
}