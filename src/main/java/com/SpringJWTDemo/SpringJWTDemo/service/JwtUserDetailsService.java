package com.SpringJWTDemo.SpringJWTDemo.service;

import java.util.ArrayList;
import java.util.Optional;

import com.SpringJWTDemo.SpringJWTDemo.model.MyUserDetails;
import com.SpringJWTDemo.SpringJWTDemo.model.UserDtl;
import com.SpringJWTDemo.SpringJWTDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserDtl> user=null;

		 user = userRepository.findByUserName(username);

		if (!user.isPresent()) {
			throw new UsernameNotFoundException("Could not find user");
		}

		if(username.equalsIgnoreCase("admin")){
			user.get().setPassword(encoder.encode(user.get().getPassword()));
		}

		return new MyUserDetails(user.get());
	}

	//Helper methods
	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			int n = Integer.parseInt(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}