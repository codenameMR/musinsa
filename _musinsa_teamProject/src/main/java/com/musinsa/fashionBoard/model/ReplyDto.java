package com.musinsa.fashionBoard.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reply_num;
	private int board_num;
	private String reply_uploadDate;
	private String reply_content;
	private String user_id;
	
//	@ToString.Exclude
//	@JoinColumn(name="board_num")
//	@OneToOne(cascade = CascadeType.ALL)
//	private FashionBoardDTO fashionBoardDTO;
//	
	

}
