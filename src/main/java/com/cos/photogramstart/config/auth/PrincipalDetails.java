package com.cos.photogramstart.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {
	
	private static final long serialVersionUID = 1L;

	private User user;
	private Map<String, Object> attributes;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	public PrincipalDetails(User user, Map<String, Object> attributes) {
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

	@Override
	public Map<String, Object> getAttributes() {
		return attributes; // {id:32321323132, name:~~~, email:ssar@nate.com}
	}

	@Override
	public String getName() {
		return (String) attributes.get("name");
	}

}
