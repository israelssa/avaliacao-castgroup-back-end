package br.com.cast.avaliacao.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.cast.avaliacao.security.jwt.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
