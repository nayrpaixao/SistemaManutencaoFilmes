package com.cinefilmes.user.exceptions;

public class DuplicateFilmException extends RuntimeException {
   	private static final long serialVersionUID = 1L;

	public DuplicateFilmException(String message) {
        super(message);
    }
}

