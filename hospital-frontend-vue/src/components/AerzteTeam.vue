<script>
import {ref} from 'vue';
import DoctorService from '../services/DoctorService'

export default {
    setup (){
        //store incoming data
        const doctor = ref('');
        const doctors = ref('');

        // functions for API-Calls
        const loadDoctor = async () => {
            try{
                const response = await DoctorService.getDoctor(1);
                doctor.value = response.data;
            } catch (err) {
                console.log(err);
            }
        }

        const loadDoctors = async () => {
            try{
                const response = await DoctorService.getDoctors();
                doctors.value = response.data;
            } catch (err) {
                console.log(err);
            }
        }

        const hireDoctor = async () => {
            const response = await DoctorService.hireDoctor(
                JSON.stringify({
                    firstName: 'Ich wurde',
                    lastName: 'erschaffen', 
                    gender: 'Male',
                    position: 'ASSISTANT_DOCTOR',
                    area: 'GENERAL'
                })
            )
            console.log(response);
        }

        const updateDoctor = async () => {
            const response = await DoctorService.updateDoctor(1, 
                JSON.stringify({
                    firstName: 'Ich bin',
                    lastName: 'geupdated', 
                    gender: 'Female',
                    position: 'ASSISTANT_DOCTOR',
                    area: 'GENERAL'
                })
            )
            console.log(response);
        }

        const deleteDoctor = async () => {
            const response = await DoctorService.fireDoctor(1)
            console.log(response);
        }

        // use functions
        loadDoctor();
        loadDoctors();


        // return object for template
        return{
            doctor,
            doctors,
            hireDoctor,
            updateDoctor,
            deleteDoctor
        }

    }
    

}

</script>

<template>
<div> <h1>This is the 3 Page</h1></div>
<p>{{ doctor }}</p>
<p>{{ doctors }}</p>
<p>
    <button @click="hireDoctor">Hire Doctor</button>
</p>
<p>
    <button @click="updateDoctor">Update Doctor</button>
</p>
<p>
    <button @click="deleteDoctor">Delete Doctor</button>
</p>

</template>

<style>

</style>