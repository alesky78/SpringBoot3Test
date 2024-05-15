package com.oniusoft.spring.demo;


import com.oniusoft.spring.demo.run.Location;
import com.oniusoft.spring.demo.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;


@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Bean
	CommandLineRunner runner(){
		return args -> {
			Run run = new Run(1,"titolo", LocalDateTime.now(),LocalDateTime.now().plus(3, ChronoUnit.HOURS), Location.INDOOR);
			logger.info("run is:"+run);
		};
	}

}
