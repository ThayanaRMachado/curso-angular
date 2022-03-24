package com.crudusuario.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.crudusuario.entities.Usuario;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Required field")
	private String nome;
	
	@NotBlank(message = "Required field")
	private String usuario;
	
	@NotBlank(message = "Required field")
	private String senha;

	public UsuarioDTO() {

	}

	public UsuarioDTO(Long id, String nome, String usuario, String senha) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}

	public UsuarioDTO(Usuario entidade) {
		this.id = entidade.getId();
		this.nome = entidade.getNome();
		this.usuario = entidade.getUsuario();
		this.senha = entidade.getSenha();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
