package com.fhdw.hospitalbe.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fhdw.hospitalbe.DatabaseTestUtil;
import com.fhdw.hospitalbe.model.Patient;
import com.fhdw.hospitalbe.model.builder.PatientBuilder;
import com.fhdw.hospitalbe.model.mapper.PatientMapper;
import com.fhdw.hospitalbe.repository.PatientRepository;
import com.fhdw.hospitalbe.repository.table.AppointmentTable;
import com.fhdw.hospitalbe.repository.table.DoctorTable;
import com.fhdw.hospitalbe.repository.table.PatientTable;
import com.fhdw.hospitalbe.repository.table.VisitTable;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
        Patient patient = PatientMapper.fromTable(databaseTestUtil.createPatientWithRecord());

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

        PatientTable patientDb = patientRepository.findOne(Example.of(PatientMapper.toTable(patient))).get();
        Assertions.assertEquals(patient.getLastName(), patientDb.getLastName());
        Assertions.assertNotNull(patientDb.getId());
    }

    @Test
    public void deletePatientTest() throws Exception{
        Patient patient = PatientMapper.fromTable(databaseTestUtil.createPatientWithRecord());

        mockMvc.perform(delete("/api/v1/patient/" + patient.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Assertions.assertEquals(0, patientRepository.findAll().size());
    }

    @Test
    public void deletePatientWithAppointmentsAndVisitsTest() throws Exception{
        DoctorTable d = databaseTestUtil.createDoctor();
        PatientTable p = databaseTestUtil.createPatientWithRecord();
        AppointmentTable a = databaseTestUtil.createAppointment(d, p.getPatientRecord());
        VisitTable v = databaseTestUtil.createVisit(d, p.getPatientRecord());

        mockMvc.perform(delete("/api/v1/patient/" + d.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Assertions.assertEquals(0, patientRepository.findAll().size());
    }

}