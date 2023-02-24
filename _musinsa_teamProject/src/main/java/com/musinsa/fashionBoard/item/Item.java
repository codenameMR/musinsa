package com.musinsa.fashionBoard.item;

import java.time.LocalDate;
import java.time.LocalDateTime;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {

       private int id;

       private String name;

       private String text; // 물건에 대한 상세설명

       private int price; // 가격

       private int count; // 판매 개수

       private int stock; // 재고

       private int isSoldout; // 상품 상태 (0 : 판매중 / 1 : 품절)

       private int seller_id; // 판매자 아이디

       private LocalDate createDate; // 상품 등록 날짜
       
       private String img_name; // 상품 

       private String img_path; // 상품 등록 날짜
}
