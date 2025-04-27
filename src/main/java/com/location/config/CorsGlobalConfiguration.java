package com.location.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsUtils; // 🔁 Checks if the request is a CORS request
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.WebFilter;      // 🔁 Functional reactive web filter
import org.springframework.web.server.ServerWebExchange; // Used internally in filters

import org.springframework.http.HttpHeaders;          // ☁️ HTTP header utilities
import org.springframework.http.server.reactive.ServerHttpRequest;  // 🔁 HTTP request in reactive
import org.springframework.http.server.reactive.ServerHttpResponse; // 🔁 HTTP response in reactive

@Configuration
public class CorsGlobalConfiguration {
	@Bean
    public WebFilter corsFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                ServerHttpResponse response = exchange.getResponse();
                HttpHeaders headers = response.getHeaders();
                headers.add("Access-Control-Allow-Origin", "http://localhost:5173"); // Your frontend domain
                headers.add("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
                headers.add("Access-Control-Allow-Headers", "Authorization, Content-Type");
                headers.add("Access-Control-Allow-Credentials", "true");
            }
            return chain.filter(exchange);
        };
    }
	
	@Bean
	public CorsWebFilter corsWebFilter() {
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOriginPattern("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);
	    return new CorsWebFilter(source);
	}
}
