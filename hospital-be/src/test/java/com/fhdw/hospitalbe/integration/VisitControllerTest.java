package com.fhdw.hospitalbe.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fhdw.hospitalbe.DatabaseTestUtil;
import com.fhdw.hospitalbe.model.Appointment;
import com.fhdw.hospitalbe.model.Visit;
import com.fhdw.hospitalbe.model.builder.VisitBuilder;
import com.fhdw.hospitalbe.model.mapper.AppointmentMapper;
import com.fhdw.hospitalbe.model.mapper.DoctorMapper;
import com.fhdw.hospitalbe.model.mapper.PatientRecordMapper;
import com.fhdw.hospitalbe.model.mapper.VisitMapper;
import com.fhdw.hospitalbe.repository.AppointmentRepository;
import com.fhdw.hospitalbe.repository.VisitRepository;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
    @Autowired
    private AppointmentRepository appointmentRepository;


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
    public void getAllVisitsTest() throws Exception {
        DoctorTable d = databaseTestUtil.createDoctor();
        PatientTable prt = databaseTestUtil.createPatientWithRecord();
        VisitTable visit = databaseTestUtil.createVisit(d, prt.getPatientRecord());
        DoctorTable d2 = databaseTestUtil.createDoctor();
        VisitTable visit2 = databaseTestUtil.createVisit(d2, prt.getPatientRecord());

        mockMvc.perform(get("/api/v1/visit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    List<Visit> visitResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Visit>>() {
                    });
                    Assertions.assertEquals(2, visitResponse.size());
                    Assertions.assertNotNull(visitResponse.get(0).getId());
                    Assertions.assertNotNull(visitResponse.get(1).getId());
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
    public void createVisitFromAppointmentTest() throws Exception {
        DoctorTable d = databaseTestUtil.createDoctor();
        PatientTable prt = databaseTestUtil.createPatientWithRecord();
        Appointment appointment = AppointmentMapper.fromTable(databaseTestUtil.createAppointment(d, prt.getPatientRecord()));

        mockMvc.perform(get("/api/v1/visit/register-from-appointment/" + appointment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(result -> {
                    Visit visitResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Visit.class);
                    Assertions.assertEquals(appointment.getAppeal(), visitResponse.getAppeal());
                    Assertions.assertNotNull(visitResponse.getId());
                });

        VisitTable visitTable = visitRepository.findAll().get(0);
        Assertions.assertEquals(visitTable.getAppeal(), appointment.getAppeal());
        Assertions.assertEquals(visitTable.getDoctor().getId(), appointment.getDoctor().getId());
        Assertions.assertNotNull(visitTable.getId());
        Assertions.assertEquals(0, appointmentRepository.findAll().size());

    }

    @Test
    public void updateVisitTest() throws Exception {
        DoctorTable d = databaseTestUtil.createDoctor();
        PatientTable prt = databaseTestUtil.createPatientWithRecord();
        Visit visit = VisitMapper.fromTable(databaseTestUtil.createVisit(d, prt.getPatientRecord()));

        visit.setArrivedTime(LocalDateTime.of(2109, 10, 10, 10, 12));

        mockMvc.perform(put("/api/v1/visit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(visit))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Visit visitResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Visit.class);
                    Assertions.assertEquals(visit.getAppeal(), visitResponse.getAppeal());
                    Assertions.assertEquals(visit.getId(), visitResponse.getId());
                });

        VisitTable visitTable = visitRepository.findOne(Example.of(VisitMapper.toTable(visit))).get();
        Assertions.assertEquals(visitTable.getAppeal(), visit.getAppeal());
        Assertions.assertEquals(visitTable.getArrivedTime(), visit.getArrivedTime());
        Assertions.assertEquals(visit.getId(), visitTable.getId());
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
