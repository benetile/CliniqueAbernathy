package com.patient.patient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.model.Patient;
import com.patient.service.dao.PatientRepository;
import org.junit.Before;
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
class PatientApplicationTests {

	@Autowired
	MockMvc mvc;

	@Autowired
	WebApplicationContext applicationContext;

	@Autowired
	PatientRepository patientRepository;

	@Before
	public void setUp(){
		this.mvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).build();
	}

	ObjectMapper mapper = new ObjectMapper();
	Patient patient =new Patient("first","last",new Date(System.currentTimeMillis()),"F","address1","0000");

	@Test
	public void getAllPatient() throws Exception{
		//Arrange & Act
		mvc.perform(get("/pat/patients"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getPatientByFirstnameAndLastNameTest() throws Exception{
		//
		mvc.perform(get("/pat/patients/Benny/Etilefanela"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getPatientByIdTest() throws Exception{
		mvc.perform(get("/pat/id/1"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void updateAndDeletePatientTest() throws Exception{
		String input = mapper.writeValueAsString(patient);
		MvcResult result = this.mvc.perform(get("/pat/update/66").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		MvcResult update2 = this.mvc.perform(MockMvcRequestBuilders.put("/pat/update/66")
				.contentType(MediaType.APPLICATION_JSON).content(input)
				.param("firstname","firstname4")
				.param("lastname","lastname4")
				.param("birthday",new Date().toString())
				.param("sex","M")
				.param("address","address update")
				.param("phone","1111111111"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();

		//delete patient
		MvcResult delete = this.mvc.perform(get("/pat/delete/66").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		assertFalse(patientRepository.findById(28).isPresent());
	}
}
