package com.example.scoremanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.scoremanage.form.GetForm;
import com.example.scoremanage.model.Student;
import com.example.scoremanage.repository.StudentDao;

 
@Service
@Transactional
public class StudentDaoService {
 
    private final StudentDao dao;
 
    @Autowired
    public StudentDaoService(StudentDao dao) {
        this.dao = dao;
    }
 
    public List<Student> findList(GetForm form) {
        return dao.findList(form);
        
 }
}