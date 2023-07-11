package com.fema.maracai.sgh.exception.custom;

/**
 * 
 * @author Paulo Romano - [paulo-romano_133@hotmail.com] <br>
 * 
 * Classe para exceções quando um objeto não for encontrado
 */
public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	public ObjectNotFoundException(String message) {
		super(message);
	}
}
