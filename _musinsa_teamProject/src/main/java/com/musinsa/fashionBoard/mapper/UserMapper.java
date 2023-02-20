package com.musinsa.fashionBoard.mapper;

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
	
	@Select
	("select * from user where username = #{id}")
	public User selectId(String id);

	@Select
	("select max(id) from user")
	public int getMaxUserId();
	
	@Insert("INSERT INTO sale "
			+ "VALUES(0, 0, #{seller_id})")
	public int saveSale(int seller_id);

}

