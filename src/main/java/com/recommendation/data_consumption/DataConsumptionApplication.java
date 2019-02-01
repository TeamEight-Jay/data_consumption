package com.recommendation.data_consumption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DataConsumptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataConsumptionApplication.class, args);
	}

}

