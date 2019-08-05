package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;

@RestController
public class TopicoController {

	@RequestMapping("/topicos")
	public List<TopicoDTO> list() {
		Topico t1 = new Topico("Dúvida 1", "Dúvida com Spring Boot", new Curso("Spring Boot", "Programação"));
//		Topico t2 = new Topico("Dúvida 2", "Dúvida com Spring Boot", new Curso("Spring Boot", "Programação"));
//		Topico t3 = new Topico("Dúvida 3", "Dúvida com Spring Boot", new Curso("Spring Boot", "Programação"));
		
		return TopicoDTO.converter(Arrays.asList(t1, t1, t1));
	}
	
}
