package com.musinsa.fashionBoard.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.musinsa.fashionBoard.mapper.FashionBoardMapper;
import com.musinsa.fashionBoard.model.FashionBoardDTO;
import com.musinsa.fashionBoard.model.ReplyDto;
import com.musinsa.fashionBoard.service.ReplyRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class FashionBoroadController {

	FashionBoardMapper fm;
	@Autowired
	ReplyRepository replyRepository;
	
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
	public String test(FashionBoardDTO dto, HttpSession session) {
		String user_id = (String)session.getAttribute("id");
		int ref = fm.selectMaxRef();
		System.out.println(ref);
		dto.setRef(ref+1);
//		dto.setUser_id(user_id);
		dto.setUser_id("test");
		fm.Insert(dto);
		return "redirect:fashionBoard";
	}
	
	@GetMapping("fashionBoardWritingForm")
	public String fashionBoardWritingForm () {
		return "fashionBoardWritingForm";
	}
	
	@GetMapping("/fashionBoard")
	public String fashionBoard(Model model, HttpSession session) {
		System.out.println("게시판 진입");
		List<FashionBoardDTO> flist = fm.selectAll();
		model.addAttribute("flist", flist);
		return "fashionBoard";
	}
	
	@GetMapping("/fashionBoardOne")
	public String fashionBoardOne(Model model, int board_num, HttpSession session, ReplyDto rDto ) {
		String sessionUser = (String)session.getAttribute("user_id");
		FashionBoardDTO fd = fm.selectOne(board_num);
		System.out.println(fd);
		fm.selectHits(board_num);
		model.addAttribute("sessionUser", sessionUser);
		model.addAttribute("selectOne",fd);
		
//		List<ReplyDto> rlist = replyRepository.findAll().stream().filter(t->Integer.valueOf(rDto.getBoard_num()).equals(board_num)).collect(Collectors.toList());
		List<ReplyDto> rlist = fm.selectBoard(board_num);
		
		model.addAttribute("rlist", rlist);
		System.out.println("r?"+rlist);
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
	
	@GetMapping("/fashionBoardReplyForm")
	public String fashionBoardReplyForm(FashionBoardDTO fDto, Model model, HttpSession session) {
		String id = (String)session.getAttribute("user_id");
		fDto.setUser_id(id);
		model.addAttribute("list", fDto);
		System.out.println("원래 ref"+fDto.getRef() + fDto.getRs_level() + fDto.getRs_step());
		return "fashionBoardReplyForm";
	}
	
	@GetMapping("/fashionBoardReplyProc")
	@Transactional
	public String fashionBoardReplyProc(FashionBoardDTO fDto, 
	Model model, HttpSession session, int ref, int rs_step, int rs_level) {
		String id = (String)session.getAttribute("user_id");
		fDto.setRef(ref);
		fm.updateReLevel(fDto);
		fDto.setRs_step(rs_step+1);
		fDto.setRs_level(rs_level+1);
		fm.reWriteBoard(fDto);
		return "redirect:/fashionBoard";
	}
	

	
	@GetMapping("/replyProc")
	public String insert(int board_num, ReplyDto fdto, Model model, HttpSession session) {
		LocalDateTime now = LocalDateTime.now();
		String fomatedNow = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH시 mm분 ss초"));
		String id = (String)session.getAttribute("id");
		fdto= ReplyDto.builder().user_id("test").reply_uploadDate(fomatedNow).user_id(id).board_num(board_num).reply_content(fdto.getReply_content()).build();
		replyRepository.save(fdto);
		model.addAttribute("dto", fdto);
		return "redirect:/fashionBoardOne?board_num="+fdto.getBoard_num();
	}
}
