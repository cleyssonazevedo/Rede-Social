package br.com.social.controller;

import javax.persistence.NoResultException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.social.exception.ConstraintViolationException;
import br.com.social.exception.UnauthorizedException;

@Component
public interface ErrorHandler {
	
	@ExceptionHandler(NoResultException.class)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	default void NoResultExceptionHandler(NoResultException e){
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	default void UnauthorizedHandler(UnauthorizedException e) {
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	default String ConstraintError(ConstraintViolationException e){
		return e.getMessage();
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	default void ExceptionErrorHandler(Exception e){
		e.printStackTrace();
	}
}
