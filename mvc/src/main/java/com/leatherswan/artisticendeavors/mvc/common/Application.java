package com.leatherswan.artisticendeavors.mvc.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = "com.leatherswan.artisticendeavors")
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.leatherswan.artisticendeavors.jpa")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}
