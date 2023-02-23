package com.musinsa.fashionBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.musinsa.fashionBoard.model.FashionBoardDTO;

@Mapper
public interface FashionBoardMapper {
	
	//글쓰기
	@Insert("INSERT INTO FASHION_BOARD(USER_ID, TITLE, CONTENT, UPLOAD_DATE, HASH_TAG, PICTURE_1, PICTURE_2, PICTURE_3)"
			+ " VALUES(#{user_id}, #{title}, #{content}, NOW(), #{hash_tag}, #{picture_1}, #{picture_2}, #{picture_3})")
	void Insert(FashionBoardDTO fd);
	
	//글전체보기
	@Select("SELECT * FROM FASHION_BOARD")
	List<FashionBoardDTO> selectAll();

	//글상세보기
	@Select("SELECT * FROM FASHION_BOARD WHERE board_num = #{board_num}")
	FashionBoardDTO selectOne(int boardNum);
	
	//글수정s
	@Update("UPDATE FASHION_BOARD SET USER_ID=#{user_id} , TITLE=#{title} , CONTENT=#{content} , HASH_TAG=#{hash_tag} , PICTURE_1=#{picture_1} , PICTURE_2=#{picture_2}, PICTURE_3=#{picture_3}"
			+ " WHERE BOARD_NUM = #{board_num}")
	void updateBoard(FashionBoardDTO fd);
	
	//글삭제
	@Delete("DELETE FROM FASHION_BOARD WHERE BOARD_NUM = #{board_num}")
	void delete(int boardNum);
}
