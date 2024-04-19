package com.example.scoremanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.scoremanage.model.Test;
import com.example.scoremanage.service.TestService;

@Controller
public class TestController {
	@Autowired
	private TestService testService;

		@GetMapping("/test/")
		public String index(Model model) {
			model.addAttribute("list", this.testService.getTestList());
			return "test";
		}
		
		@PostMapping("/test/")
	    public String getFilteredTests(
	    		//@RequestParam(name = "entYear", required = false) Integer entYear,
	            @RequestParam(name = "classNum", required = false) String classNum,
	            @RequestParam(name = "subjectCd", required = false) String subjectCd,
	            @RequestParam(name = "no", required = false) Integer no,
	            Model model) {
	        // 検索操作の場合
	        List<Test> tests = testService.searchTests(/*entYear, */classNum, subjectCd, no);
	        model.addAttribute("list", tests);
			//listという名前は、controllerの@GetMapping("/test/")　と　templatesのtestのth:each="item, stat : ${list}"　と同じにする
	        return "test";
	    }
		
		@GetMapping("/test/reference/")
		public String testreference(Model model) {
			model.addAttribute("list", this.testService.getTestList());
			return "testreference";
		}
		
		@PostMapping("/test/reference/")
	    public String getFilteredReferences(
	    		//@RequestParam(name = "entYear", required = false) Integer entYear,
	            @RequestParam(name = "classNum", required = false) String classNum,
	            @RequestParam(name = "subject", required = false) String subject,
	            @RequestParam(name = "studentNo", required = false) String studentNo,
	            Model model) {
	        // 検索操作の場合
	        List<Test> references = testService.searchReferences(/*entYear, */classNum, subject, studentNo);
	        model.addAttribute("list", references);
			//listという名前は、controllerの@GetMapping("/test/reference/")　と　templatesのstudentのth:each="item, stat : ${list}"　と同じにする
	        return "testreference";
	    }

	}

