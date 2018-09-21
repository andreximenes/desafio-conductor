package com.alx.conductor.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * Classe utilziada para deixar o retorno de excecões mais amigavel para o usuario
 * @author ximenes
 *
 */
public class ApiError {
	 
    private HttpStatus status;
    private String message;
    private List<String> errors;
 
    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
 
    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getErrors() {
		return errors;
	}
    
    
}