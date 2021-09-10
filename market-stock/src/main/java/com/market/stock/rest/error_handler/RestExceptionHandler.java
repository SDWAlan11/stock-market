package com.market.stock.rest.error_handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
@CrossOrigin(origins = "http://localhost:3000/")
public class RestExceptionHandler {

	@ExceptionHandler({RuntimeException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CustomErrorDto handleRunTimeException(RuntimeException e) {
	     return createCustomError(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({Exception.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CustomErrorDto handleRunTimeException(Exception e) {
		return createCustomError(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({NoSuchElementException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CustomErrorDto handleNotFoundException(NoSuchElementException e) {
	    return createCustomError(e, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CustomErrorDto handleBadRequestException(MethodArgumentNotValidException e) {
	    return createCustomError(e, HttpStatus.BAD_REQUEST);
	}
	
	private CustomErrorDto createCustomError(Exception e, HttpStatus httpStatus) {
		CustomErrorDto customErrorDto = new CustomErrorDto();
		customErrorDto.setError(e.getMessage());
		customErrorDto.setStatus(httpStatus.value());
		customErrorDto.setTimestamp(LocalDateTime.now());
		return customErrorDto;
	}

}
