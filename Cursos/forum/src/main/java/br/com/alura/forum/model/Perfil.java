package br.com.alura.forum.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
@Table(name = "PERFIL")
public class Perfil implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(name = "DESCRICAO")
	private String descricao;

	@Override
	public String getAuthority() {
		
		return descricao;
	}
}
