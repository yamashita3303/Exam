package com.example.scoremanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.scoremanage.model.Student;
import com.example.scoremanage.service.StudentService;

import io.micrometer.common.lang.NonNull;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping("/student/")
	public String index(Model model) {
	//	model.addAttribute("massage", "こんにちは");
		model.addAttribute("list", this.studentService.getStudentList());
		return "student";
	}
	
	@GetMapping("/student/form/")
	public ModelAndView add(Student student, ModelAndView model) {
		model.addObject("student", student);
		model.setViewName("studentform");
		return model;
	}
 
	@PostMapping("/student/form/")
	public String add(@Validated @ModelAttribute @NonNull Student student, RedirectAttributes result, ModelAndView model,
			RedirectAttributes redirectAttributes) {
		try {
			this.studentService.save(student);
			redirectAttributes.addFlashAttribute("exception", "");
 
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/student/";
	}
	
	/*
	@GetMapping("/student/update/{id}")
	public ModelAndView detail(@PathVariable(name = "id") Long id, Student student, ModelAndView model) {
		model.addObject("list", this.studentService.get(id));
		model.setViewName("studentupdate");
		return model;
	}
	*/
	
	// 編集画面を表示する
	@GetMapping("/student/update/{id}")
	public String student(Model model, Student student) {
			
		student = studentService.getOneBook(student.getId());
	    model.addAttribute(student);
			
	    return "studentupdate";
	}
	
	// 編集画面を表示する　上のが出来なければこれで
	/*
		@GetMapping("/detail/{id}")
		public String student(@PathVariable(name = "id") Long id,Model model, Student student) {
			student = studentService.getOneBook(id);
		    model.addAttribute(student);		
		    return "update";
		}
	*/
	
	// 本の情報を更新する
	@PostMapping("/student/update/{id}")
	public String update(@ModelAttribute @Validated Student student, BindingResult result, Model model) {
			
	    // バリデーションエラーの場合
	    if (result.hasErrors()) {
	        // 編集画面に遷移
	        return "studentupdate";
	    }
		
	    // 本を更新する
	    studentService.update(student);
		
	    // 本の一覧画面にリダイレクト
	    return "redirect:/student/";
	}

}
