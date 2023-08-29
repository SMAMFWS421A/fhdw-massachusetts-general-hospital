<template>
  <div class="TerminPlan-container" :style="backgroundImageStyle">
    <div class="First-Elements">
    <h1>Terminplan</h1>


    <h2>Neuen Termin anlegen</h2>
    <button @click="openNewAppointmentForm">Neuen Termin anlegen</button>
    <div v-if="isNewAppointmentFormOpen">
      <h3>Neuer Termin</h3>
      <p>Datum: <input v-model="newAppointment.date"></p>
      <p>Uhrzeit: <input v-model="newAppointment.time"></p>
      <p>Beschreibung: <input v-model="newAppointment.description"></p>
      <p>Arzt:
        <select v-model="newAppointment.doctorId">
          <option v-for="doctor in doctors" :key="doctor.id" :value="doctor.id">{{ doctor.name }}</option>
        </select>
      </p>
      <p>Patient:
        <select v-model="newAppointment.patientId">
          <option v-for="patient in patients" :key="patient.id" :value="patient.id">{{ patient.name }}</option>
        </select>
      </p>
      <button @click="createNewAppointment">Erstellen</button>
      <button @click="cancelNewAppointment">Abbrechen</button>
    </div>



    <div v-if="selectedAppointment">
      <h2>Bearbeite Termin</h2>
      <p>Datum: <input v-model="selectedAppointment.date"></p>
      <p>Uhrzeit: <input v-model="selectedAppointment.time"></p>
      <p>Beschreibung: <input v-model="selectedAppointment.description"></p>
      <button @click="saveAppointmentChanges">Speichern</button>
      <button @click="cancelAppointmentEdit">Abbrechen</button>
    </div>
    </div>



    <h2>Bestehende Termine</h2>
    <ul>
      <li v-for="appointment in appointments" :key="appointment.id">
        <h3>{{ appointment.date }} - {{ appointment.time }}</h3>
        <p>Beschreibung: {{ appointment.description }}</p>
        <p>Arzt: {{ getDoctorName(appointment.doctorId) }}</p>
        <p>Patient: {{ getPatientName(appointment.patientId) }}</p>
        <button @click="editAppointment(appointment)">Bearbeiten</button>
        <button @click="deleteAppointment(appointment.id)">Löschen</button>
      </li>
    </ul>





    <h2>Übersicht nach Tag</h2>
    <ul v-for="(appointments, date) in appointmentsByDay" :key="date">
      <li>
        <h3>{{ date }}</h3>
        <ul>
          <li v-for="appointment in appointments" :key="appointment.id">
            <h4>{{ appointment.time }}</h4>
            <p>Beschreibung: {{ appointment.description }}</p>
            <p>Arzt: {{ getDoctorName(appointment.doctorId) }}</p>
            <p>Patient: {{ getPatientName(appointment.patientId) }}</p>
          </li>
        </ul>
      </li>
    </ul>




    <h2>Übersicht nach Woche</h2>
    <ul v-for="(appointments, weekStartDate) in appointmentsByWeek" :key="weekStartDate">
      <li>
        <h3>Woche ab {{ weekStartDate }}</h3>
        <ul>
          <li v-for="appointment in appointments" :key="appointment.id">
            <h4>{{ appointment.date }} - {{ appointment.time }}</h4>
            <p>Beschreibung: {{ appointment.description }}</p>
            <p>Arzt: {{ getDoctorName(appointment.doctorId) }}</p>
            <p>Patient: {{ getPatientName(appointment.patientId) }}</p>
          </li>
        </ul>
      </li>
    </ul>



  </div>
</template>


<script>

  import TerminPlanModel from '@/models/TerminPlanModel';

  export default {
  data() {
  return {
  isNewAppointmentFormOpen: false,
  newAppointment: {
  date: '',
  time: '',
  description: '',
  doctorId: null,
  patientId: null,
},
  selectedAppointment: null,
};
},
  computed: {
  backgroundImagePath() {
  return require('@/assets/TerminPlanBild.jpg');
},
  backgroundImageStyle() {
  return {
  backgroundImage: `url(${this.backgroundImagePath})`,
  backgroundSize: '100% 100%',
  backgroundRepeat: 'no-repeat',
  backgroundPosition: 'center',
  position: 'relative',
};
},
  appointments() {
  return TerminPlanModel.appointments;
},
  doctors() {
  return TerminPlanModel.doctors;
},
  patients() {
  return TerminPlanModel.patients;
},
  appointmentsByDay() {
  return TerminPlanModel.groupAppointmentsByDay();
},
  appointmentsByWeek() {
  return TerminPlanModel.groupAppointmentsByWeek();
},
},
  methods: {
  getDoctorName(doctorId) {
  return TerminPlanModel.getDoctorName(doctorId);
},
  getPatientName(patientId) {
  return TerminPlanModel.getPatientName(patientId);
},
  editAppointment(appointment) {
  this.selectedAppointment = { ...appointment };
},
  saveAppointmentChanges() {
  if (this.selectedAppointment) {
  const index = this.appointments.findIndex(
  appointment => appointment.id === this.selectedAppointment.id
  );
  if (index !== -1) {
  this.appointments[index] = { ...this.selectedAppointment };
  TerminPlanModel.updateAppointmentInWeekView(this.selectedAppointment);
  TerminPlanModel.updateAppointmentInDayView(this.selectedAppointment);
  this.selectedAppointment = null;
}
}
},
  cancelAppointmentEdit() {
  this.selectedAppointment = null;
},
  deleteAppointment(appointmentId) {
  const index = this.appointments.findIndex(appointment => appointment.id === appointmentId);
  if (index !== -1) {
  this.appointments.splice(index, 1);
}
},
  openNewAppointmentForm() {
  this.isNewAppointmentFormOpen = true;
},
  createNewAppointment() {
  if (this.newAppointment.date && this.newAppointment.time) {
  const newId = Math.max(...this.appointments.map(appointment => appointment.id)) + 1;
  this.newAppointment.id = newId;
  this.appointments.push({ ...this.newAppointment });
  this.cancelNewAppointment();
}
},
  cancelNewAppointment() {
  this.newAppointment = {
  date: '',
  time: '',
  description: '',
  doctorId: null,
  patientId: null,
};
  this.isNewAppointmentFormOpen = false;
},
},
    mounted() {
      TerminPlanModel.groupAppointmentsByDay();
      TerminPlanModel.groupAppointmentsByWeek();
    },
};
</script>


<style>

.TerminPlan-container{
  font-family: sans-serif;
  font-weight: bold;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.2);
  color: darkslategray;
  z-index: 1;
}

.TerminPlan-container::before{
  background-color: rgba(255,255,255,0.2);
}


.First-Elements{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 25vh;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ccc;
  background-color: #f9f9f9;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}


</style>