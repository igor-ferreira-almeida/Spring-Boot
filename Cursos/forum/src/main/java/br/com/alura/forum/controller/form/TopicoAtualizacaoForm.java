package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.model.Topico;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicoAtualizacaoForm {
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String titulo;
	
	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String mensagem;

	public Topico atualizar(Long id, Topico topico) {
		topico.setTitulo(titulo);
		topico.setMensagem(mensagem);
		
		return topico;
	}
}
