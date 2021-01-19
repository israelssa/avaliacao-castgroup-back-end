package br.com.cast.avaliacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.avaliacao.dto.CursoDTO;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.service.CursoService;
import br.com.cast.avaliacao.service.exceptions.AvaliacaoException;

@RestController
@RequestMapping(value = "/curso")
public class CursoController {

	@Autowired
	private CursoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CursoDTO> getById(@PathVariable Integer id) {
		Curso obj = service.find(id);
		return ResponseEntity.ok().body(service.conversor(obj));
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ResponseEntity<List<CursoDTO>> listar() {
		List<Curso> obj = service.findAll();
		return ResponseEntity.ok().body(service.conversor(obj));
	}

	@PutMapping
	protected ResponseEntity<Curso> update(@RequestBody CursoDTO dto) {
		try {
			return Optional.ofNullable(service.update(dto)).map(obj -> new ResponseEntity<>(obj, HttpStatus.OK))
					.orElseThrow(() -> new AvaliacaoException("Erro ao atualizar Registro"));
		} catch (AvaliacaoException ex) {
			throw new AvaliacaoException(ex.getMessage());
		}
	}

	@PostMapping
	protected ResponseEntity<Curso> save(@RequestBody CursoDTO dto) {
		try {
			return Optional.ofNullable(service.update(dto)).map(obj -> new ResponseEntity<>(obj, HttpStatus.OK))
					.orElseThrow(() -> new AvaliacaoException("Erro ao cadastrar Registro"));
		} catch (AvaliacaoException ex) {
			throw new AvaliacaoException(ex.getMessage());
		}
	}
	
	@PostMapping(value = "/validar")
	protected ResponseEntity<?> validar(@RequestBody CursoDTO dto) {
		try {
			Curso curso = this.service.conversor(dto);
			
			if(this.service.dataInicialInvalida(curso)) {
				throw new AvaliacaoException("Não é permitida a inclusão de cursos com a data de inicio menor que a data atual.");
			}
			
			if(this.service.existemCursosNoPerido(curso)) {
				throw new AvaliacaoException("Existe(m) curso(s) planejados(s) dentro do período informado.");
			}
			
			return ResponseEntity.ok().build();
		} catch (AvaliacaoException ex) {
			throw new AvaliacaoException(ex.getMessage());
		}
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			this.service.delete(id);
			return ResponseEntity.ok().build();
		} catch (AvaliacaoException ex) {
			throw new AvaliacaoException(ex.getMessage());
		}

	}

}