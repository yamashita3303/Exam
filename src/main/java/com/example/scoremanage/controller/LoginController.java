package com.example.scoremanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.scoremanage.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    
    @GetMapping("/")
    public String showLoginForm() {
        return "login"; // login.htmlに遷移
    }

    @PostMapping("/")
    public String loginUser(String loginId, String password, RedirectAttributes redirectAttributes, Model model) {
        String name = loginService.getNameForLoginIdAndPassword(loginId, password);
        if (name != null) {
            // ログイン成功の場合は、名前をモデルに追加して、メインページにリダイレクト
            model.addAttribute("loggedInUserName", name);
            return "redirect:/mainmenu";
        } else {
            // ログイン失敗の場合は、エラーメッセージを追加してログインページに戻る
            redirectAttributes.addFlashAttribute("errorMessage", "ユーザーIDまたはパスワードが間違っています");
            return "redirect:/";
        }
    }
}
