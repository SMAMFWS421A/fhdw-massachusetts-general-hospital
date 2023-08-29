package com.fhdw.hospitalbe.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fhdw.hospitalbe.DatabaseTestUtil;
import com.fhdw.hospitalbe.model.Appointment;
import com.fhdw.hospitalbe.model.Visit;
import com.fhdw.hospitalbe.model.builder.AppointmentBuilder;
import com.fhdw.hospitalbe.model.mapper.AppointmentMapper;
import com.fhdw.hospitalbe.model.mapper.DoctorMapper;
import com.fhdw.hospitalbe.model.mapper.PatientRecordMapper;
import com.fhdw.hospitalbe.repository.AppointmentRepository;
import com.fhdw.hospitalbe.repository.VisitRepository;
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

import java.time.LocalDateTime;
import java.util.List;

import static com.fhdw.hospitalbe.DatabaseTestUtil.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AppointmentRepository appointmentRepository;

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
    public void getAppointmentTest() throws Exception {
        DoctorTable d = databaseTestUtil.createDoctor();
        PatientTable prt = databaseTestUtil.createPatientWithRecord();
        AppointmentTable appointment = databaseTestUtil.createAppointment(d, prt.getPatientRecord());

        mockMvc.perform(get("/api/v1/appointment/" + appointment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Appointment appointmentResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Appointment.class);
                    Assertions.assertEquals(appointment.getAppeal(), appointmentResponse.getAppeal());
                    Assertions.assertNotNull(appointmentResponse.getId());
                });
    }

    @Test
    public void getAllAppointmentsTest() throws Exception {
        DoctorTable d = databaseTestUtil.createDoctor();
        PatientTable prt = databaseTestUtil.createPatientWithRecord();
        AppointmentTable appointment = databaseTestUtil.createAppointment(d, prt.getPatientRecord());
        DoctorTable d2 = databaseTestUtil.createDoctor();
        AppointmentTable appointment2 = databaseTestUtil.createAppointment(d2, prt.getPatientRecord());

        mockMvc.perform(get("/api/v1/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    List<Appointment> appointmentResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Appointment>>(){});
                    Assertions.assertEquals(2, appointmentResponse.size());
                    Assertions.assertNotNull(appointmentResponse.get(0).getId());
                    Assertions.assertNotNull(appointmentResponse.get(1).getId());
                });
    }

    @Test
    public void createAppointmentTest() throws Exception {
        DoctorTable d = databaseTestUtil.createDoctor();
        PatientTable prt = databaseTestUtil.createPatientWithRecord();
        Appointment appointment = new AppointmentBuilder()
                .patientRecord(PatientRecordMapper.fromTable(prt.getPatientRecord()))
                .doctor(DoctorMapper.fromTable(d))
                .appeal("test")
                .visitingTime(LocalDateTime.of(2009, 10, 10, 10, 10))
                .build();

        mockMvc.perform(post("/api/v1/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(appointment))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(result -> {
                    Appointment appointmentResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Appointment.class);
                    Assertions.assertEquals(appointment.getAppeal(), appointmentResponse.getAppeal());
                    Assertions.assertNotNull(appointmentResponse.getId());
                });

        AppointmentTable appointmentTable = appointmentRepository.findOne(Example.of(AppointmentMapper.toTable(appointment))).get();
        Assertions.assertEquals(appointmentTable.getAppeal(), appointment.getAppeal());
        Assertions.assertEquals(appointmentTable.getVisitingTime(), appointment.getVisitingTime());
        Assertions.assertNotNull(appointmentTable.getId());
    }

    @Test
    public void createVisitFromAppointmentTest() throws Exception {
        DoctorTable d = databaseTestUtil.createDoctor();
        PatientTable prt = databaseTestUtil.createPatientWithRecord();
        Appointment appointment = AppointmentMapper.fromTable(databaseTestUtil.createAppointment(d, prt.getPatientRecord()));

        mockMvc.perform(get("/api/v1/appointment/"+appointment.getId()+"/to-visit")
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
    public void updateAppointmentTest() throws Exception {
        DoctorTable d = databaseTestUtil.createDoctor();
        PatientTable prt = databaseTestUtil.createPatientWithRecord();
        AppointmentTable appointmentT = databaseTestUtil.createAppointment(d, prt.getPatientRecord());
        appointmentT.setAppeal("Neu");
        Appointment appointment = AppointmentMapper.fromTable(appointmentT);

        mockMvc.perform(put("/api/v1/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(appointment))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Appointment appointmentResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Appointment.class);
                    Assertions.assertEquals(appointment.getAppeal(), appointmentResponse.getAppeal());
                    Assertions.assertEquals(appointment.getId(),appointmentResponse.getId());
                });

        AppointmentTable appointmentTable = appointmentRepository.findOne(Example.of(AppointmentMapper.toTable(appointment))).get();
        Assertions.assertEquals(appointmentTable.getAppeal(), appointment.getAppeal());
        Assertions.assertEquals(appointmentTable.getVisitingTime(), appointment.getVisitingTime());
        Assertions.assertEquals(appointment.getId(),appointmentTable.getId());
    }


    @Test
    public void deleteAppointmentTest() throws Exception {
        DoctorTable d = databaseTestUtil.createDoctor();
        PatientTable prt = databaseTestUtil.createPatientWithRecord();
        AppointmentTable appointment = databaseTestUtil.createAppointment(d, prt.getPatientRecord());

        mockMvc.perform(delete("/api/v1/appointment/" + appointment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Assertions.assertEquals(0, appointmentRepository.findAll().size());
    }
}
