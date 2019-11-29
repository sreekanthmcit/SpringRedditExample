package com.sreekanth.springit.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sreekanth.springit.domain.User;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		if(SecurityContextHolder.getContext().getAuthentication() == null) {
			return Optional.of("master@gmail.com");
		}
		return Optional.of(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
	}

}
