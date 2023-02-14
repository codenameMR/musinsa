package com.musinsa.fashionBoard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.musinsa.fashionBoard.entity.UserEntity;
import com.musinsa.fashionBoard.model.UserVO;
import com.musinsa.fashionBoard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	public void save(UserVO userVO) {
		UserEntity userEntity = UserEntity.toUserEntity(userVO);
		userRepository.save(userEntity);
		// repository의 save메서드 호출(조건. entity객체를 넘겨줘야 함.
		
	}
	
	public UserVO login(UserVO userVO) {
		Optional<UserEntity> byUserEmail = userRepository.findByUserEmail(userVO.getUserEmail());
		if (byUserEmail.isPresent()) {
			//조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
			UserEntity userEntity = byUserEmail.get();
			if (userEntity.getUserPassword().equals(userVO.getUserPassword())) {
				//비밀번호 일치
				//entity -> vo변환 후 리턴;
				UserVO vo = UserVO.toUserVO(userEntity);
				return vo;
			} else {
				//비밀번호 불일치(로그인실패)
				return null;
			}
		} else {
			//조회 결과가 없다(해당 이메일을 가진 회원이 없다)
			return null;
		}
	}

	public List<UserVO> findAll() {
		List<UserEntity> userEntityList = userRepository.findAll();
		List<UserVO> userVOList = new ArrayList<>();
		for (UserEntity userEntity: userEntityList) {
			userVOList.add(UserVO.toUserVO(userEntity));
//			UserVO userVO = UserVO.toUserVO(userEntity);
//			userVOList.add(userVO);
			
		}
		return userVOList;
	}

	public UserVO findById(Long id) {
		Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
		if (optionalUserEntity.isPresent()) {
//			UserEntity userEntity = optionalUserEntity.get();
//          UserVO userVO = UserVO.toUserVO(userEntity);
//          return userVO;
			return UserVO.toUserVO(optionalUserEntity.get());
		} else {
            return null;
		}
	}
	
	 public UserVO updateForm(String myEmail) {
	        Optional<UserEntity> optionalUserEntity = userRepository.findByUserEmail(myEmail);
	        if (optionalUserEntity.isPresent()) {
	            return UserVO.toUserVO(optionalUserEntity.get());
	        } else {
	            return null;
	        }
	    }

	    public void update(UserVO userVO) {
	        userRepository.save(UserEntity.toUpdateUserEntity(userVO));
	    }

	    public void deleteById(Long id) {
	        userRepository.deleteById(id);
	    }

	    public String emailCheck(String UserEmail) {
	        Optional<UserEntity> byUserEmail = userRepository.findByUserEmail(UserEmail);
	        if (byUserEmail.isPresent()) {
	            // 조회결과가 있다 -> 사용할 수 없다.
	            return null;
	        } else {
	            // 조회결과가 없다 -> 사용할 수 있다.
	            return "ok";
	        }
	    }

}
