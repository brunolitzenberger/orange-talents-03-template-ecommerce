package br.com.zupacademy.bruno.mercadolivre.security;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class ConfiguracoesDeSeguranca extends WebSecurityConfigurerAdapter {
	
	private AuthenticationService authenticationService;
	
	private JWTService jwtService;
	
	private EntityManager em;	
	
	public ConfiguracoesDeSeguranca(AuthenticationService authenticationService, JWTService jwtService,
			EntityManager em) {
		this.authenticationService = authenticationService;
		this.jwtService = jwtService;
		this.em = em;
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	private static final String[] PUBLIC_MATCHERS = {
		"/h2/**",
		"/login"
	};
	
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/produtos",
			"/categorias",
			"/compra-pagseguro/{compraId}",
			"/retorno-paypal/{compraId}",
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
		"/usuario",
		"/produtos",
		"/notas-fiscais",
		"/ranking-vendedores"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.headers().frameOptions().sameOrigin();
		http.authorizeRequests()
		.antMatchers(PUBLIC_MATCHERS).permitAll()
		.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
		.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new TokenAuthenticationFilter(jwtService, em), UsernamePasswordAuthenticationFilter.class);;
	}
	
	
	
}
