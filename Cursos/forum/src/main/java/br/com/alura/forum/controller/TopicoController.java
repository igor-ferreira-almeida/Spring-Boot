package br.com.alura.forum.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.form.TopicoAtualizacaoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.dto.TopicoDetalheDTO;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public List<TopicoDTO> list(String nomeCurso) {
		List<Topico> topicos = new ArrayList<>();
		if(nomeCurso == null) {
			topicos = topicoRepository.findAll();
		} else {
			topicos = topicoRepository.findByCursoNome(nomeCurso);
		}
		return TopicoDTO.converter(topicos);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicoDetalheDTO> detalhar(@PathVariable Long id) {
		Optional<Topico> optionalTopico = topicoRepository.findById(id);
		
		if (optionalTopico.isPresent()) {
			TopicoDetalheDTO topicoDetalheDTO = new TopicoDetalheDTO(optionalTopico.get());
			return ResponseEntity.ok(topicoDetalheDTO);
		} 
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDTO> atualizar(@RequestBody @Valid TopicoAtualizacaoForm form, @PathVariable Long id) {
		Optional<Topico> optionalTopico = topicoRepository.findById(id);
		
		if (optionalTopico.isPresent()) {
			Topico topico = form.atualizar(id, optionalTopico.get());
			topicoRepository.save(topico);
			
			return ResponseEntity.ok(new TopicoDTO(topico));
		} 
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Topico> optionalTopico = topicoRepository.findById(id);
		
		if (optionalTopico.isPresent()) {
			topicoRepository.deleteById(id);
			
			return ResponseEntity.ok().build();
		} 
		
		return ResponseEntity.notFound().build();
	}
	
}
