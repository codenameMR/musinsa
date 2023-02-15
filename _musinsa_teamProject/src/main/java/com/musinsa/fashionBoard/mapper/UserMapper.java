package com.musinsa.fashionBoard.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;




@Mapper
public interface UserMapper {

	@Insert("INSERT INTO user "
			+ "VALUES(0, #{date}, #{email}, #{name}, #{password},"
			+ "#{role}, #{username}, #{address}, #{coin}, #{phone})")
	public int save(User user);
	
	@Select
	("select * from user where username = #{username} and password = #{password}")
	public User login(User user);

	
//	@Select("SELECT i, NAME, EMAIL, AUTH FROM MEMBERS
//      WHERE ID = #{id} AND PWD = #{pwd}
//  
}

