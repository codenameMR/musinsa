package com.musinsa.fashionBoard.item;

import java.time.LocalDate;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

	    private int id;

	    private String name;

	    private String text; // 물건에 대한 상세설명

	    private int price; // 가격

	    private int count; // 판매 개수

	    private int stock; // 재고

	    private int isSoldout; // 상품 상태 (0 : 판매중 / 1 : 품절)

	    private int seller; // 판매자 아이디

	    private LocalDate createDate; // 상품 등록 날짜
}


