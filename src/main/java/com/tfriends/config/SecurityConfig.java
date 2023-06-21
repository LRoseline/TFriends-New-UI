package com.tfriends.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
public class SecurityConfig {

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers(
				"/css/*.css", "/js/*.js", "/favicon.ico", "/robots.txt");
	}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
		http
				.sessionManagement(s -> s
					.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
					.maximumSessions(1)
					.expiredUrl("/expired"))
				.authorizeHttpRequests((authz) -> authz
						.requestMatchers("/nsfw/**").hasRole("ADMIN")
						.requestMatchers("/*/*/write", "/*/*/edit").authenticated()
						.anyRequest().permitAll())
				.logout(l -> l
						.logoutUrl("/logout")
						.logoutSuccessUrl("/"))
				.exceptionHandling(handling -> handling
						.accessDeniedPage("/access-denied"))
				.csrf(cs -> cs
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.requestCache(c -> c
						.requestCache(requestCache));
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
