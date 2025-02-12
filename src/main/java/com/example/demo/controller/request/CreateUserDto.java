package com.example.demo.controller.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
}
