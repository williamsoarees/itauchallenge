package com.example.itauchallenge.error;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.itauchallenge.exceptions.CardException;
import com.example.itauchallenge.exceptions.CustomerException;
import com.example.itauchallenge.exceptions.PurchaseException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ErrorObject> errors = getErrors(ex);
		ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
		return new ResponseEntity<>(errorResponse, status);
	}

	private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status,
			List<ErrorObject> errors) {
		return new ErrorResponse("Requisição possui campos inválidos", status.value(), status.getReasonPhrase(),
				ex.getBindingResult().getObjectName(), errors);
	}

	private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors().stream()
				.map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
				.collect(Collectors.toList());
	}

	@Override
	public ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ErrorServiceResponse errorServiceResponse = new ErrorServiceResponse(status.name(), status.getReasonPhrase(),
				Arrays.asList(ex.getLocalizedMessage()));

		return ResponseEntity.status(status).body(errorServiceResponse);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		ErrorServiceResponse errorServiceResponse = new ErrorServiceResponse(ErrosCodes.INTERNAL_SERVER_ERROR, ex.getMessage());

		request.getDescription(false);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorServiceResponse);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {

		ErrorServiceResponse errorServiceResponse = new ErrorServiceResponse(ErrosCodes.INVALID_REQUEST, ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorServiceResponse);

	}
	
	@ExceptionHandler(PurchaseException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(PurchaseException ex) {

		ErrorServiceResponse errorServiceResponse = new ErrorServiceResponse(ErrosCodes.PURCHASE_ERROR, ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorServiceResponse);

	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(CustomerException ex) {

		ErrorServiceResponse errorServiceResponse = new ErrorServiceResponse(ErrosCodes.CUSTOMER_ERROR, ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorServiceResponse);

	}
	
	@ExceptionHandler(CardException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(CardException ex) {

		ErrorServiceResponse errorServiceResponse = new ErrorServiceResponse(ErrosCodes.CARD_ERROR, ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorServiceResponse);

	}

}
