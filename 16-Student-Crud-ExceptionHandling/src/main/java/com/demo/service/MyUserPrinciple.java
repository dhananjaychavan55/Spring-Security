package com.demo.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.model.Student;

public class MyUserPrinciple implements UserDetails {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Student stu;
	
	public  MyUserPrinciple(Student stu) {
			this.stu=stu;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
				return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority("User"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return stu.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return stu.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
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
