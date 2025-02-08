package br.edu.ifba.demo.backend.api.model;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id_usuario;

	@Column(name = "login", nullable = false, unique = true)
	private String login;

	@Column(name = "senha", nullable = false)
	private String senha;

	@Column(name = "create_at")
	private Timestamp create_at;

	@Column(name = "last_login", nullable = true)
	private Timestamp last_login;

	public UsuarioModel() {
		super();
	}

	public UsuarioModel(Long id_usuario, String login, String senha) {
		this.id_usuario = id_usuario;
		this.login = login;
		this.senha = senha;
	}

	public UsuarioModel(Long id_usuario, String login, String senha, Timestamp create_at, Timestamp last_login) {
		this.id_usuario = id_usuario;
		this.login = login;
		this.senha = senha;
		this.create_at = create_at;
		this.last_login = last_login;
	}
}