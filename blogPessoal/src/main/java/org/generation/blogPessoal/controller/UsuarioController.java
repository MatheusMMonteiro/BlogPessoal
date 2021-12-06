package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping("/todes")
	public ResponseEntity<List<Usuario>> pegarTodes(){
		List<Usuario> objetoLista = repository.findAll();
		
		if(objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	@GetMapping("/nome/{nome_usuario}")
	public ResponseEntity<List<Usuario>> buscaNome(@PathVariable(value = "nome_usuario") String nome){
		List<Usuario> objetoLista = repository.findAllByNomeContainingIgnoreCase(nome);
		
		if(objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	@GetMapping("/{id_usuario}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable(value = "id_usuario") Long idUsuario){
		return repository.findById(idUsuario).map(resp -> ResponseEntity.status(200).body(resp))
				.orElseThrow(() ->{
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Id inexistente, passe um Id válido para pesquisa");
				});
	}	
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> autentication(@RequestBody Optional<UserLogin> user){
		return usuarioService.logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario){
		return usuarioService.cadastrarUsuario(usuario)
				.map(usuarioExistente -> ResponseEntity.status(201).body(usuario))
				.orElseThrow(() ->{
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"Usuario existente, cadastre outro usuário!");
				});
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario usuario){
		return usuarioService.atualizarUsuario(usuario).map(resp -> ResponseEntity.status(201).body(resp))
				.orElseThrow(() ->{
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"Necessário que passe um idUsuario válido");
				});
	}
	
	@DeleteMapping("/deletar/{id_usuario}")
	public ResponseEntity<Object> deletar(@PathVariable(value = "id_usuario") Long idUsuario){
		return repository.findById(idUsuario).map(resp ->{
			repository.deleteById(idUsuario);
			return ResponseEntity.status(200).build();
		}).orElseThrow(() ->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"ID inexistente, passe um ID valido para deletar!.");
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
