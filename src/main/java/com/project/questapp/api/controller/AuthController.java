package com.project.questapp.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.business.UserManager;
import com.project.questapp.entities.User;
import com.project.questapp.requests.UserRequest;
import com.project.questapp.responses.AuthResponse;
import com.project.questapp.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	private UserManager userManager;
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public AuthResponse login(@RequestBody UserRequest loginRequest)
	{
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword());
	    Authentication auth = authenticationManager.authenticate(authToken);
	    
	    SecurityContextHolder.getContext().setAuthentication(auth);
	    String jwtToken = jwtTokenProvider.generateJwtToken(auth);
	
	    User user = userManager.getUserByUserName(loginRequest.getUserName());
	    
	    AuthResponse authResponse = new AuthResponse();
	    authResponse.setMessage(jwtToken);
	    authResponse.setUserId(user.getId());
	    
	    return authResponse;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserRequest registerRequest)
	{
	     if(userManager.getUserByUserName(registerRequest.getUserName()) != null)
	     {
	    	 return new ResponseEntity<>("username already in user", HttpStatus.BAD_REQUEST);
	     }
	     
	     User user = new User();
	     user.setUserName(registerRequest.getUserName());
	     user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
	     userManager.addUser(user);
	     
	     return new ResponseEntity<>("User successfully registered",HttpStatus.OK);
	}
	
}
