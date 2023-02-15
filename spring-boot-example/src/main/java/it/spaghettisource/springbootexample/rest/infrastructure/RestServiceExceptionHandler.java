package it.spaghettisource.springbootexample.rest.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.spaghettisource.springbootexample.exception.BaseException;
import it.spaghettisource.springbootexample.security.AppUserDetailServiceImpl;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestServiceExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(RestServiceExceptionHandler.class);
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String error = "Failed to read request, malformed JSON request";
		return buildResponseEntity(new RestServiceException(HttpStatus.BAD_REQUEST, error, ex));
	}


	private ResponseEntity<Object> buildResponseEntity(RestServiceException exception) {
		return new ResponseEntity<>(exception, exception.getStatus());
	}

	/**
	 * framework exception
	 * 
	 */
	@ExceptionHandler(BaseException.class)
	protected ResponseEntity<Object> handleBaseExcecption(BaseException ex) {
		RestServiceException apiError; 
				
		if(ex.getCause()!=null) {
			apiError = new RestServiceException(ex.getHttpStatus(), ex.getMessage(), ex.getCause());			
		}else {
			apiError  = new RestServiceException(ex.getHttpStatus(), ex.getMessage());
		}
		

		return buildResponseEntity(apiError);
	}
	
	
	@ExceptionHandler(AccessDeniedException.class)
	protected ResponseEntity<Object> handleUnamanagedException(AccessDeniedException ex) {
		RestServiceException apiError = new RestServiceException(HttpStatus.UNAUTHORIZED, "Endpoint not authorized for this user", ex);
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<Object> handleUnamanagedException(RuntimeException ex) {
		RestServiceException apiError = new RestServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Unamanaged Exception", ex);
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleUnamanagedException(Exception ex) {
		RestServiceException apiError = new RestServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Unamanaged Exception", ex);
		return buildResponseEntity(apiError);
	}
	
}
