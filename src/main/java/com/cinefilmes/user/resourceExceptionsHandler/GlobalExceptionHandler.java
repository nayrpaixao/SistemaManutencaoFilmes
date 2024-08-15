package com.cinefilmes.user.resourceExceptionsHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cinefilmes.user.exceptions.DuplicateFilmException;
import com.cinefilmes.user.exceptions.FilmNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(FilmNotFoundException.class)
    public ResponseEntity<String> handleFilmNotFoundException(FilmNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	
	}
	
	@ExceptionHandler(DuplicateFilmException.class)
	 public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	    }

	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleException(Exception e) {
	        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
	
	 }
	
}
