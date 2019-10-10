package com.developer.finalprojectseg3102;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class RunApplication {

	@RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
	
	public static void main(String[] args) {
		SpringApplication.run(RunApplication.class, args);
	}

}
