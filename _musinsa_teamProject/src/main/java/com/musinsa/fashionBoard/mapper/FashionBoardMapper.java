package com.musinsa.fashionBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.musinsa.fashionBoard.model.FashionBoardDTO;
import com.musinsa.fashionBoard.model.ReplyDto;

@Mapper
public interface FashionBoardMapper {
	
	//글쓰기
	@Insert("INSERT INTO FASHION_BOARD(USER_ID, TITLE, CONTENT, UPLOAD_DATE, HASH_TAG, PICTURE_1, PICTURE_2, PICTURE_3, ref, rs_step, rs_level)"
			+ " VALUES(#{user_id}, #{title}, #{content}, NOW(), #{hash_tag}, #{picture_1}, #{picture_2}, #{picture_3}, #{ref},1,1)")
	void Insert(FashionBoardDTO fd);
	
	@Select("select max(ref) from FASHION_BOARD")
	int selectMaxRef();
	
	@Update("update from FASHION_BOARD set ref=#{ref}+1")
	void updateRef();
	
	//글전체보기
	@Select("select * from fashion_board order by ref desc, rs_level asc")
	List<FashionBoardDTO> selectAll();

	//글상세보기
	@Select("SELECT * FROM FASHION_BOARD WHERE board_num = #{board_num}")
	FashionBoardDTO selectOne(int boardNum);
	
	//조회수 
	@Update("update FASHION_BOARD set hits=hits+1 where board_num=#{board_num}")
//	@Update("select hits from FASHION_BOARD")
	void selectHits(int board_num);
	
	//글수정s
	@Update("UPDATE FASHION_BOARD SET USER_ID=#{user_id} , TITLE=#{title} , CONTENT=#{content} , HASH_TAG=#{hash_tag} , PICTURE_1=#{picture_1} , PICTURE_2=#{picture_2}, PICTURE_3=#{picture_3}"
			+ " WHERE BOARD_NUM = #{board_num}")
	void updateBoard(FashionBoardDTO fd);
	
	//글삭제
	@Delete("DELETE FROM FASHION_BOARD WHERE BOARD_NUM = #{board_num}")
	void delete(int boardNum);
	
	//level 변경 
	@Update("update fashion_board set rs_level=rs_level+1 where ref=#{ref} and rs_level>#{rs_level}")
//	int updateReLevel(@Param("fd")FashionBoardDTO fd);
	void updateReLevel(FashionBoardDTO fd);
	
	//해당 level, step 변경 
	@Update("update fashion_board set rs_level=rs_level+1, rs_step=rs_step+1 where board_num=#{fd.board_num}")
	void updateStepLevel(@Param("fd")FashionBoardDTO fd);
	
	//덧글 추가 
	@Insert("insert into FASHION_BOARD(USER_ID, TITLE, CONTENT, UPLOAD_DATE, HASH_TAG, PICTURE_1, PICTURE_2, PICTURE_3,ref,rs_step,rs_level)"
			+ " VALUES(#{user_id}, #{title}, #{content}, NOW(), #{hash_tag}, #{picture_1}, #{picture_2}, #{picture_3}, #{ref}, #{rs_step}, #{rs_level})")
	void reWriteBoard(FashionBoardDTO fd);
	
	@Select ("select * from reply_dto where board_num =#{board_num}")
	public List<ReplyDto> selectBoard(int board_num);
}
