package it.spaghettisource.springbootexample.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import it.spaghettisource.springbootexample.service.dto.CustomerRequest;

@JsonTest
public class CustomerRequestTest {

	@Autowired
	private JacksonTester<CustomerRequest> json;

	@Test
	public void CustomerRequestDeserialize_OK_Test() throws IOException {

		String expected = """
							{
							    "name": "name",
							    "email": "email",
							    "age": 99
							}
						 """;

		//asserts
		assertThat(json.parse(expected)).isEqualTo(new CustomerRequest("name", "email", 99));
		assertThat(json.parseObject(expected).getName()).isEqualTo("name");
		assertThat(json.parseObject(expected).getEmail()).isEqualTo("email");
		assertThat(json.parseObject(expected).getAge()).isEqualTo(99);		

	}


}
