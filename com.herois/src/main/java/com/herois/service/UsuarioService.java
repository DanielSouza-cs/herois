package com.herois.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService{
	
	private PasswordEncoder encoder; 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(!username.equals("daniel")){
			throw new UsernameNotFoundException("USÁRIO NÃO ENCONTRADO NA BASE"); 
		}
		return User
				.builder()
					.username("daniel")
					.password(encoder.encode("123"))
					.roles("USER", "ADMIN")
				.build(); 
	}
}
