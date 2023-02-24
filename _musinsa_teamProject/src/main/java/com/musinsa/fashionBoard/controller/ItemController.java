package com.musinsa.fashionBoard.controller;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

//import com.example.weblogin.config.auth.PrincipalDetails;
import com.musinsa.fashionBoard.item.Item;
//import com.musinsa.fashionBoard.item.ItemRepository;
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
//   @Autowired
//   private ItemRepository itemRepository;
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
   public String itemUpload( @RequestParam(value = "file", required = false) MultipartFile file,Item item, HttpSession session, Model model)throws Exception {
      String oriImgName = file.getOriginalFilename();
        String imgName = "";
      System.out.println("filee>>>>"+file);
      System.out.println("filee>>>>?>>>>>>>>>>>>>>>>"+file.getOriginalFilename());
        String imgPath = System.getProperty("user.dir") + "/src/main/resources/static/files/";
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+imgPath);
        
        UUID uuid = UUID.randomUUID();
        String savedFileName = uuid + "_" + oriImgName; // 파일명 -> imgName
        
        imgName = savedFileName;
        File saveFile = new File(imgPath, imgName);
        file.transferTo(saveFile);
        item.setImg_name(imgName);
        item.setImg_path("/files/" + imgName);

      String id = (String)session.getAttribute("id");
       User list = userMapper.selectId(id);
       int seller_id = list.getId();
       item.setSeller_id(seller_id);
       
      LocalDate createDate = LocalDate.now();
      item.setCreateDate(createDate);
      
      System.out.println("***************");
      System.out.println(item);
//      itemRepository.save(item);
      itemMapper.upload(item);
      
      model.addAttribute("list", list);
      return "seller/sellerPage";
   }
   
    }
   