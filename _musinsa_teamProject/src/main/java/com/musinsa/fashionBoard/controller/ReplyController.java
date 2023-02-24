package com.musinsa.fashionBoard.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.musinsa.fashionBoard.model.ReplyDto;
import com.musinsa.fashionBoard.service.ReplyRepository;

import jakarta.servlet.http.HttpSession;

//@Controller
public class ReplyController {
//	@Autowired
	ReplyRepository replyRepository;
	
	
	public String insert(int board_num, ReplyDto dto, Model model, HttpSession session) {
		LocalDateTime now = LocalDateTime.now();
		String fomatedNow = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH시 mm분 ss초"));
		String id = (String)session.getAttribute("id");
		dto.setUser_id("test");
		dto= ReplyDto.builder().reply_uploadDate(fomatedNow).user_id(id).board_num(board_num).build();
		replyRepository.save(dto);
		model.addAttribute("dto", dto);
		return "fashionBoardOne";
				
				
				
//				rDto.reply_num.reply_id("dkdk").reply_parentNum(1).reply_content("happy").build();
	}

}
