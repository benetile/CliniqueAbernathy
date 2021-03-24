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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Before()
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //Patient patient1 = new Patient(1,"firstName1","lastName1",new Date(System.currentTimeMillis()),"M","homeAddress","00000000");

    @Test
    public void addUser(){

    }
}
