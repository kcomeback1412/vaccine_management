package com.spring.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.consts.RoleEnum;
import com.spring.entities.Users;

public class CustomUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private Users users;
	
	public CustomUserDetails() {
		
	}
	
	public CustomUserDetails(Users users) {
		this.users = users;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		RoleEnum userRoles = users.getRoleEnum();
		
		authorities.add(new SimpleGrantedAuthority(userRoles.toString()));
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		return users.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
