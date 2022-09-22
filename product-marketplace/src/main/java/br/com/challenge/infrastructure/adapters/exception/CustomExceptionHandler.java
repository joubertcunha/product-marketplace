package br.com.challenge.infrastructure.adapters.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.challenge.domain.data.ErroExceptionDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
public class CustomExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleHeaderException(ResourceNotFoundException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;

		return new ResponseEntity(new ErroExceptionDTO(status.value(), status.getReasonPhrase(), Arrays.asList(ex.getLocalizedMessage())), status);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handle(MethodArgumentNotValidException ex) {
		List<String> details = new ArrayList<>();

		ex.getBindingResult().getFieldErrors().stream()
				.map(item -> details.add(messageSource.getMessage(item, LocaleContextHolder.getLocale())))
				.collect(Collectors.toList());

		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ResponseEntity(new ErroExceptionDTO(status.value(), status.getReasonPhrase(), details), status);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		return new ResponseEntity(new ErroExceptionDTO(status.value(), status.getReasonPhrase(), Arrays.asList(ex.getLocalizedMessage())), status);
	}
}
