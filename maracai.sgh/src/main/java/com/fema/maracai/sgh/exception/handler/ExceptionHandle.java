package com.fema.maracai.sgh.exception.handler;

import java.time.DateTimeException;
import java.util.Objects;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fema.maracai.sgh.exception.model.ExcecaoPadronizada;
import com.fema.maracai.sgh.exception.model.ValidationError;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 
 * @author Paulo Romano <br>
 * 
 * Classe responsável por interceptar as exceções
 * e padronizar as informações para visualização
 * 
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionHandle {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ExcecaoPadronizada> illegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
		return padronizarExcecao(HttpStatus.BAD_REQUEST, "Valor inválido!", exception, request);
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ExcecaoPadronizada> illegalStateException(IllegalStateException exception, HttpServletRequest request) {
		return padronizarExcecao(HttpStatus.BAD_REQUEST, "Estado inválido!", exception, request);
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<ExcecaoPadronizada> numberFormatException(NumberFormatException exception, HttpServletRequest request) {
		return padronizarExcecao(HttpStatus.BAD_REQUEST, "Erro ao converter valor para número!", exception, request);
	}
	
	@ExceptionHandler(DateTimeException.class)
	public ResponseEntity<ExcecaoPadronizada> dateTimeException(DateTimeException exception, HttpServletRequest request) {
		return padronizarExcecao(HttpStatus.BAD_REQUEST, "Erro ao manipular data e/ou hora!", exception, request);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ExcecaoPadronizada> nullPointerException(NullPointerException exception, HttpServletRequest request) {
		return padronizarExcecao(HttpStatus.BAD_REQUEST, "Valor nulo!", exception, request);
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ExcecaoPadronizada> objectNotFoundException(ObjectNotFoundException exception, HttpServletRequest request) {
		return padronizarExcecao(HttpStatus.NOT_FOUND, "Objeto não encontrado!", exception, request);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ExcecaoPadronizada> emptyResultDataAccessException(EmptyResultDataAccessException exception, HttpServletRequest request) {
		return padronizarExcecao(HttpStatus.NOT_FOUND, "Nenhum resultado retornado!", exception, request);
	}
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExcecaoPadronizada> validationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
		
		ValidationError validationError = new ValidationError(System.currentTimeMillis(), 
				HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação de Campos!", 
				exception.getMessage(), request.getRequestURI());
		
		exception.getBindingResult().getFieldErrors().forEach(fieldError -> validationError.addError(
				fieldError.getField(), fieldError.getDefaultMessage()));
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationError);
	}


	/**
	 * Método responsável por padronizar as informações da exceção que foi interceptada
	 * @param httpStatus - Enum com o código HTTP da exceção
	 * @param mensagemGenericaErro - Mensagem genérica com a informação do tipo de exceção
	 * @param exception - A classe da exceção que foi interceptada
	 * @param request - A requisição que gerou a exceção
	 * @return {@code ResponseEntity<ExcecaoPadronizada>}
	 */
	private ResponseEntity<ExcecaoPadronizada> padronizarExcecao(HttpStatus httpStatus, String mensagemGenericaErro, 
			Exception exception, HttpServletRequest request) {
		
		if (Objects.isNull(exception.getMessage())) 
			exception.printStackTrace();
		
		return ResponseEntity.status(httpStatus).body(new ExcecaoPadronizada(System.currentTimeMillis(), httpStatus.value(), 
				mensagemGenericaErro, exception.getMessage(), request.getRequestURI()));
	}
}
