package com.example.scoremanage.controller;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.scoremanage.model.Student;
import com.example.scoremanage.model.Teacher;
import com.example.scoremanage.service.StudentService;

import jakarta.validation.Valid;
 
@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
 
	/*
	@GetMapping("/student/") // HTTP GETリクエストを"/student/"エンドポイントで処理する
	public String top(Model model) { // モデルを受け取る
	    // 学生の一覧を取得してモデルに追加する
	    model.addAttribute("list", this.studentService.getStudentList());
	    // "student"テンプレート名を返す
	    return "student";
	}
	*/
	@GetMapping("/student/") // HTTP GETリクエストを"/student/"エンドポイントで処理する
	public String top(Model model, @AuthenticationPrincipal UserDetails user) { // モデルを受け取る
	    // 学生の一覧を取得してモデルに追加する
	    model.addAttribute("list", this.studentService.getResStudentList(user));
	    // "student"テンプレート名を返す
	    return "student";
	}
	@PostMapping("/student/")
	public String getFilteredStudents(
	        @RequestParam(name = "entYear", required = false) Integer entYear,
	        @RequestParam(name = "classNum", required = false) String classNum,
	        @RequestParam(name = "isAttend", required = false) Boolean isAttend,
	        Model model, @AuthenticationPrincipal Teacher teacher) {
	    // 検索操作の場合
	    List<Student> students = studentService.searchStudents(entYear, classNum, isAttend, teacher.getSchoolCd());
	    model.addAttribute("list", students != null ? students : new ArrayList<>());
	    model.addAttribute("searchPerformed", true); // 検索が行われたことを示すフラグ
	    return "student";
	}
	@GetMapping("/student/form/")
	public ModelAndView add(Student student, ModelAndView model) {
		model.addObject("student", student);
		model.setViewName("studentform");
		return model;
	}
 
 
    @PostMapping("/student/form/")
    public String add(@Valid @ModelAttribute Student student, BindingResult result, Model model,
                      RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails user) {
        if (result.hasErrors()) {
            return "studentform";
        }
        try {
            this.studentService.save(student, user);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("StudentErrorMessage", "学生番号が重複しています。");
            return "redirect:/student/form/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("StudentErrorMessage", e.getMessage());
            return "redirect:/student/form/";
        }
        return "redirect:/student/formcomplete/";
    }
 

//	@PostMapping("/student/form/")
//	public String add(@Validated @ModelAttribute @NonNull Student student, RedirectAttributes result, ModelAndView model,
//			RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails user) {
//		try {
//			this.studentService.save(student, user);
//			redirectAttributes.addFlashAttribute("exception", "");
// 
//		} catch (Exception e) {
//			redirectAttributes.addFlashAttribute("exception", e.getMessage());
//		}
//		return "studentformcomplete";
//	}
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
	    // 完了画面に移行
	    return "studentupdatecomplete";
	}

	// 登録後、完了メッセージを表示する
	@GetMapping("/student/formcomplete/")
	public ModelAndView formcomplete(Student student, ModelAndView model) {
		model.addObject("student", student);
		model.setViewName("studentformcomplete");
		return model;
	}
	// 更新後、完了メッセージを表示する
	@GetMapping("/student/updatecomplete/")
	public ModelAndView updatecomplete(Student student, ModelAndView model) {
		model.addObject("student", student);
		model.setViewName("studentupdatecomplete");
		return model;
	}
 
}