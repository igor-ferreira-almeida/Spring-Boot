package br.com.alura.forum.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "RESPOSTA")
public class Resposta {

	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(name = "MENSAGEM")
	private String mensagem;
	
	@ManyToOne
	@JoinColumn(name = "ID_TOPICO")
	private Topico topico;
	
	@Column(name = "DATA_CRIACAO")
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario autor;
	
	@Column(name = "SOLUCAO")
	private Boolean solucao = false;
}
