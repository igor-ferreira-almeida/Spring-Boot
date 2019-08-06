package br.com.alura.forum.config.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FormErrorDTO {
	private String campo;
	private String mensagem;
}
