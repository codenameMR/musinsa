package com.musinsa.fashionBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.musinsa.fashionBoard.model.FashionBoardVO;



@Mapper
public interface FashionBoardMapper {
	@Select("select * from testtb")
	List<FashionBoardVO> findAll();
}
