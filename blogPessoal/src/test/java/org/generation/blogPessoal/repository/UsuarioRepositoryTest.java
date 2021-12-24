package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {

		Usuario usuario = new Usuario(0L, "Matheus Monteiro", "matheus@gmail.com", "12345678");

		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			usuarioRepository.save(usuario);
		}

		usuario = new Usuario(0L, "Milena Monteiro", "milena@gmail.com", "12345678");

		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			usuarioRepository.save(usuario);
		}

		usuario = new Usuario(0L, "Larissa Monteiro", "Larissa@gmail.com", "12345678");

		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			usuarioRepository.save(usuario);
		}

		usuario = new Usuario(0L, "Renata", "renata@gmail.com", "12345678");

		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			usuarioRepository.save(usuario);
		}

	}

	@Test
	@DisplayName("👀 Retorna 3 Usuarios")
	public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {
		List<Usuario> ListaDeUsuario = usuarioRepository.findAllByNomeContainingIgnoreCase("renata");
		assertEquals(1, ListaDeUsuario.size());
	}
	
	@AfterAll
	public void end() {
		System.out.println("Teste foi finalizado");
	}
}
