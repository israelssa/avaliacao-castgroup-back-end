package br.com.cast.avaliacao.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cast.avaliacao.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
	
	List<Curso> findByDescricaoContaining(String descricao);
	
	List<Curso> findAllByDataInicioLessThanEqualAndDataTerminoGreaterThanEqual(LocalDate dataInicio, LocalDate dataTermino);
	
	@Query(value = "Select c From Curso c Where (c.dataInicio between :dataInicio and :dataTermino) or (c.dataTermino between :dataInicio and :dataTermino)")
	List<Curso> verificarCursosNoPerido(LocalDate dataInicio, LocalDate dataTermino);
	
}
