package com.wic.app.scar.reporting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScarRmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScarRmApplication.class, args);
	}

}
