package com.musinsa.fashionBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.musinsa.fashionBoard.item.Item;

@Mapper
public interface ItemMapper {
	@Insert("INSERT INTO item "
			+ "VALUES(0, #{count}, #{createDate}, #{isSoldout}, #{name}, "
			+ "#{price}, #{stock}, #{text}, #{seller})")
	public int upload(Item item);
	
	@Select("select * from Item")
	public List<Item> selectAllItem();
	
	
}
