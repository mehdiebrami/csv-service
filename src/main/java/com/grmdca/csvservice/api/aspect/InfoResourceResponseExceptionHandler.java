package com.grmdca.csvservice.api.aspect;

import com.grmdca.csvservice.exception.NotFoundInfoException;
import com.grmdca.csvservice.exception.CsvFileException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class InfoResourceResponseExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(NotFoundInfoException.class)
	public final ResponseEntity<Object> handleBusinessException(NotFoundInfoException ex) {
		logger.error(ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CsvFileException.class)
	public final ResponseEntity<Object> handleBusinessException(CsvFileException ex) {
		logger.error(ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleGeneralException(Exception ex) {
		logger.error("unexpected error ", ex);
		return new ResponseEntity<>("FAILURE: contact Admin", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
