package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.TemaModel;

import org.generation.blogPessoal.repository.TemaRepository;
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

@RestController
@RequestMapping("/tema")
@CrossOrigin("*")
public class TemaController {

	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<TemaModel>> buscarTodos() {
		List<TemaModel> objetoTema = repository.findAll();
		if (objetoTema.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoTema);
		}
	}
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<TemaModel>buscaId(@PathVariable long id) {
		//return ResponseEntity.ok(repository.findById(id));
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	@RequestMapping("/descricao/{descricao}")
	public ResponseEntity<List<TemaModel>> buscaDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping 
	public ResponseEntity<TemaModel> salvar (@RequestBody TemaModel tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));		
	}
	@PutMapping 
	public ResponseEntity<TemaModel> atualizar (@RequestBody TemaModel tema){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));		
	}
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	


}
