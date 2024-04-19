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

import com.example.scoremanage.model.Test;
import com.example.scoremanage.service.TestService;

import io.micrometer.common.lang.NonNull;

@Controller
public class TestController {
	@Autowired
	private TestService testService;

		@GetMapping("/test/")
		public String index(Model model) {
			model.addAttribute("list", this.testService.getTestList());
			return "test";
		}
		
		@GetMapping("/test/form/")
		public ModelAndView add(Test test, ModelAndView model) {
			model.addObject("test", test);
			model.setViewName("testform");
			return model;
		}
	 
		@PostMapping("/test/form/")
		public String add(@Validated @ModelAttribute @NonNull Test test, RedirectAttributes result, ModelAndView model,
				RedirectAttributes redirectAttributes) {
			try {
				this.testService.save(test);
				redirectAttributes.addFlashAttribute("exception", "");
	 
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("exception", e.getMessage());
			}
			return "redirect:/test/";
		}
		
		// 編集画面を表示する
		@GetMapping("/test/update/{id}")
		public String subject(Model model, Test test) {
				
			test = testService.getOneBook(test.getId());
		    model.addAttribute(test);
				
		    return "subjectupdate";
		}
		
		// 本の情報を更新する
		@PostMapping("/test/update/{id}")
		public String update(@ModelAttribute @Validated Test test, BindingResult result, Model model) {
				
		    // バリデーションエラーの場合
		    if (result.hasErrors()) {
		        // 編集画面に遷移
		        return "testupdate";
		    }
			
		    // 本を更新する
		    testService.update(test);
			
		    // 本の一覧画面にリダイレクト
		    return "redirect:/test/";
		}
	}

