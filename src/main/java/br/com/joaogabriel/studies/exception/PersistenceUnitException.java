package br.com.joaogabriel.studies.exception;

public class PersistenceUnitException extends RuntimeException {
	private static final long serialVersionUID = 0L;
	
	public PersistenceUnitException(String message) {
		super(message);
	}

}
