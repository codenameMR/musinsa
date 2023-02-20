package com.musinsa.fashionBoard.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sale {
	private int id;
	private int total_count;
	private int seller_id;
	private String role;
	
}
