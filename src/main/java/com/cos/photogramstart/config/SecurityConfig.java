package com.cos.photogramstart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // 해당 파일로 시큐리티를 활성화
@Configuration // IoC 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨.
		http.authorizeRequests()
			.antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**").authenticated() // 해당 주소로 시작하게 되면 인증이 필요하다.
			.anyRequest().permitAll() // 그게 아닌 모든 요청은 허용
			.and()
			.formLogin() // form 태그가 있고 input 태그가 있는 form 로그인
			.loginPage("/auth/signin")
			.defaultSuccessUrl("/"); // 로그인이 정상적으로 처리가 되면 / 로 가게 한다.
	}
}
