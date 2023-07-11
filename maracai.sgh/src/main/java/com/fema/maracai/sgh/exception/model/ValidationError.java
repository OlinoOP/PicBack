package com.fema.maracai.sgh.exception.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Paulo Romano <br>
 * 
 * Classe responsável por armazenar todos os erros de validação
 * que foram gerados
 */
public class ValidationError extends ExcecaoPadronizada {
	
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	
	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}


	public List<FieldMessage> getErrors() {
		return errors;
	}


	/**
	 * Método responsável por adicionar os erros de validação
	 * na lista
	 * @param fieldName - Nome do campo
	 * @param message - Mensagem do erro
	 */
	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
}
