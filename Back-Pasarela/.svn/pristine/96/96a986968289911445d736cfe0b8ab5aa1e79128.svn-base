package com.spring.visa;


import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.spring.visa.seguridad.JwtAuthenticationEntryPoint;
import com.spring.visa.seguridad.JwtAuthenticationProvider;
import com.spring.visa.seguridad.JwtAuthenticationTokenFilter;
import com.spring.visa.seguridad.JwtSuccessHandler;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationProvider authenticationProvider;
    private final JwtAuthenticationEntryPoint entryPoint;

    public WebConfig(JwtAuthenticationProvider authenticationProvider, JwtAuthenticationEntryPoint entryPoint) {
        this.authenticationProvider = authenticationProvider;
        this.entryPoint = entryPoint;
    }

    @Bean
    @Override
	public AuthenticationManager authenticationManager() {
		List<AuthenticationProvider> authProviderList = new ArrayList<>();
		authProviderList.add(authenticationProvider);
		return new ProviderManager(authProviderList);
	}
	
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilter() {
		JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
		
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
		
		return filter;
	}
	
    /**
     * Se deja preparado la configuracion de spring-security para la futura implementacion
     * en la cual se puede utilizar jwt o oauth2
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable()
        	.authorizeRequests().antMatchers("**/rest/**").authenticated()
        	.and()
        	.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "**/rest/**").permitAll()
        	.and()
        	.exceptionHandling().authenticationEntryPoint(entryPoint)
        	.and()
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
        http.addFilter(corsFilter());
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    
}