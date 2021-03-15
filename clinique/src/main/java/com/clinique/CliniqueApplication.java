package com.clinique;

import com.clinique.service.dao.AppointmentRepository;
import com.clinique.service.dao.AssessmentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableDiscoveryClient
@EnableFeignClients("com.clinique")
@EnableMongoRepositories(basePackageClasses = {AppointmentRepository.class, AssessmentRepository.class})
@EnableJpaRepositories(excludeFilters =
@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {AppointmentRepository.class,AssessmentRepository.class}))
@SpringBootApplication
public class CliniqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(CliniqueApplication.class, args);
	}

}
