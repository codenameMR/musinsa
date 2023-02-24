package com.musinsa.fashionBoard.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.musinsa.fashionBoard.item.Item;

@Mapper
public interface ItemMapper {
   @Insert("INSERT INTO item "
         + "VALUES (#{id},#{count},NOW(),#{isSoldout},#{name},"
         + "#{price},#{stock},#{text},#{seller_id},#{img_name},#{img_path})")   
   public int upload(Item item);
   
   @Select("select * from Item")
   public List<Item> selectAllItem();


   
   
   
}