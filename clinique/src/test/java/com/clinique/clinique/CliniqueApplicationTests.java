package com.clinique.clinique;

import com.clinique.model.Assessment;
import com.clinique.service.dao.AssessmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class CliniqueApplicationTests {

	@Autowired
	MockMvc mvc;

	@Autowired
	WebApplicationContext applicationContext;

	@BeforeEach
	public void setUp(){
		this.mvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).build();
	}

	ObjectMapper mapper = new ObjectMapper();

	Assessment assess = new Assessment();

	@Test
	public void assessmentTest() throws Exception{
		String input = mapper.writeValueAsString(assess);
		//generate assessment
		mvc.perform(get("/assess/id/1"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

		//find all assessment
		mvc.perform(get("/assess"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

	}

}
