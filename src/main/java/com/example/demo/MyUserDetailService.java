package com.example.demo;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.dto.UserDetail;
@Service
public class MyUserDetailService implements UserDetailsService, UserDetailsPasswordService {

	@Autowired
	UserRepository rep;
	@Autowired PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetail user = rep.findById(username).get();
		System.out.println(user);
		if (user == null) {
			throw new RuntimeException("Invalid credentials! Username not correct.");
		}
		return new User(user.getUsername(), user.getPassword(),
				user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
	}

	@Override
	public UserDetails updatePassword(UserDetails user, String newPassword) {
		UserDetail user1 = rep.findById(user.getUsername()).get();
		System.out.println(user.getPassword());
		user1.setPassword(encoder.encode(newPassword));
		System.out.println(encoder.encode(newPassword));
		 rep.save(user1);
		 return this.loadUserByUsername(user.getUsername());
		
	}

}
