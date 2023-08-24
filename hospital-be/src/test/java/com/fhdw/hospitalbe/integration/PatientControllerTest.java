package com.fhdw.hospitalbe.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fhdw.hospitalbe.DatabaseTestUtil;
import com.fhdw.hospitalbe.model.Patient;
import com.fhdw.hospitalbe.model.builder.PatientBuilder;
import com.fhdw.hospitalbe.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.fhdw.hospitalbe.DatabaseTestUtil.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DatabaseTestUtil databaseTestUtil;


    @BeforeEach
    public void init(){
        databaseTestUtil.deleteAll();
    }

    @Test
    public void getPatientTest() throws Exception {
        Patient patient = databaseTestUtil.createPatientWithRecord();

        mockMvc.perform(get("/api/v1/patient/" + patient.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Patient patientResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Patient.class);
                    Assertions.assertEquals(patient.getLastName(), patientResponse.getLastName());
                    Assertions.assertNotNull(patientResponse.getId());
                });
    }

    @Test
    public void createPatientTest() throws Exception {
        Patient patient= new PatientBuilder().firstName("First").lastName("Last").build();

        mockMvc.perform(post("/api/v1/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(patient))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(result -> {
                    Patient patientResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Patient.class);
                    Assertions.assertEquals(patient.getLastName(), patientResponse.getLastName());
                    Assertions.assertNotNull(patientResponse.getId());
                });

        Patient patientDb = patientRepository.findOne(Example.of(patient)).get();
        Assertions.assertEquals(patient.getLastName(), patientDb.getLastName());
        Assertions.assertNotNull(patientDb.getId());
    }


}
