package com.rafaelcecyn.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelcecyn.workshopmongo.domain.Post;
import com.rafaelcecyn.workshopmongo.resources.util.URL;
import com.rafaelcecyn.workshopmongo.services.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@Operation(description = "Busca o post por id")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna o post por id"),
			@ApiResponse(responseCode = "404", description = "Erro ao retornar o post")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@Operation(description = "Busca um caracter contido no titulo do post")
	@ApiResponse(responseCode = "200", description = "Retorna um caracter contido no titulo do post")
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		// tipo de requisição
		// http://localhost:8080/posts/titlesearch?text=bom%20dia
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@Operation(description = "Busca um caracter contido no titulo, corpo ou comentário,"
			+ " data mínima e data máxima do post")
	@ApiResponse(responseCode = "200", description = "Retorna um caracter contido no titulo, corpo ou comentário, data mínima e data máxima do post")
	@GetMapping(value="/fullsearch")
 	public ResponseEntity<List<Post>> fullSearch(
 			@RequestParam(value="text", defaultValue="") String text,
 			@RequestParam(value="minDate", defaultValue="") String minDate,
 			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		// tipo de requisição
		// http://localhost:8080/posts/fullsearch?text=bom&minDate=2018-03-20&maxDate=2018-03-30
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
	
}
