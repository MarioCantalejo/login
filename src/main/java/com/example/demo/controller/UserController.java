package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.request.CreateUserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	
	private final UserService userSevice;
	@Autowired
	public UserController(UserService userService) {
		this.userSevice = userService;
	}
	
	
	@PostMapping("/login")
	public boolean login(@RequestBody UserEntity user) {
		return userSevice.login(user);
	}
	
	@PostMapping("/registro")
	public ResponseEntity<?> registro(@Validated @RequestBody CreateUserDto createUserDto){
		try {
			UserEntity userEntity = UserEntity.builder()
					.username(createUserDto.getUsername())
					.password(createUserDto.getPassword())
					.email(createUserDto.getEmail())
					.build();
			return ResponseEntity.ok(userSevice.saveUser(userEntity)) ;
		} catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario");		}
	}
}
