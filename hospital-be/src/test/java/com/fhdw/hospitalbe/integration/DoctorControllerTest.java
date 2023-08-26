package com.fhdw.hospitalbe.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fhdw.hospitalbe.DatabaseTestUtil;
import com.fhdw.hospitalbe.model.*;
import com.fhdw.hospitalbe.model.builder.DoctorBuilder;
import com.fhdw.hospitalbe.model.mapper.DoctorMapper;
import com.fhdw.hospitalbe.repository.DoctorRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DatabaseTestUtil databaseTestUtil;


    @BeforeEach
    public void init(){
        databaseTestUtil.deleteAll();
    }

    @Test
    public void getDoctorTest() throws Exception {
        Doctor doctor = DoctorMapper.fromTable(databaseTestUtil.createDoctor());

        mockMvc.perform(get("/api/v1/doctor/" + doctor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Doctor doctorResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Doctor.class);
                    Assertions.assertEquals(doctor.getLastName(), doctorResponse.getLastName());
                    Assertions.assertNotNull(doctorResponse.getId());
                });
    }


    @Test
    public void createDoctorTest() throws Exception {
        Doctor doctor = new DoctorBuilder().firstName("First").lastName("Last").build();

        mockMvc.perform(post("/api/v1/doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(doctor))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(result -> {
                    Doctor doctorResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Doctor.class);
                    Assertions.assertEquals(doctor.getLastName(), doctorResponse.getLastName());
                    Assertions.assertNotNull(doctorResponse.getId());
                });

        DoctorTable doctorDb = doctorRepository.findOne(Example.of(DoctorMapper.toTable(doctor))).get();
        Assertions.assertEquals(doctor.getLastName(), doctorDb.getLastName());
        Assertions.assertNotNull(doctorDb.getId());
    }

    @Test
    public void deleteDoctorTest() throws Exception{
        Doctor doctor = DoctorMapper.fromTable(databaseTestUtil.createDoctor());

        mockMvc.perform(delete("/api/v1/doctor/" + doctor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Assertions.assertEquals(0, doctorRepository.findAll().size());
    }

    @Test
    public void deleteDoctorWithAppointmentsAndVisitsTest() throws Exception{
        DoctorTable d = databaseTestUtil.createDoctor();
        PatientTable p = databaseTestUtil.createPatientWithRecord();
        AppointmentTable a = databaseTestUtil.createAppointment(d, p.getPatientRecord());
        VisitTable v = databaseTestUtil.createVisit(d, p.getPatientRecord());

        mockMvc.perform(delete("/api/v1/doctor/" + d.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Assertions.assertEquals(0, doctorRepository.findAll().size());
    }


}
