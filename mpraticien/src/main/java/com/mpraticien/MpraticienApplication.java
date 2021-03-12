package com.mpraticien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.mpraticien")
@EnableDiscoveryClient
public class MpraticienApplication {

	public static void main(String[] args) {
		SpringApplication.run(MpraticienApplication.class, args);
	}

}
