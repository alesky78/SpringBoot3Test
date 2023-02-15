package it.spaghettisource.springbootexample.exception;

import org.springframework.http.HttpStatus;

import it.spaghettisource.springbootexample.i18n.I18NMessageHelper;

/**
 * Factory class used to create exception, this class allow to format the correct error message and hide the error code 
 * avoiding to put the error code in the business code
 * 
 * @author Alessando D'Ottavio
 * @version 1.0
 */
public class ExceptionFactory {

	private final static int HTTP_INTERNAL_SERVER_ERROR = 500;
	
	private static Object[] EMPTY_PARAMETERS = new Object[] {};
	
	private I18NMessageHelper messageHelper;
	
	public void setMessageHelper(I18NMessageHelper messageHelper) {
		this.messageHelper = messageHelper;
	}

	//start the definition of the exceptions here
	public BaseException getUnexpectedException(Throwable cause){		
		return getException(cause, "exception.UnexpectedException", EMPTY_PARAMETERS);
	}
	
	public BaseException getUnmanagedException(){		
		return getException("exception.UnmanagedException", EMPTY_PARAMETERS);
	}
	
	public BaseException getEntityNotFound(Object id) {
		return getException(HttpStatus.NOT_FOUND, "exception.entityNotFound", id);
	}
	
	public BaseException getEntityNotFound(String entityName, Object id) {
		return getException(HttpStatus.NOT_FOUND, "exception.entityTypeNotFound", new Object[] {entityName,id});
	}
	
	
	///////////internal methods///////////
	private BaseException getException(String errorMessage,Object... messageParameters ){
		BaseException ex = new BaseException(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR, messageParameters);
		ex.setMessageHelper(messageHelper);
		return ex;
	}
	
	private BaseException getException(HttpStatus httpStatus, String errorMessage,Object... messageParameters ){
		BaseException ex = new BaseException(errorMessage,httpStatus, messageParameters);
		ex.setMessageHelper(messageHelper);
		return ex;
	}

	
	private BaseException getException(Throwable cause,String errorMessage,Object... messageParameters ){
		BaseException ex = new BaseException(cause,HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, messageParameters);
		ex.setMessageHelper(messageHelper);
		return ex;
	}
	
	private BaseException getException(HttpStatus httpStatus, Throwable cause,String errorMessage,Object... messageParameters ){
		BaseException ex = new BaseException(cause,httpStatus, errorMessage, messageParameters);
		ex.setMessageHelper(messageHelper);
		return ex;
	}














			
}
