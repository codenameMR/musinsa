package com.musinsa.fashionBoard.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.musinsa.fashionBoard.item.Item;
import com.musinsa.fashionBoard.mapper.ItemMapper;
import com.musinsa.fashionBoard.mapper.User;
import com.musinsa.fashionBoard.mapper.UserMapper;

import jakarta.servlet.http.HttpSession;

@Controller
public class ItemController {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ItemMapper itemMapper;

	// 상품 등록 페이지 - 판매자만 가능
	@GetMapping("/item/new")
	public String itemSaveForm(Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		User list = userMapper.selectId(id);
		model.addAttribute("list", list);
		System.out.println(list);

		return "seller/itemForm";

	}

	@PostMapping("/item/new")
	public String itemUpload(Item item, HttpSession session, Model model) {
		String id = (String)session.getAttribute("id");
    	User list = userMapper.selectId(id);
    	
    	int seller_id = list.getId();
    	item.setSeller(seller_id);
    	
		LocalDate createDate = LocalDate.now();
		item.setCreateDate(createDate);
		
		System.out.println("***************");
		System.out.println(item);
		
		itemMapper.upload(item);
		
		model.addAttribute("list", list);
		return "seller/sellerPage";
	}
	
	
}