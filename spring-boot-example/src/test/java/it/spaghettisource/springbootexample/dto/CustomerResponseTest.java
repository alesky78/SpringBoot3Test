package it.spaghettisource.springbootexample.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import it.spaghettisource.springbootexample.service.dto.CustomerResponse;

@JsonTest
public class CustomerResponseTest {
	
    @Autowired
    private JacksonTester<CustomerResponse> json;

    @Test
    public void CustomerRequestSerialization_OK_Test() throws IOException {
    	CustomerResponse request = new CustomerResponse(1L,"name", "email", 99);
    	
    	//asserts
        assertThat(json.write(request)).isStrictlyEqualToJson("CustomerResponseSerialization_OK_Test.json");
        assertThat(json.write(request)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(request)).extractingJsonPathNumberValue("@.id").isEqualTo(1);        
        assertThat(json.write(request)).hasJsonPathStringValue("@.name");
        assertThat(json.write(request)).extractingJsonPathStringValue("@.name").isEqualTo("name");
        assertThat(json.write(request)).hasJsonPathStringValue("@.email");
        assertThat(json.write(request)).extractingJsonPathStringValue("@.email").isEqualTo("email");
        assertThat(json.write(request)).hasJsonPathNumberValue("@.age");
        assertThat(json.write(request)).extractingJsonPathNumberValue("@.age").isEqualTo(99);        
    }
    
    
}
