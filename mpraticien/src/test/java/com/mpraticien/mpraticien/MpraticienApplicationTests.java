package com.mpraticien.mpraticien;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpraticien.model.Praticien;
import com.mpraticien.model.Recommandation;
import com.mpraticien.service.dao.PraticienRepository;
import com.mpraticien.service.dao.RecommandationRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class MpraticienApplicationTests {

	@Autowired
	MockMvc mvc;

	@Autowired
	WebApplicationContext applicationContext;

	@Autowired
	RecommandationRepository recommandationRepository;

	@Autowired
	PraticienRepository praticienRepository;

	@BeforeEach
	public void setUp(){
		this.mvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).build();
	}

	ObjectMapper mapper = new ObjectMapper();

	Recommandation recommandation = new Recommandation(1,"il est malade");

	Praticien praticien = new Praticien("firstn","lastn","speciality","F","1111111");

	@Test
	public void RecommandationTest() throws Exception{
		String input = mapper.writeValueAsString(recommandation);
		//save recommandation
		//MvcResult mvcResult = this.mvc.perform(get("/recommandations/add/{id}").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		MvcResult mvcSave = this.mvc.perform(MockMvcRequestBuilders.post("/recommandations/add/1")
				.contentType(MediaType.APPLICATION_JSON).content(MediaType.APPLICATION_JSON_VALUE)
				.param("idPatient","1")
				.param("observation","il est malade"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();

		//find all recommandation
		mvc.perform(get("/recommandations/patients/1"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

		//find by id
		mvc.perform(get("/recommandations/patients/id/46"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

		//update recommandation
		MvcResult update = this.mvc.perform(MockMvcRequestBuilders.put("/recommandations/update/46")
				.contentType(MediaType.APPLICATION_JSON).content(input)
				.param("idPatient","1")
				.param("observation","malade update"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();

		//delete recommandation
		MvcResult delete = this.mvc.perform(get("/recommandations/delete/46").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		//assertFalse(recommandationRepository.findById(32).isPresent());
	}

	@Test
	public void praticienTest() throws Exception{
		String input = mapper.writeValueAsString(praticien);
		//save praticien
		MvcResult mvcSave = this.mvc.perform(MockMvcRequestBuilders.post("/prat/add")
				.contentType(MediaType.APPLICATION_JSON).content(input)
				.param("firstname","firstname")
				.param("lastname","lastname")
				.param("speciality","speciality")
				.param("sex","F")
				.param("phone","1111111"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();

		//find all recommandation
		mvc.perform(get("/prat/praticiens"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

		//find by speciality
		mvc.perform(get("/prat/Generaliste"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

		//find by id
		mvc.perform(get("/prat/id/3"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

		//find by firstname
		mvc.perform(get("/prat/firstname/Divine"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

		//find by firstname and lastname
		mvc.perform(get("/prat/lastname/lastname1"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

		//update recommandation
		MvcResult update = this.mvc.perform(MockMvcRequestBuilders.put("/prat/update/9")
				.contentType(MediaType.APPLICATION_JSON).content(input)
				.param("firstname","firstname")
				.param("lastname","lastname")
				.param("speciality","speciality")
				.param("sex","F")
				.param("phone","1111111"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		//delete recommandation
		//MvcResult delete = this.mvc.perform(get("/prat/delete/10").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		//assertFalse(recommandationRepository.findById(5).isPresent());
	}
	@Test
	void contextLoads() {
	}

}
