package com.example.scoremanage.repository;

import java.util.List;

import com.example.scoremanage.form.GetForm;
import com.example.scoremanage.model.Student;
 
public interface StudentDao {
    // 登録されている学生情報を取得
	List<Student> findList(GetForm form);

}