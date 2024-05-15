package com.example.scoremanage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.scoremanage.model.Student;
import com.example.scoremanage.model.Subject;
import com.example.scoremanage.model.Teacher;
import com.example.scoremanage.model.Test;
import com.example.scoremanage.repository.SubjectRepository;
import com.example.scoremanage.service.StudentService;
import com.example.scoremanage.service.SubjectService;
import com.example.scoremanage.service.TestService;

@Controller
public class TestController {
	@Autowired
	private TestService testService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private SubjectRepository subjectRepository;

		@GetMapping("/test/")
		public String index(Model model, @AuthenticationPrincipal UserDetails user) {
			model.addAttribute("list", this.testService.getResTestList(user));
			model.addAttribute("kamokulist", this.subjectService.getAll());
			return "test";
		}
		
		@PostMapping("/test/")
	    public String getFilteredTests(
    		@RequestParam(name = "entYear", required = false) Integer entYear,
            @RequestParam(name = "classNum", required = false) String classNum,
            @RequestParam(name = "subjectCd", required = false) String subjectCd,
            @RequestParam(name = "no", required = false) Integer no,
            Model model, @AuthenticationPrincipal Teacher teacher) {
			// 検索操作の場合
	        List<Student> tests = testService.searchTests(entYear, classNum, teacher.getSchoolCd());
	        model.addAttribute("list", tests);
	        model.addAttribute("code",subjectCd);
	        model.addAttribute("kaisu",no);
	        model.addAttribute("schoolCd",teacher.getSchoolCd());
	        return "test";
	    }
		
		@PostMapping("/test/complate/")
		public String complate(Test test, @AuthenticationPrincipal UserDetails user,
	            @RequestParam(name = "point", required = false) Integer point[],
	            @RequestParam(name = "no", required = false) Integer no) {
			System.out.println(test);
			String studentNo[] = test.getStudentNo().split(",");
			String subjectCd[] = test.getSubjectCd().split(",");
			String schoolCd[] = test.getSchoolCd().split(",");
			String classNum[] = test.getClassNum().split(",");

			//int i = studentNo.length;
			//ループ処理１
			for (int i = 0; i < studentNo.length; i++) {
			//testModel型をインスタンス化する
				Test tests = new Test();
			//インスタンス化したtestModelにそれぞれ代入
	            tests.setStudentNo(studentNo[i]);
	            tests.setSubjectCd(subjectCd[i]);
	            tests.setSchoolCd(schoolCd[i]);
	            tests.setNo(no);
	            tests.setPoint(point[i]);
	            tests.setClassNum(classNum[i]);
	            this.testService.save(tests);
			}
			//ループ処理１
			return "testcomplate";
		}
		
		@GetMapping("/test/reference/")
		public String testreference(Model model, @AuthenticationPrincipal UserDetails user) {
			model.addAttribute("list", this.testService.getResTestList(user));
			model.addAttribute("kamokulist", this.subjectService.getAll());
			model.addAttribute("studentlist", this.studentService.getAll());
			return "testreference";
		}
		
		@PostMapping("/test/reference/subjects/")
	    public String getFilteredSubjects(
	    		@RequestParam(name = "entYear", required = false) Integer entYear,
	            @RequestParam(name = "classNum", required = false) String classNum,
	            @RequestParam(name = "subjectCd", required = false) String subjectCd,
	            Model model) {
	        System.out.println("-----------------");
			List<Student> students = studentService.searchEntYears(entYear);
			System.out.println(students);
			//List<ArrayList> num = students.getNo();
	        // 検索操作の場合
	        List<Test> subjects = testService.searchSubjects(/*entYear, */classNum, subjectCd);
	        System.out.println(subjects);
	        model.addAttribute("subjectslist", subjects);
	        System.out.println("-----------------");
			//listという名前は、controllerの@GetMapping("/test/reference/")　と　templatesのstudentのth:each="item, stat : ${list}"　と同じにする
	        return "testreference";
	    }
		@PostMapping("/test/reference/studentnos/")
	    public String getFilteredStudentNos(
	            @RequestParam(name = "studentNo", required = false) String studentNo,
	            Model model) {
	        // 検索操作の場合
	        List<Test> studentNos = testService.searchStudentNos(studentNo);
	        System.out.println(studentNos);
	        System.out.println("-----------------");
	        model.addAttribute("studentNoslist", studentNos);
	        
	        List<String> subjectCds = new ArrayList<>(); // SubjectCd の数値を格納するリストを作成
	        for (Test testModel : studentNos) {
	            subjectCds.add(testModel.getSubjectCd()); // 各 TestModel オブジェクトから SubjectCd の数値を取得し、リストに追加
	        }
	        System.out.println("StudentModelのsubjectCd =" + subjectCds);
	        
	        List<String> subjectNames = new ArrayList<>();
	        for (String kariname : subjectCds) {
	        	Subject sub = this.subjectRepository.findByCd(kariname);
	        	//subjectNames.add(sub);
	        	System.out.println("kariname" + kariname);
	        	System.out.println(sub);
	        	subjectNames.add(sub.getName());
	        }
	        System.out.print(subjectNames);
	        
	        model.addAttribute("subjectNameslist", subjectNames);
	        return "testreference";
	    }

	}

