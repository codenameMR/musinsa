package com.musinsa.fashionBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.musinsa.fashionBoard.mapper.User;
import com.musinsa.fashionBoard.mapper.UserMapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

// 판매자에 해당하는 페이지 관리
// 판매자페이지, 상품관리, 판매내역

@RequiredArgsConstructor
@Controller
public class SellerPageController {
	

	@Autowired
	private UserMapper userMapper;
	
	// 판매자 프로필 페이지 접속
    @RequestMapping("/seller/sellerPage")
    public String sellerPage(Model model, HttpSession session) {
    	String id = (String)session.getAttribute("id");
    	User list = userMapper.selectId(id);
    	model.addAttribute("list", list);
    	System.out.println(list);
    	return "seller/sellerPage";
    	
    }

    

  /*  // 상품 관리 페이지
    @GetMapping("/seller/manage/{id}")
    public String itemManage(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails.getUser().getId() == id) {
            // 로그인이 되어있는 판매자의 id와 상품관리 페이지에 접속하는 id가 같아야 함
            List<Item> allItem = itemService.allItemView();
            List<Item> userItem = new ArrayList<>();

            // 자신이 올린 상품만 가져오기
            for(Item item : allItem) {
                if(item.getSeller().getId() == id) {
                    userItem.add(item);
                }
            }

            model.addAttribute("seller", userPageService.findUser(id));
            model.addAttribute("userItem", userItem);

            return "seller/itemManage";
        } else {
            return "redirect:/main";
        }
    }

    // 판매 내역 조회 페이지
    @GetMapping("/seller/salelist/{id}")
    public String saleList(@PathVariable("id")Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        // 로그인이 되어있는 유저의 id와 판매내역에 접속하는 id가 같아야 함
        if (principalDetails.getUser().getId() == id) {

            Sale sales = saleService.findSaleById(id);
            List<SaleItem> saleItemList = saleService.findSellerSaleItems(id);

            model.addAttribute("sales", sales);
            model.addAttribute("totalCount", sales.getTotalCount());
            model.addAttribute("sellerSaleItems", saleItemList);
            model.addAttribute("seller", userPageService.findUser(id));

            return "seller/saleList";
        }
        else {
            return "redirect:/main";
        }*/
    }

