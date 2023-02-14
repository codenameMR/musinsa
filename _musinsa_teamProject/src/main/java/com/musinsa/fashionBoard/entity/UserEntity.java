package com.musinsa.fashionBoard.entity;


import com.musinsa.fashionBoard.model.UserVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "user_table")
public class UserEntity {
	@Id //pk 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
	private long id;
	
	@Column(unique = true) // unique 제약조건 추가
	private String userEmail;
	
	@Column
	private String userPassword;
	
	@Column
	private String userName;
	
	public static UserEntity toUserEntity(UserVO userVO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserEmail(userVO.getUserEmail());
		userEntity.setUserPassword(userVO.getUserPassword());
		userEntity.setUserName(userVO.getUserName());
		return userEntity;
	}
	
	public static UserEntity toUpdateUserEntity(UserVO userVO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userVO.getId());
        userEntity.setUserEmail(userVO.getUserEmail());
        userEntity.setUserPassword(userVO.getUserPassword());
        userEntity.setUserName(userVO.getUserName());
        return userEntity;
    }
}
