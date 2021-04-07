package br.com.zupacademy.bruno.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestLogin;
import br.com.zupacademy.bruno.mercadolivre.controller.response.TokenResponse;
import br.com.zupacademy.bruno.mercadolivre.security.JWTService;

@RestController
@RequestMapping
public class LoginController {

	private JWTService jwtService;
	private AuthenticationManager authenticationManager;
	
	
	
	public LoginController(JWTService jwtService, AuthenticationManager authenticationManager) {
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}



	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid RequestLogin request){
		UsernamePasswordAuthenticationToken login = request.converter();
		try {
			Authentication authentication = authenticationManager.authenticate(login);
			String token = jwtService.gerarToken(authentication);
			return ResponseEntity.ok().body(new TokenResponse(token));
		} catch (Exception e) {
			System.out.println();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
