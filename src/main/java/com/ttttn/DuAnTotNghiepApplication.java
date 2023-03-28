package com.ttttn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableConfigurationProperties
//@ComponentScan(basePackages = "com.ttttn")
//@EnableJpaRepositories(basePackages = {"com.ttttn.repository"})
public class DuAnTotNghiepApplication  {

	public static void main(String[] args) {
		SpringApplication.run(DuAnTotNghiepApplication.class, args);

	}

}
