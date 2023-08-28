import api from './api';
const additionalPath = '/visit';
const registerFromAppointmentPath = '/register-from-appointment';

export default {
    getVisit(visitId){
        return api().get(additionalPath + '/' + visitId);
    },
    getVisits(){
        return api().get(additionalPath);
    },
    getregisteredVisit(appointmentId){
        return api().get(registerFromAppointmentPath + '/' + appointmentId);
    },
    createVisit(visit){
        return api().post(additionalPath, visit, {headers: {'Content-Type': 'application/json'} }); 
    },
    updateVisit(updatedVisit){
        return api().put(additionalPath, updatedVisit, {headers: {'Content-Type': 'application/json'} });
    },
    deleteVisit(visitId){
        return api().delete(additionalPath + '/' + visitId);
    }
};