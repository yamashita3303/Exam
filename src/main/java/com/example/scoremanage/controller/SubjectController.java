package com.example.scoremanage.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.scoremanage.model.Subject;
import com.example.scoremanage.service.SubjectService;

@Controller
public class SubjectController {
	@Autowired
	private SubjectService subjectService;

//		@GetMapping("/subject/") // HTTP GETリクエストを"/student/"エンドポイントで処理する
//		public String top(Model model, @AuthenticationPrincipal UserDetails user) { // モデルを受け取る
//		    // 学生の一覧を取得してモデルに追加する
//		    model.addAttribute("list", this.subjectService.getResSubjectList(user));
//		    // "student"テンプレート名を返す
//		    return "subject";
//		}
		
		@GetMapping("/subject/")
		public String top(Model model, @AuthenticationPrincipal UserDetails user) {
		    List<Subject> subjectList = subjectService.getResSubjectList(user);
		    model.addAttribute("subjectList", subjectList);
	
		    // 他の必要な情報もモデルに追加
		    model.addAttribute("list", this.subjectService.getResSubjectList(user));
	
		    return "subject";
		}
	
		@GetMapping("/subject/form/")
		public ModelAndView add(ModelAndView model) {
		    model.addObject("subject", new Subject());
		    model.setViewName("subjectform");
		    return model;
		}
	
		@PostMapping("/subject/form/")
		public String add(@Validated @ModelAttribute Subject subject,
		                  BindingResult bindingResult,
		                  RedirectAttributes redirectAttributes,
		                  @AuthenticationPrincipal UserDetails user) {
		    if (bindingResult.hasErrors()) {
		    	System.out.println("ここがうえ");
		        return "subjectform"; // バリデーションエラーがある場合はフォームページに戻る
		    }
	
		    try {
		        this.subjectService.save(subject, user);
		    } catch (DataIntegrityViolationException e) {
		        redirectAttributes.addFlashAttribute("SubjectErrorMessage", "科目コードが重複しています。");
		        return "redirect:/subject/form/";
		    } catch (Exception e) {
		        redirectAttributes.addFlashAttribute("SubjectErrorMessage", e.getMessage());
		        return "redirect:/subject/form/";
		    }
	
		    return "redirect:/subject/formcomplete/";
		}
		
		
		// 編集画面を表示する
	    @GetMapping("/subject/update/{id}")
	    public String subject(@PathVariable("id") Long id, Model model) {
	        Subject subject = subjectService.getOneBook(id);
	        model.addAttribute("subject", subject);
	        return "subjectupdate";
	    }

	    // 本の情報を更新する
	    @PostMapping("/subject/update/{id}")
	    public String update(@ModelAttribute @Validated Subject subject, BindingResult result, RedirectAttributes redirectAttributes) {
	        // バリデーションエラーの場合
	        if (result.hasErrors()) {
	            return "subjectupdate"; // 編集画面に遷移
	        }

	        try {
	            // 本を更新する
	            subjectService.update(subject);
	        } catch (DataIntegrityViolationException e) {
	            // 重複エラーの場合
	            redirectAttributes.addFlashAttribute("SubjectErrorMessage", "科目コードが重複しています。");
	            return "redirect:/subject/update/" + subject.getId();
	        } catch (Exception e) {
	            // その他のエラーの場合
	            redirectAttributes.addFlashAttribute("SubjectErrorMessage", e.getMessage());
	            return "redirect:/subject/update/" + subject.getId();
	        }

	        // 完了画面に移行
	        return "redirect:/subject/updatecomplete/";
	    }
	    
		
		@GetMapping("/subject/delete/{id}")
		public ModelAndView delete(@PathVariable(name = "id") Long id, Subject subject, ModelAndView model) {
			//model.addAttribute("name", subjectService.get());
			model.addObject("deletelist", this.subjectService.get(id));
			model.setViewName("subjectdelete");
			return model;
		}
		
		// 登録後、完了メッセージを表示する
		@GetMapping("/subject/formcomplete/")
		public ModelAndView formcomplete(Subject subject, ModelAndView model) {
			model.addObject("subject", subject);
			model.setViewName("subjectformcomplete");
			return model;
		}
		
		// 更新後、完了メッセージを表示する
		@GetMapping("/subject/updatecomplete/")
		public ModelAndView updatecomplete(Subject subject, ModelAndView model) {
			model.addObject("subject", subject);
			model.setViewName("subjectupdatecomplete");
			return model;
		}
		
		// 削除後、完了メッセージを表示する
		@GetMapping("/subject/deletecomplete/{id}")
		public String subjectdeletecomplete(@PathVariable Long id, Model model) {
		    // IDをモデルに設定する
			this.subjectService.delete(id);
		    return "subjectdeletecomplete"; // リダイレクト先のビュー名を返す
		}
		
	}