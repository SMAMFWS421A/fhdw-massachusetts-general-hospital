import api from './api';
const additionalPath = '/appointment';

export default {
    getAppointment(appointmentId){
        return api().get(additionalPath + '/' + appointmentId);
    },
    getAppointments(){
        return api().get(additionalPath);
    },
    createAppointment(appointment){
        return api().post(additionalPath, appointment, {headers: {'Content-Type': 'application/json'} }); 
    },
    updateAppointment(updatedAppointment){
        return api().put(additionalPath, updatedAppointment, {headers: {'Content-Type': 'application/json'} });
    },
    deleteAppointment(appointmentId){
        return api().delete(additionalPath + '/' + appointmentId);
    }
};