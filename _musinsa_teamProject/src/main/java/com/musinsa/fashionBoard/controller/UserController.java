package com.musinsa.fashionBoard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.musinsa.fashionBoard.model.UserVO;
import com.musinsa.fashionBoard.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	//생성자 주입
	private final UserService userService;
	
	//회원가입 페이지 출력 요청
	@GetMapping("/user/save")
	public String saveForm() {
		return "save";
	}
	
	@PostMapping("/user/save")
	public String save(@ModelAttribute UserVO userVO) {
		System.out.println("UserController.save");
		System.out.println("userVO = " + userVO);
		userService.save(userVO);
		return "login";
	}
	@GetMapping("/user/login")
	public String loginForm() {
		return "login";
	}
	
	@PostMapping("/user/login")
	public String login(@ModelAttribute UserVO userVO, HttpSession session) {
		UserVO loginResult = userService.login(userVO);
		if (loginResult != null) {
			// login 성공
			session.setAttribute("loginEmail", loginResult.getUserEmail());
			return "main";
		} else {
			// login 실패
			return "login";
		}
	}
	
	@GetMapping("/user/")
	public String findAll(Model model) {
		List<UserVO> userVOList = userService.findAll();
		model.addAttribute("userList", userVOList);
		return "list";	
	}
	
	@GetMapping("/user/{id}")
    public String findById(@PathVariable Long id, Model model) {
        UserVO userVO = userService.findById(id);
        model.addAttribute("user", userVO);
        return "detail";
    }

    @GetMapping("/user/update")
    public String updateForm(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        UserVO userVO = userService.updateForm(myEmail);
        model.addAttribute("updateUser", userVO);
        return "update";
    }

    @PostMapping("/user/update")
    public String update(@ModelAttribute UserVO userVO) {
        userService.update(userVO);
        return "redirect:/user/" + userVO.getId();
    }

    @GetMapping("/user/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/user/";
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @PostMapping("/user/email-check")
    public @ResponseBody String emailCheck(@RequestParam("userEmail") String userEmail) {
        System.out.println("userEmail = " + userEmail);
        String checkResult = userService.emailCheck(userEmail);
        return checkResult;
//	        if (checkResult != null) {
//	            return "ok";
//	        } else {
//	            return "no";
//	        }
    }
}
