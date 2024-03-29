package it.spaghettisource.springbootexample.i18n;


import java.text.MessageFormat;
import java.util.Locale;

/**
 * <code>FileMessageHelper</code> provides utilities to produce complex messages using the {@link FileMessageRepository} and the support of the java utilities of {@link MessageFormat}  
 *
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 *
 */
public class FileMessageHelper implements I18NMessageHelper{
	
	private FileMessageRepository messageRepository;
	
	private static Object[] EMPTY_PARAMETERS = new Object[] {};
	
	/**
	 * set the message repository
	 * 
	 * @param messageRepository
	 */
	public void setMessageRepository(FileMessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	/**
	 * Utility to support {@link MessageFormat}
	 * it return a formatted message internationalized by the system locale 
	 * 
	 * @param messageId of the message to find from the error Messages bundle
	 * @return the message formatted and internationalized
	 */
	public String getFormattedMessageI18N(String messageId)
	{
		String message = messageRepository.getMessageById(messageId);
		return getFormattedMessage(message, EMPTY_PARAMETERS);
	}	
	
	/**
	 * Utility to support {@link MessageFormat}
	 * it return a formatted message internationalized by the system locale 
	 * 
	 * @param messageId of the message to find from the error Messages bundle
	 * @param arguments that will be injected in the message
	 * @return the message formatted and internationalized
	 */
	public String getFormattedMessageI18N(String messageId, Object... arguments)
	{
		String message = messageRepository.getMessageById(messageId);
		return getFormattedMessage(message, arguments);
	}

	/**
	 * Utility to support {@link MessageFormat}
	 * it return a formatted message internationalized by the locale 
	 * 
	 * @param messageId of the message to find from the error Messages bundle
	 * @param arguments that will be injected in the message
	 * @return the message formatted and internationalized
	 */
	public String getFormattedMessageI18N(Locale locale,String messageId, Object... arguments)
	{
		String message = messageRepository.getMessageById(messageId,locale);
		return getFormattedMessage(message, arguments);
	}
	
	
	/**
	 * Utility to support {@link MessageFormat}
	 * it return a formatted message 
	 * 
	 * @param message that follow the {@link MessageFormat} convention
	 * @param arguments that will be injected in the message
	 * @return the message formatted
	 */
	private static String getFormattedMessage(String message, Object... arguments)
	{
		return MessageFormat.format(message, arguments);
	}	
	
}
