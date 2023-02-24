package com.musinsa.product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.boot.test.context.SpringBootTest;

import com.musinsa.fashionBoard.mapper.FashionBoardMapper;
import com.musinsa.fashionBoard.model.FashionBoardDTO;
import com.musinsa.fashionBoard.model.ReplyDto;
import com.musinsa.fashionBoard.service.ReplyRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class MusinsaTeamProjectApplicationTests {

	@Autowired
	FashionBoardMapper fashionBoardMapper;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@Disabled
	void update() {
		FashionBoardDTO fDto = FashionBoardDTO.builder().board_num(30).ref(1).rs_level(0).rs_step(1).build(); 
		fashionBoardMapper.updateReLevel(fDto);
		log.info(fDto);
	} 
	
	@Test
	@Disabled
	void updateStepLevel() {
		FashionBoardDTO fDto = FashionBoardDTO.builder().board_num(31).ref(1).rs_level(1).rs_step(1).build();
		fashionBoardMapper.updateStepLevel(fDto);
		log.info(fDto);
	}
	@Test
	@Disabled
	void updateRelevel() {
		FashionBoardDTO fdto = FashionBoardDTO.builder().board_num(1064).ref(31).rs_level(1).rs_step(1).build();
		fashionBoardMapper.updateReLevel(fdto);
		log.info(fdto);
	}
	@Autowired
	ReplyRepository replyRepository;
	
	@Test
	@Disabled
	void insert() {
		LocalDateTime now = LocalDateTime.now();
		String fomatedNow = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH시 mm분 ss초"));
		ReplyDto replyDto = ReplyDto.builder().board_num(1).reply_content("happy").reply_uploadDate(fomatedNow).build();
		log.info(replyDto);
		replyRepository.save(replyDto);
	}
	
	@Test
	@Disabled
	void delete() {
		ReplyDto rd = ReplyDto.builder().reply_num(2).build();
		log.info(rd);
		replyRepository.delete(rd);
	}
	
	@Test
	void updateReply() {
		
	}
	
	@Test
	void select() {
		ReplyDto rd = ReplyDto.builder().reply_num(1).build();
	}
	
	@Test
	void aaa() {
		List<ReplyDto> rlist1 = replyRepository.findAll().stream().filter(t->Integer.valueOf(1).equals(1)).collect(Collectors.toList());
		List<ReplyDto> rlist2 = replyRepository.findAll();
		List<ReplyDto> rlist3 = rlist2.stream().filter(t->Integer.valueOf(1).equals(1)).collect(Collectors.toList());
		System.out.println("r2?"+rlist2);
		System.out.println("r3?"+rlist3);
//		log.info(rlist1);
//		log.info(rlist2);
		log.info(rlist3);
	}

}
