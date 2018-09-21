package com.alx.conductor.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * Handler que captura todas as exceções ocorridas no sistema e retorna de forma mais amigável para o usuario,
 * utilizando a classe ApiError.
 * @author ximenes
 *
 */
@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> validateError(ConstraintViolationException ex) {
		return ResponseEntity.badRequest().body(ex.getConstraintViolations().stream().map(cv -> cv.getMessage()).collect(Collectors.toList()));
	}
	
	@ExceptionHandler(Throwable.class)
    public ResponseEntity<?> otherErrors(Throwable ex) {
		List<String> erros = new ArrayList<>();
		Exception cause = (Exception) ex.getCause();
		while(cause != null) {
			erros.add(cause.getClass().getName());
			cause = (Exception) cause.getCause();
		}
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), erros));
    }
}
