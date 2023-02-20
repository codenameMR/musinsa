package com.musinsa.fashionBoard.user;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

	private int id;
	private String username;
	private String password;
	private String name;
	private String email;
	private String address;
	private String phone;
	private String role; // 권한 (회원 / 관리자)
	private int coin; // 구매자 - 충전한 돈 / 판매자 - 수익
	private LocalDateTime date;
}
