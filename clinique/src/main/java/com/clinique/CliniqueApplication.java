package com.clinique;

import com.clinique.service.dao.AppointmentRepository;
import com.clinique.service.dao.AssessmentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EnableFeignClients("com.clinique")
@EnableJpaRepositories(basePackageClasses = {AppointmentRepository.class,AssessmentRepository.class})
@SpringBootApplication
public class CliniqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(CliniqueApplication.class, args);
	}

}
