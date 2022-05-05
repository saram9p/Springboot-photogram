package com.cos.photogramstart.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private User user;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	// 권한 : 한개가 아닐 수 있음. (3개 이상의 권한)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collector = new ArrayList<>();
		collector.add(() -> {return user.getRole();});
		
		return collector;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { // 네 계정이 만료가 되었니?
		return true; // user.getExpired();
	}

	@Override
	public boolean isAccountNonLocked() { // 네 계정이 잠겼니?
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 네 계정의 비밀번호를 바꾸지 않았니?
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
