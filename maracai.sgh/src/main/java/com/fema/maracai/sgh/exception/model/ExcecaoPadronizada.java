package com.fema.maracai.sgh.exception.model;

import java.io.Serializable;

/**
 * 
 * @author Paulo Romano <br>
 * 
 * Classe responsável por estruturar as informações quando
 * uma exceção for lançada <br>
 * 
 * <b>Atributos:</b><br>
 * <ul>
 * <li>timestamp - Instante em que a exceção foi lançada</li>
 * <li>status - Código HTTP da exceção</li>
 * <li>error - Mensagem genérica do tipo da exceção</li>
 * <li>message - Mensagem específica do motivo que gerou a exceção</li>
 * <li>path - Path da requisição que originou a exceção</li>
 * </ul>
 */
public class ExcecaoPadronizada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	

	public ExcecaoPadronizada(Long timestamp, Integer status, String error, String message, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}


	public Long getTimestamp() {
		return timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}
}

