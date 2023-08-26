package com.fhdw.hospitalbe.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fhdw.hospitalbe.DatabaseTestUtil;
import com.fhdw.hospitalbe.model.Visit;
import com.fhdw.hospitalbe.model.builder.VisitBuilder;
import com.fhdw.hospitalbe.model.mapper.VisitMapper;
import com.fhdw.hospitalbe.model.mapper.DoctorMapper;
import com.fhdw.hospitalbe.model.mapper.PatientRecordMapper;
import com.fhdw.hospitalbe.repository.VisitRepository;
import com.fhdw.hospitalbe.repository.table.VisitTable;
import com.fhdw.hospitalbe.repository.table.DoctorTable;
import com.fhdw.hospitalbe.repository.table.PatientTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static com.fhdw.hospitalbe.DatabaseTestUtil.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VisitControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private DatabaseTestUtil databaseTestUtil;


    @BeforeEach
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
        databaseTestUtil.deleteAll();
    }

    @Test
    public void getVisitTest() throws Exception {
        DoctorTable d = databaseTestUtil.createDoctor();
        PatientTable prt = databaseTestUtil.createPatientWithRecord();
        VisitTable visit = databaseTestUtil.createVisit(d, prt.getPatientRecord());

        mockMvc.perform(get("/api/v1/visit/" + visit.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Visit visitResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Visit.class);
                    Assertions.assertEquals(visit.getAppeal(), visitResponse.getAppeal());
                    Assertions.assertNotNull(visitResponse.getId());
                });
    }

    @Test
    public void createVisitTest() throws Exception {
        DoctorTable d = databaseTestUtil.createDoctor();
        PatientTable prt = databaseTestUtil.createPatientWithRecord();
        Visit visit = new VisitBuilder()
                .patientRecord(PatientRecordMapper.fromTable(prt.getPatientRecord()))
                .doctor(DoctorMapper.fromTable(d))
                .appeal("test")
                .arrivedTime(LocalDateTime.of(2009, 10, 10, 10, 10))
                .planOfActions("test")
                .build();

        mockMvc.perform(post("/api/v1/visit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(visit))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(result -> {
                    Visit visitResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Visit.class);
                    Assertions.assertEquals(visit.getAppeal(), visitResponse.getAppeal());
                    Assertions.assertNotNull(visitResponse.getId());
                });

        VisitTable visitTable = visitRepository.findOne(Example.of(VisitMapper.toTable(visit))).get();
        Assertions.assertEquals(visitTable.getAppeal(), visit.getAppeal());
        Assertions.assertEquals(visitTable.getArrivedTime(), visit.getArrivedTime());
        Assertions.assertNotNull(visitTable.getId());
    }

    @Test
    public void deleteVisitTest() throws Exception {
        DoctorTable d = databaseTestUtil.createDoctor();
        PatientTable prt = databaseTestUtil.createPatientWithRecord();
        VisitTable visit = databaseTestUtil.createVisit(d, prt.getPatientRecord());

        mockMvc.perform(delete("/api/v1/visit/" + visit.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Assertions.assertEquals(0, visitRepository.findAll().size());
    }
}
