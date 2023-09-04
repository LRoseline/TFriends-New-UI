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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web
				.ignoring()
				.requestMatchers(new AntPathRequestMatcher("/css/*.css"))
				.requestMatchers(new AntPathRequestMatcher("/js/*.js"))
				.requestMatchers(new AntPathRequestMatcher("/fonts/**"))
				.requestMatchers(new AntPathRequestMatcher("/cdn/**"))
				.requestMatchers(new AntPathRequestMatcher("/favicon.ico"))
				.requestMatchers(new AntPathRequestMatcher("/robots.txt"));
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
						.requestMatchers(new AntPathRequestMatcher("/nsfw/**")).hasRole("ADMIN")
						.requestMatchers(new AntPathRequestMatcher("/*/*/write")).authenticated()
						.requestMatchers(new AntPathRequestMatcher("/*/*/edit")).authenticated()
						.anyRequest().permitAll())
				.logout(l -> l
						.logoutUrl("/logout")
						.logoutSuccessUrl("/"))
				.exceptionHandling(handling -> handling
						.accessDeniedPage("/access-denied"))
				.csrf(cs -> cs
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				// .disable())
				.requestCache(c -> c
						.requestCache(requestCache));
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
