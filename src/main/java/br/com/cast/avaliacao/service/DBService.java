package br.com.cast.avaliacao.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.cast.avaliacao.domain.enums.Perfil;
import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.model.Usuario;
import br.com.cast.avaliacao.repository.CategoriaRepository;
import br.com.cast.avaliacao.repository.CursoRepository;
import br.com.cast.avaliacao.repository.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private CursoRepository cursoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		// Usuarios
		Usuario cl1 = new Usuario(null, "teste@email.com", "Administrador", pe.encode("1234"), "11111111111");
		cl1.addPerfil(Perfil.ADMIN);
		usuarioRepository.save(cl1);
		
		// Categorias
		Categoria est1 = new Categoria(null, 1, "Comportamental");
		Categoria est2 = new Categoria(null, 2, "Programação");
		Categoria est3 = new Categoria(null, 3, "Qualidade");
		Categoria est4 = new Categoria(null, 4, "Processos");
		
		categoriaRepository.saveAll(Arrays.asList(est1, est2, est3, est4));
		
		
		Curso cr1 = new Curso(null,"Descricao Teste", LocalDate.now(), LocalDate.now(), 100, new Categoria(1));
		
		cursoRepository.save(cr1);
		
		
	
	}
}
