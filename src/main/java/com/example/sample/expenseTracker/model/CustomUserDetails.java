package com.example.sample.expenseTracker.model;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.sample.expenseTracker.utilities.AuthoritiesConstants;

@Component
public class CustomUserDetails implements org.springframework.security.core.userdetails.UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	User user=null;
	
	public CustomUserDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList((new SimpleGrantedAuthority(AuthoritiesConstants.ROLE_USER.name())),
				(new SimpleGrantedAuthority(AuthoritiesConstants.ROLE_ADMIN.name())),
				(new SimpleGrantedAuthority(AuthoritiesConstants.ANONYMOUS.name())));
	}

	@Override
	public String getPassword() {
		return getUser().getPasswordHash();
	}

	@Override
	public String getUsername() {
		return getUser().getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
