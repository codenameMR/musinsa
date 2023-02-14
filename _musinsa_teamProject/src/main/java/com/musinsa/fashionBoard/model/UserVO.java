package com.musinsa.fashionBoard.model;

import com.musinsa.fashionBoard.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성
@AllArgsConstructor // 필드를 모두 다 매개변수로 하는 생성자를 만들어준다.
@ToString
public class UserVO {
	private long id;
	private String userEmail;
	private String userPassword;
	private String userName;
	
	public static UserVO toUserVO(UserEntity userEntity) {
		UserVO userVO = new UserVO();
		userVO.setId(userEntity.getId());
		userVO.setUserEmail(userEntity.getUserName());
		userVO.setUserPassword(userEntity.getUserPassword());
		userVO.setUserName(userEntity.getUserName());
		return userVO;
	}
}
