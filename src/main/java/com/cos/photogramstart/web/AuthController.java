package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 필드를 DI 할때 사용, final이 걸려있는 모든 것들에 대한 생성자를 만들어준다.
@Controller // 1. IoC 2. 파일을 리턴하는 컨트롤러
public class AuthController {

	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	private final AuthService authService; // final이 걸려있으면 무조건 생성자가 실행될때 혹은 객체가 만들어질 때 초기화를 해줘야한다.
	
//	public AuthController(AuthService authService) {
//		this.authService = authService;
//	}
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	// 회원가입을 진행함
	// 회원가입버튼 -> /auth/signup -> /auth/signin
	@PostMapping("/auth/signup")
	public String signup(SignupDto signupDto) { // key=value (x-www-form-urlencoded)
		log.info(signupDto.toString());
		// User <- SignupDto
		User user = signupDto.toEntity();
		log.info(user.toString());
		User userEntity = authService.회원가입(user);
		System.out.println(userEntity);
		return "auth/signin";
	}
}
