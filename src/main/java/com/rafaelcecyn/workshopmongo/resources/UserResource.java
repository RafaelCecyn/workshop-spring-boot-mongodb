package com.rafaelcecyn.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafaelcecyn.workshopmongo.domain.Post;
import com.rafaelcecyn.workshopmongo.domain.User;
import com.rafaelcecyn.workshopmongo.dto.UserDTO;
import com.rafaelcecyn.workshopmongo.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;

	@Operation(description = "Busca todos os usuários")
	@ApiResponse(responseCode = "200", description = "Retorna todos os usuários")
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findall();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@Operation(description = "Busca o ususário por id")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna o usuário por id"),
			@ApiResponse(responseCode = "404", description = "Erro ao retornar o usuário")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@Operation(description = "Insere um novo usuário")
	@ApiResponse(responseCode = "200", description = "Registra o novo usuário")
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO) {
		User obj = service.fromDTO(objDTO); // Converte para User de UserDTO
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(description = "Delete um usuário por id")
	@ApiResponse(responseCode = "200", description = "apaga um usuário por id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(description = "Atualiza o ususário por id")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Atualiza o usuário por id"),
			@ApiResponse(responseCode = "404", description = "Erro ao atualizar o usuário")
	})
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO,@PathVariable String id) {
		User obj = service.fromDTO(objDTO); // Converte para User de UserDTO
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	

	@Operation(description = "Busca o post de um usuario por id")
	@ApiResponse(responseCode = "200", description = "Retorna o post de um usuario por id")
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}
	
	
	
}
