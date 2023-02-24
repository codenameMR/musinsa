package com.musinsa.fashionBoard.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class FashionBoardDTO {
	
	@Id
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
	private int ref;
	private int rs_level;
	private int rs_step;
	private int likes;
	
//	@OneToOne(mappedBy = "fashionBoardDTO")
//	private ReplyDto replyDto;
}
