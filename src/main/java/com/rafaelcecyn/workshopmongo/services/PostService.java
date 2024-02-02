package com.rafaelcecyn.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelcecyn.workshopmongo.domain.Post;
import com.rafaelcecyn.workshopmongo.repository.PostRepository;
import com.rafaelcecyn.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); 
	}
	
	public List<Post> findByTitle(String text) {
		return repo.searchTitles(text);
//		return repo.findByTitleContainingIgnoreCase(text);
	}
	

	
}
