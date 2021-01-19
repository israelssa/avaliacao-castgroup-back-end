package br.com.cast.avaliacao.service.exceptions;

public class AvaliacaoException extends RuntimeException {

	private static final long serialVersionUID = 3099918174143775903L;

	public AvaliacaoException(String exception) {
        super(exception);
    }
    
}