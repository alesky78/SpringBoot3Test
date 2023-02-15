package it.spaghettisource.springbootexample;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import it.spaghettisource.springbootexample.exception.ExceptionFactory;
import it.spaghettisource.springbootexample.i18n.FileMessageHelper;
import it.spaghettisource.springbootexample.i18n.FileMessageRepository;
import jakarta.persistence.EntityManagerFactory;

@Configuration 
@EnableTransactionManagement
public class ConfigurationApplication {


	@Bean
	@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
	public ExceptionFactory exceptionFactory() {

		FileMessageRepository repo = new FileMessageRepository("i18n.exception-message");
		FileMessageHelper exceptionMessageHelper = new FileMessageHelper();
		exceptionMessageHelper.setMessageRepository(repo);

		ExceptionFactory exceptionFactory = new ExceptionFactory();		
		exceptionFactory.setMessageHelper(exceptionMessageHelper);
		return exceptionFactory;
	}

	@Bean(name = "transactionManager")
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	

}
