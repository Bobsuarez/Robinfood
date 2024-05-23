package com.robinfood.localorderbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages="com.robinfood.localorderbc.entities")
@EnableJpaRepositories(
		basePackages = "com.robinfood.localorderbc.repositories"
)
@EnableFeignClients
@SpringBootApplication
public class LocalOrderBcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalOrderBcApplication.class, args);
	}

}
