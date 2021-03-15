package com.mpraticien;

import com.mpraticien.service.dao.PraticienRepository;
import com.mpraticien.service.dao.RecommandationRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableDiscoveryClient
@EnableFeignClients("com.mpraticien")
@EnableMongoRepositories(basePackageClasses = {PraticienRepository.class, RecommandationRepository.class})
@EnableJpaRepositories(excludeFilters =
@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {PraticienRepository.class,RecommandationRepository.class}))
@SpringBootApplication
public class MpraticienApplication {

	public static void main(String[] args) {
		SpringApplication.run(MpraticienApplication.class, args);
	}

}
