package com.example.emsui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.impubudu.emscloud.commons.model")
public class EmsUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsUiApplication.class, args);
	}

}
