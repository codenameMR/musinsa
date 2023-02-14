package com.musinsa.fashionBoard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musinsa.fashionBoard.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	//이메일로 회원 정보 조회 (select * from user_table where user.email=?>
	Optional<UserEntity> findByUserEmail(String userEmail);
}
