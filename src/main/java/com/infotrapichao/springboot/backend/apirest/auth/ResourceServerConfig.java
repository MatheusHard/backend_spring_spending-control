package com.infotrapichao.springboot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/setores", "/api/setores/page/**").permitAll()
		.antMatchers("/api/funcionarios/**").permitAll()
		.antMatchers("/api/funcionarios/find_by_login/**").permitAll()
		.antMatchers("/api/funcionarios/{id}").permitAll()
		.antMatchers("/api/viagens/**").permitAll()
		.antMatchers("/api/ufs/**").permitAll()
		.antMatchers("/api/cidades/**").permitAll()
		.antMatchers("/api/gastos/**").permitAll()
		.antMatchers("/api/especificacao_gastos/**").permitAll()
		.antMatchers("/api/sub_especificacao_gastos/**").permitAll()
		.antMatchers("/api/solicitacoes_viajem/**").permitAll()

		//.antMatchers(HttpMethod.POST, "/api/setores").hasAnyRole("ADMIN")
		//.antMatchers("/api/setores/**").hasAnyRole("ADMIN")

		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
	
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	    configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE","OPTION"));
	    configuration.setAllowCredentials(true);
	    configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
	    
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    
	    return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;	
	}
	

}












































