package com.rafaelcecyn.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelcecyn.workshopmongo.domain.User;
import com.rafaelcecyn.workshopmongo.dto.UserDTO;
import com.rafaelcecyn.workshopmongo.repository.UserRepository;
import com.rafaelcecyn.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	
	public List<User> findall() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); 
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public User fromDTO(UserDTO obj) {
		return new User(obj.getId(),obj.getName(),obj.getEmail());
	}
	
}
