package br.com.alura.forum.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.model.Topico;
import lombok.Getter;

public class TopicoDTO {
	
	@Getter
	private Long id;
	
	@Getter
	private String titulo;
	
	@Getter
	private String mensagem;
	
	@Getter
	private LocalDateTime dataCriacao;
	
	public TopicoDTO(Topico topico) {
		id = topico.getId();
		titulo = topico.getTitulo();
		mensagem = topico.getMensagem();
		dataCriacao = topico.getDataCriacao();
	}
	
	public static List<TopicoDTO> converter(List<Topico> topicos) {
		return topicos.stream().map(TopicoDTO::new).collect(Collectors.toList());
	}
	
	
}
