package com.musinsa.fashionBoard.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FashionBoardDTO {
	
	private int board_num;
	private String user_id;
	private String title;
	private String content;
	private int hits;
	private String upload_date;
	private String hash_tag;
	private String picture_1;
	private String picture_2;
	private String picture_3;
}
