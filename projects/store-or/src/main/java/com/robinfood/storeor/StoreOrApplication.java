package com.robinfood.storeor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StoreOrApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreOrApplication.class, args);
	}

}
