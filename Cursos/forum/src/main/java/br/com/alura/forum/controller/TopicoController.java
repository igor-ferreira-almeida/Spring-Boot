package br.com.alura.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Page<TopicoDTO> listar(@RequestParam(required = false) String nomeCurso, @RequestParam int pagina, @RequestParam int quantidade,  @RequestParam String ordenacao) {
		
		Pageable paginacao = PageRequest.of(pagina, quantidade, Direction.DESC, ordenacao);
		
		if(nomeCurso == null) {
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return TopicoDTO.converter(topicos);
		} else {
			Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
			return TopicoDTO.converter(topicos);
		}
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
