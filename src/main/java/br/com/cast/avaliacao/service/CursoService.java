package br.com.cast.avaliacao.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.avaliacao.domain.enums.Perfil;
import br.com.cast.avaliacao.dto.CursoDTO;
import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.repository.CursoRepository;
import br.com.cast.avaliacao.security.jwt.UserSS;
import br.com.cast.avaliacao.service.exceptions.AuthorizationException;
import br.com.cast.avaliacao.service.exceptions.AvaliacaoException;
import br.com.cast.avaliacao.service.exceptions.ObjectNotFoundException;
import io.jsonwebtoken.lang.Objects;

@Service
public class CursoService {

	@Autowired
	private CursoRepository repo;

	public Curso find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Curso> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Curso.class.getName()));
	}

	public List<Curso> findByDescricao(String descricao) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN)) {
			throw new AuthorizationException("Acesso negado");
		}

		List<Curso> obj = repo.findByDescricaoContaining(descricao);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Curso.class.getName());
		}
		return obj;
	}

	public List<Curso> findAll() {
		return repo.findAll();
	}

	public Curso save(CursoDTO cursodto) {
		return repo.save(this.conversor(cursodto));
	}

	public Curso update(CursoDTO cursodto) {
		return repo.save(this.conversor(cursodto));
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}

	public Curso conversor(CursoDTO cursodto) {

		Curso curso = new Curso();
		curso.setId(cursodto.getId());
		curso.setCategoria(new Categoria(cursodto.getIdCategoria()));
		curso.setDescricao(cursodto.getDescricao());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		curso.setDataInicio(LocalDate.parse(cursodto.getDataInicio().substring(0, 10), formatter));
		curso.setDataTermino(LocalDate.parse(cursodto.getDataTermino().substring(0, 10), formatter));
		curso.setQtdAlunos(cursodto.getQtdAlunos());

		return curso;
	}
	
	public CursoDTO conversor(Curso curso) {

		CursoDTO cursodto = new CursoDTO();
		cursodto.setId(curso.getId());
		cursodto.setIdCategoria(curso.getCategoria().getId());
		cursodto.setDescricaoCategoria(curso.getCategoria().getDescricao());
		cursodto.setDescricao(curso.getDescricao());
		cursodto.setDataInicio(curso.getDataInicio().toString());
		cursodto.setDataTermino(curso.getDataTermino().toString());
		cursodto.setQdtAlunos(curso.getQtdAlunos());

		return cursodto;
	}
	
	public List<CursoDTO> conversor (List<Curso> cursos){
		List<CursoDTO> cursosdto = new ArrayList<>();
		
		cursos.forEach(curso -> {
			cursosdto.add(this.conversor(curso));
		});
		
		return cursosdto;
	}

	public boolean dataInicialInvalida(Curso curso) {
		if(LocalDate.now().isAfter(curso.getDataInicio())) {
			return true;
		}
		return false;
	}

	public boolean existemCursosNoPerido(Curso curso) {
		
		return !repo.verificarCursosNoPerido(
				curso.getDataInicio(), curso.getDataTermino()).isEmpty();
	}

}
