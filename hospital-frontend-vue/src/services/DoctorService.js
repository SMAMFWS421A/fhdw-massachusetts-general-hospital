import api from './api';
const additionalPath = '/doctor';

export default {
    getDoctor(doctorId){
        return api().get(additionalPath + '/' + doctorId);
    },
    getDoctors(){
        return api().get(additionalPath);
    },
    hireDoctor(doctor){
        return api().post(additionalPath, doctor, {headers: {'Content-Type': 'application/json'} }); 
    },
    updateDoctor(updatedDoctor){
        return api().put(additionalPath, updatedDoctor, {headers: {'Content-Type': 'application/json'} });
    },
    fireDoctor(doctorId){
        return api().delete(additionalPath + '/' + doctorId);
    }
};