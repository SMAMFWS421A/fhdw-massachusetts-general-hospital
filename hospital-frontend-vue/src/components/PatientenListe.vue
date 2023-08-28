<script>
import {ref} from 'vue';
import PatientService from '@/services/PatientService';
export default {
  setup (){
        //store incoming data
        const patient = ref('');
        const patients = ref('');

        // functions for API-Calls
        const loadPatient = async () => {
            try{
                const response = await PatientService.getPatient(1);
                patient.value = response.data;
            } catch (err) {
                console.log(err);
            }
        }

        const loadPatients = async () => {
            try{
                const response = await PatientService.getPatients();
                patients.value = response.data;
            } catch (err) {
                console.log(err);
            }
        }

        const createPatient = async () => {
            const response = await PatientService.createPatient(
                JSON.stringify({
                    firstName: 'Ich wurde',
                    lastName: 'erschaffen', 
                    gender: 'Male',
                    birthday: '2003-08-01',
                    isPrivate: true,
                    phoneNumber: '01879643284',
                    city: 'Dortmund',
                    patientRecord:{
                      medication: 'Krebstabletten',
                      diseases: 'kein Krebs'
                    } 
                })
            )
            console.log(response);
        }

        const updatePatient = async () => {
            const response = await PatientService.updatePatient( 
                JSON.stringify({
                    id: 1,
                    firstName: 'Ich wurde',
                    lastName: 'geupdated', 
                    gender: 'Female',
                    birthday: '2003-01-10',
                    isPrivate: true,
                    phoneNumber: '01879643284',
                    city: 'Essen',
                    patientRecord:{
                      medication: 'Nichts',
                      diseases: 'Krebs'
                    } 
                })
            )
            console.log(response);
        }

        const deletePatient = async () => {
            const response = await PatientService.deletePatient(1)
            console.log(response);
        }

        // use functions
        loadPatient();
        loadPatients();


        // return object for template
        return{
            patient,
            patients,
            createPatient,
            updatePatient,
            deletePatient
        }

    }
    

}

</script>

<template>
<div> <h1>This is the 3 Page</h1></div>
<p>{{ patient }}</p>
<p>{{ patients }}</p>
<p>
    <button @click="createPatient">Create Patient</button>
</p>
<p>
    <button @click="updatePatient">Update Patient</button>
</p>
<p>
    <button @click="deletePatient">Delete Patient</button>
</p>

</template>

<style>

</style>