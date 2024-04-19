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

import com.example.scoremanage.model.Subject;
import com.example.scoremanage.service.SubjectService;

import io.micrometer.common.lang.NonNull;

@Controller
public class SubjectController {
	@Autowired
	private SubjectService subjectService;

		@GetMapping("/subject/")
		public String index(Model model) {
			model.addAttribute("list", this.subjectService.getSubjectList());
			return "subject";
		}
		
		@GetMapping("/subject/form/")
		public ModelAndView add(Subject subject, ModelAndView model) {
			model.addObject("subject", subject);
			model.setViewName("subjectform");
			return model;
		}
	 
		@PostMapping("/subject/form/")
		public String add(@Validated @ModelAttribute @NonNull Subject subject, RedirectAttributes result, ModelAndView model,
				RedirectAttributes redirectAttributes) {
			try {
				this.subjectService.save(subject);
				redirectAttributes.addFlashAttribute("exception", "");
	 
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("exception", e.getMessage());
			}
			return "subjectformcomplate";
		}
		
		// 編集画面を表示する
		@GetMapping("/subject/update/{id}")
		public String subject(Model model, Subject subject) {
				
			subject = subjectService.getOneBook(subject.getId());
		    model.addAttribute(subject);
				
		    return "subjectupdate";
		}
		
		// 本の情報を更新する
		@PostMapping("/subject/update/{id}")
		public String update(@ModelAttribute @Validated Subject subject, BindingResult result, Model model) {
				
		    // バリデーションエラーの場合
		    if (result.hasErrors()) {
		        // 編集画面に遷移
		        return "subjectupdate";
		    }
			
		    // 本を更新する
		    subjectService.update(subject);
			
		    // 完了画面に移行
		    return "subjectupdatecomplate";
		}
		
		// 登録後、完了メッセージを表示する
		@GetMapping("/subject/formcomplate/")
		public ModelAndView formcomplate(Subject subject, ModelAndView model) {
			model.addObject("subject", subject);
			model.setViewName("subjectformcomplate");
			return model;
		}
		
		// 更新後、完了メッセージを表示する
		@GetMapping("/subject/updatecomplate/")
		public ModelAndView updatecomplate(Subject subject, ModelAndView model) {
			model.addObject("subject", subject);
			model.setViewName("subjectupdatecomplate");
			return model;
		}
	}

