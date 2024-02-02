package com.rafaelcecyn.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rafaelcecyn.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' }}")
	// ?0 pega o valor do primeiro parâmetro abaixo que é text, i -> ignore case
	List<Post> searchTitles(String text); // Nome que quiser
	
	List<Post> findByTitleContainingIgnoreCase(String text); // Precisa ser o mesmo nome do repositório

}
