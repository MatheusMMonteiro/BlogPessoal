package org.generation.blogPessoal.model;

public class UserLogin {
	
	private Long idUsuario;
	
	private String nome;
	
	private String usuario;
	
	private String Senha;
	
	private String token;	

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}	

}
