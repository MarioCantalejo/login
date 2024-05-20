package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
    public boolean login(UserEntity usuarioLogin) {
        String username = usuarioLogin.getUsername();
        String pass = usuarioLogin.getPassword();

        Optional<UserEntity> usuarioOptional = userRepository.findByUsername(username);
        if (usuarioOptional.isEmpty()) {
            return false;
        }

        UserEntity usuario = usuarioOptional.get();
        if (!usuario.getPassword().equals(pass)) {
            return false;
        }

        return true;
    }
    
    public UserEntity saveUser(UserEntity user) {
    	try {
			return userRepository.save(user);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear el usuario");		
			}
    }
}
