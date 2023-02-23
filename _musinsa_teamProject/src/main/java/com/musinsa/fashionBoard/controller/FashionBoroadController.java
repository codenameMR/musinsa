package com.musinsa.fashionBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.musinsa.fashionBoard.mapper.FashionBoardMapper;
import com.musinsa.fashionBoard.model.FashionBoardDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class FashionBoroadController {

	FashionBoardMapper fm;
	
	@Autowired
	public FashionBoroadController(FashionBoardMapper fm) {
		super();
		this.fm = fm;
	}
	@GetMapping("/test")
	public String test() {
		
		return "test";
	}

	int i = 0;
	@GetMapping("/insert")
	public String test(FashionBoardDTO dto) {
		dto.setUser_id("testid"+i);
		i++;
		fm.Insert(dto);
		return "redirect:fashionBoard";
	}
	
	@GetMapping("/fashionBoard")
	public String fashionBoard(Model model, HttpSession session) {
		List<FashionBoardDTO> flist = fm.selectAll();
		model.addAttribute("flist", flist);
		return "fashionBoard";
	}
	
	@GetMapping("/fashionBoardOne")
	public String fashionBoardOne(Model model, int board_num, HttpSession session) {
		String sessionUser = (String)session.getAttribute("user_id");
		FashionBoardDTO fd = fm.selectOne(board_num);
		model.addAttribute("sessionUser", sessionUser);
		model.addAttribute("selectOne",fd);
		return "fashionBoardOne";
	}
	
	@GetMapping("/fashionBoardUpdate")
	public void fashionBoardUpdate(Model model, int board_num) {
		FashionBoardDTO fd = fm.selectOne(board_num);
		model.addAttribute("selectOne",fd);
	}
	
	@PostMapping("/fashionBoardUpdate")
	public String fashionBoardUpdate(FashionBoardDTO fd) {
		fm.updateBoard(fd);
		return "redirect:/fashionBoard";
	}
	
	@GetMapping("/fashionBoardDelete")
	public String fashionBoardDelete(int board_num) {
		fm.delete(board_num);
		return "redirect:/fashionBoard";
	}
	
}
