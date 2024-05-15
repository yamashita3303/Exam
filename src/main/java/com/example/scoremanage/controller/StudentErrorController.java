package com.example.scoremanage.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class StudentErrorController {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("StudentErrorMessage", "学生番号が重複しています。");
        return "redirect:/student/form/";
    }
}