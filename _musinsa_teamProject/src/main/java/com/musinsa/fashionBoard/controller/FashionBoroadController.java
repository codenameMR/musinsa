package com.musinsa.fashionBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.musinsa.fashionBoard.mapper.FashionBoardMapper;
import com.musinsa.fashionBoard.model.FashionBoardVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class FashionBoroadController {
	@Autowired
	FashionBoardMapper fm;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<FashionBoardVO> list = fm.findAll();
		model.addAttribute("list", list);
		return "index";
	}
}
