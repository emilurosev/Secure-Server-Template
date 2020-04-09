package rs.ac.singidunum.server.authentication;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class AuthenticationResponse {
	private final String jwt;
	private final String username;
	private final Collection<? extends GrantedAuthority> authorities;
	public String getJwt() {
		return jwt;
	}
	public String getUsername() {
		return username;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public AuthenticationResponse(String jwt, String username, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.jwt = jwt;
		this.username = username;
		this.authorities = authorities;
	}
}
