package com.fema.maracai.sgh.exception.model;

import java.io.Serializable;

/**
 * 
 * @author Paulo Romano <br>
 * 
 * Classe para erros de validação que será utilizada para
 * armazenar o nome do campo que gerou uma exceção e a 
 * mensagem do erro
 */
public class FieldMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;
	

	public FieldMessage(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}


	public String getFieldName() {
		return fieldName;
	}

	public String getMessage() {
		return message;
	}
}
