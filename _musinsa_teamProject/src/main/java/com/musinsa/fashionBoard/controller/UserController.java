package com.musinsa.fashionBoard.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.musinsa.fashionBoard.mapper.User;
import com.musinsa.fashionBoard.mapper.UserMapper;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserMapper userMapper;

	@GetMapping("/") // return 과 달라도 상관 없음
	public String main(@RequestParam(value = "username", required = false,defaultValue = "손님") String id, Model model) {
		model.addAttribute("id",id);
		return "layout/index";
	}

	@GetMapping("/signup")
	public String signUpForm() {
		return "signup";
	}

	@PostMapping("/signup")
	public String signUp(User user,Model model) {
		LocalDateTime createDate = LocalDateTime.now();
		user.setDate(createDate);
	
		userMapper.save(user);

		return "signin";
	}

	@GetMapping("/signin")
	public String signInForm() {
		return "signin";
	}

	@PostMapping("signin")
	public String signIn(@RequestParam("username") String id, User user,Model model, HttpSession session) {
		User loginResult = userMapper.login(user);
		
		System.out.println(id);
		
		if(loginResult != null) {			
			// login 성공
			session.setAttribute("id", id);
			session.setMaxInactiveInterval(30*1);
			model.addAttribute("user",loginResult);
			System.out.println("로그인 성공!");
			return "layout/index";
		} else {
			// login 실패
			System.out.println("로그인 실패!");
			return "signup";
		}
	}
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		System.out.println("로그아웃 성공!");
		return "redirect:/";
	}
	
}
