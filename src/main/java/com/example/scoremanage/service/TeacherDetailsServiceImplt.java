package com.example.scoremanage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.scoremanage.model.Teacher;
import com.example.scoremanage.repository.TeacherRepository;

@Service
public class TeacherDetailsServiceImplt implements UserDetailsService {

	@Autowired
	private TeacherRepository repository; // ユーザモデルのRepository

	/**
	 * ユーザの検索を行う
	 */
	@Override
	public UserDetails loadUserByUsername(String teacherId) throws UsernameNotFoundException {
		System.out.println("serach teacherId : " + teacherId);
		Teacher user = this.repository.findByTeacherIdEquals(teacherId); 
		System.out.println(user.toString());
		return user;
	}
}