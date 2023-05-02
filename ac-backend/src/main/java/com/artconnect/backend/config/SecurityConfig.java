package com.artconnect.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.artconnect.backend.config.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

	final String PATH_AUTH = "/auth/**";

	private final JwtAuthenticationFilter jwtAuthFilter;
	private final ReactiveAuthenticationManager reactiveAuthenticationManager;

	@Bean
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {

		return http
				.cors().configurationSource(createCorsConfigSource()).and()
				.csrf().disable()
				.httpBasic().disable()
				.authenticationManager(reactiveAuthenticationManager)
				.authorizeExchange(it -> it
						.pathMatchers(HttpMethod.POST, PATH_AUTH).permitAll()
						.pathMatchers(HttpMethod.GET, "/auth/confirm-account/**").permitAll()
						.anyExchange().authenticated()
						.and()
				)
				.addFilterAt(jwtAuthFilter, SecurityWebFiltersOrder.AUTHENTICATION)
				.build();
	}

	private CorsConfigurationSource createCorsConfigSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("http://localhost:3000");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		source.registerCorsConfiguration("/**", config);
		return source;
	}

}