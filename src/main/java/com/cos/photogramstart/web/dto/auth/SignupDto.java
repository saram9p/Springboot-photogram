package com.cos.photogramstart.web.dto.auth;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data // Getter,Setter
public class SignupDto {
	private String username;
	private String password;
	private String email;
	private String name;
	
	public User toEntity() { // 위의 네개의 데이터를 기반으로 User 객체를 만들어서 리턴한다.
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
