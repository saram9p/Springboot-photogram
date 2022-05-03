package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공)

@Builder
@AllArgsConstructor // 전체 생성자
@NoArgsConstructor // 빈 생성자
@Data
@Entity // DB에 테이블을 생성
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
	private  int id; // 서비스하는 프로그램이 아니라서 int로 함
	
	@Column(length = 20, unique = true) // 제약조건
	private String username;
	private String password;
	
	private String name; // 이름
	private String website; // 웹 사이트
	private String bio; // 자기 소개
	private String email; // 이메일
	private String phone; // 전화번호
	private String gender; // 성별
	
	private String profileimageUrl; // 사진
	private String role; // 권한
	
	private LocalDateTime createDate;
	
	@PrePersist // DB에 INSERT 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
