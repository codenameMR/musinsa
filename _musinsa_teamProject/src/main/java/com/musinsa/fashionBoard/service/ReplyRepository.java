package com.musinsa.fashionBoard.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musinsa.fashionBoard.model.ReplyDto;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyDto, Integer>{
	
}
