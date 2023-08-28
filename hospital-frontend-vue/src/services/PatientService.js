import api from './api';
const additionalPath = '/patient';

export default {
    getPatient(patientId){
        return api().get(additionalPath + '/' + patientId);
    },
    getPatients(){
        return api().get(additionalPath);
    },
    createPatient(patient){
        return api().post(additionalPath, patient, {headers: {'Content-Type': 'application/json'} }); 
    },
    updatePatient(updatedPatient){
        return api().put(additionalPath, updatedPatient, {headers: {'Content-Type': 'application/json'} });
    },
    deletePatient(patientId){
        return api().delete(additionalPath + '/' + patientId);
    }
};