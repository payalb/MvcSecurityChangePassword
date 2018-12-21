/*package com.example.demo;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.dao.UserRepository;
import com.example.demo.dto.UserDetail;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserRepository rep;
	@Autowired PasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserDetail user = rep.findById(authentication.getName()).get();
		System.out.println(user);
		if (user == null) {
			throw new RuntimeException("Invalid credentials!");
		}
		
		if (!user.getPassword().equals(authentication.getCredentials())) {
			throw new RuntimeException("Invalid credentials!");
		}
		return new UsernamePasswordAuthenticationToken(user.getUsername(), authentication.getCredentials(),
				user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
*/