package br.com.cast.avaliacao.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class CursoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private String dataInicio;
	private String dataTermino;
	private Integer qtdAlunos;
	private Integer idCategoria;
	private String descricaoCategoria;
	
	public CursoDTO() {
		
	}

	public CursoDTO(Integer id, String descricao, String dataInicio, String dataTermino, Integer qtdAlunos,
			Integer idCategoria, String descricaoCategoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.qtdAlunos = qtdAlunos;
		this.idCategoria = idCategoria;
		this.descricaoCategoria = descricaoCategoria;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the dataInicio
	 */
	public String getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataTermino
	 */
	public String getDataTermino() {
		return dataTermino;
	}

	/**
	 * @param dataTermino the dataTermino to set
	 */
	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}

	/**
	 * @return the qdtAlunos
	 */
	public Integer getQtdAlunos() {
		return qtdAlunos;
	}

	/**
	 * @param qdtAlunos the qdtAlunos to set
	 */
	public void setQdtAlunos(Integer qtdAlunos) {
		this.qtdAlunos = qtdAlunos;
	}

	/**
	 * @return the idCategoria
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return the descricaoCategoria
	 */
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	/**
	 * @param descricaoCategoria the descricaoCategoria to set
	 */
	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}
	
}
