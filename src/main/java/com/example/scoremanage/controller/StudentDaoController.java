package com.example.scoremanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.scoremanage.form.GetForm;
import com.example.scoremanage.model.Student;
import com.example.scoremanage.service.StudentDaoService;

 
@Controller
@RequestMapping("/")
public class StudentDaoController {
     
    private final StudentDaoService studentDaoService;
     
    @Autowired
    public StudentDaoController(StudentDaoService studentDaoService) {
        this.studentDaoService = studentDaoService;
    }
 
    /**
     * 日記アプリの一覧画面を表示
     * @param model
     * @return resources/templates/list.html
     */
    @GetMapping
    public String StudentList(
        @ModelAttribute GetForm form,
        Model model
    ) {
        List<Student> list = studentDaoService.findList(form);
        model.addAttribute("list", list);
        model.addAttribute("getForm", form);
        return "list";
    }
}