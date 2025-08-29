package com.teachable.takehomeassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TakehomeassessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TakehomeassessmentApplication.class, args);
	}

}
