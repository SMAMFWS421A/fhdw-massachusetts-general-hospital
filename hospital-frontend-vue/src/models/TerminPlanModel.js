import dummyDataTerminPlan from '@/assets/dummyDataTerminPlan.json';

function getWeekStartDate(date) {
    const d = new Date(date);
    const day = d.getDay();
    const diff = d.getDate() - day + (day === 0 ? -6 : 1);
    return new Date(d.setDate(diff)).toISOString().split('T')[0];
}

const TerminPlanModel = {
    appointments: dummyDataTerminPlan.appointments,
    doctors: dummyDataTerminPlan.doctors,
    patients: dummyDataTerminPlan.patients,
    getDoctorName(doctorId) {
        const doctor = this.doctors.find(doctor => doctor.id === doctorId);
        return doctor ? doctor.name : 'Unbekannter Arzt';
    },
    getPatientName(patientId) {
        const patient = this.patients.find(patient => patient.id === patientId);
        return patient ? patient.name : 'Unbekannter Patient';
    },
    groupAppointmentsByDay() {
        const appointmentsByDay = {};
        for (const appointment of this.appointments) {
            const date = appointment.date;
            if (!appointmentsByDay[date]) {
                appointmentsByDay[date] = [];
            }
            appointmentsByDay[date].push(appointment);
        }
        return appointmentsByDay;
    },
    groupAppointmentsByWeek() {
        const appointmentsByWeek = {};
        for (const appointment of this.appointments) {
            const weekStartDate = getWeekStartDate(appointment.date);
            if (!appointmentsByWeek[weekStartDate]) {
                appointmentsByWeek[weekStartDate] = [];
            }
            appointmentsByWeek[weekStartDate].push(appointment);
        }
        return appointmentsByWeek;
    },
    updateAppointmentInWeekView(appointment) {
        const weekStartDate = getWeekStartDate(appointment.date);
        if (this.appointmentsByWeek[weekStartDate]) {
            const index = this.appointmentsByWeek[weekStartDate].findIndex(
                appt => appt.id === appointment.id
            );
            if (index !== -1) {
                this.appointmentsByWeek[weekStartDate][index] = { ...appointment };
            }
        }
    },
    updateAppointmentInDayView(appointment) {
        const date = appointment.date;
        if (this.appointmentsByDay[date]) {
            const index = this.appointmentsByDay[date].findIndex(
                appt => appt.id === appointment.id
            );
            if (index !== -1) {
                this.appointmentsByDay[date][index] = { ...appointment };
            }
        }
    },
};

export default TerminPlanModel;