package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.encoder.Encoder;

import org.apache.commons.codec.binary.Base64;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	private static String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);		
	}
	
	public Optional<Object> cadastrarUsuario(Usuario usuario) {
		return repository.findByUsuario(usuario.getUsuario()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			usuario.setSenha(criptografarSenha(usuario.getSenha()));
			return Optional.ofNullable(repository.save(usuario));
		});
	}
	
	public Optional<UserLogin> logar (Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodeAuth);
				
				user.get().setIdUsuario(usuario.get().getId());
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				user.get().setSenha(usuario.get().getSenha());
				user.get().setFoto(usuario.get().getFoto());
				user.get().setTipo(usuario.get().getTipo());
				
				return user;
			}
		}
		return null;
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		return repository.findById(usuario.getId()).map(resp ->{
			resp.setNome(usuario.getNome());
			resp.setSenha(criptografarSenha(usuario.getSenha()));
			resp.setFoto(usuario.getFoto());
			resp.setTipo(usuario.getTipo());
			resp.setUsuario(usuario.getUsuario());
			return Optional.ofNullable(repository.save(resp));
		}).orElseGet(() ->{
			return Optional.empty();
		});
	}

}
