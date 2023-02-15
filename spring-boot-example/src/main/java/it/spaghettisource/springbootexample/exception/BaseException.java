package it.spaghettisource.springbootexample.exception;


import java.util.Locale;

import org.springframework.http.HttpStatus;

import it.spaghettisource.springbootexample.i18n.I18NMessageHelper;

/**
 * Superclass of all Exceptions used
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class BaseException extends RuntimeException {

	protected HttpStatus httpStatus;
	protected String errorMessage;  
	protected Object[] messageParameters;
	
	protected I18NMessageHelper messageHelper;
	
	public BaseException(Throwable cause,HttpStatus httpStatus, String errorMessage,Object... messageParameters ) {
		super(cause);
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		this.messageParameters = messageParameters;
	}

	public BaseException(String errorMessage,HttpStatus httpStatus, Object...  messageParameters ) {
		super();
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		this.messageParameters = messageParameters;
	}

	public void setMessageHelper(I18NMessageHelper messageHelper) {
		this.messageHelper = messageHelper;
	}

	@Override
	public String getMessage(){		
		return messageHelper.getFormattedMessageI18N(errorMessage, messageParameters);		 
	}

	public String getMessage(Locale locale){		
		return messageHelper.getFormattedMessageI18N(locale, errorMessage, messageParameters);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
}
