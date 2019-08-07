package br.com.alura.forum.dto;

import java.time.LocalDateTime;

import br.com.alura.forum.model.Resposta;
import lombok.Getter;

@Getter
public class RespostaDTO {
	private Long id;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeAutor;
	
	public RespostaDTO(Resposta resposta) {
		id = resposta.getId();
		mensagem = resposta.getMensagem();
		dataCriacao = resposta.getDataCriacao();
		nomeAutor = resposta.getAutor().getNome();
	}
}
