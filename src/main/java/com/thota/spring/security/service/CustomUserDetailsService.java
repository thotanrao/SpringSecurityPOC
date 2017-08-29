package com.thota.spring.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thota.spring.security.model.CustomUserDetails;
import com.thota.spring.security.model.User;
import com.thota.spring.security.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> optionalUsers = userRepository.findByUserName(userName);
		
		optionalUsers.orElseThrow(()->new UsernameNotFoundException("User Name not Found"));
		return optionalUsers.map(CustomUserDetails::new).get();
	}

}
