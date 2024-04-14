package com.ccifuentes.equifax.security.models.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ccifuentes.equifax.security.models.enums.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public MainUser(Long id, String name, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static MainUser build(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(Role.ROLE_USER.toString()));

		return new MainUser(user.getIdUser(), user.getName(), user.getMail(), user.getPassword(), authorities);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
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

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

}
