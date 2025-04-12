package com.MyProject.tsServiceRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TsServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TsServiceRegistryApplication.class, args);
	}

}
