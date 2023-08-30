
<template>
    <header class="header">
        <h1>Patientenliste</h1>
    </header>

    <body class="body">
        <br />
        <div class="btnSection">
            <br />
            <div v-if="openForumlar">
                <form>
                    <fieldset>
                        <legend>Formular</legend>
                        <p>ID: <input type="text" id="id" name="id" placeholder="ID" required
                                v-model="newPatient.id"></p>
                        <p>Vorname: <input type="text" id="fname" name="fname" placeholder="Vorname" required
                                v-model="newPatient.firstName"></p>
                        <p>Nachname: <input type="text" id="lname" name="lname" placeholder="Nachname"
                                v-model="newPatient.lastName"></p>
                        <p>Geschlecht: <select name="gender" id="gender" v-model="newPatient.gender">
                                <option value="male">Male</option>
                                <option value="female">Female</option>
                            </select></p>
                        <p>Geburtstag: <input type="date" id="geb" name="geb" v-model="newPatient.birthday"></p>
                        <p>Privatversichert: <select name="isPrivate" id="isPrivate" v-model="newPatient.isPrivate">
                                <option value="false">Nein</option>
                                <option value="true">Ja</option>
                            </select></p>
                        <p>Telefonnummer: <input type="number" id="tel" name="tel" v-model="newPatient.phoneNumber"></p>
                        <p>Stadt: <input type="text" id="city" name="city" v-model="newPatient.city"></p>
                        <p>Patientenakte: <input type="text" id="patientRecord" name="patientRecord" v-model="newPatient.patientRecord"></p>
                    </fieldset>
                    <button @click="postApi()">Doktor Erstellen</button><button
                        v-on:click="updateApi(newPatient.id, newPatient.firstName, newPatient.lastName, newPatient.gender, newPatient.position, newPatient.area)">Doktor
                        Updaten</button>
                    <br>
                </form>
            </div>
        </div>
        <br />
        <h2>Mitarbeiterliste</h2>
        <input type="text" @input="handleInput" placeholder="Mitarbeiter Suchen">{{ search }}
        <table>
            <tr>
                <td>Id</td>
                <td>Vorname</td>
                <td>Nachname</td>
                <td>Geschlecht</td>
                <td>Geburtstag</td>
                <td>Privatversichert</td>
                <td>Telefonnummer</td>
                <td>Stadt</td>
                <td>Patientenakte</td>
                <td>Löschen</td>
            </tr>
            <tr v-for="patient in patients" v-bind:key="patient.index">
                <td> {{ patient.id }}</td>
                <td> {{ patient.firstName }}</td>
                <td> {{ patient.lastName }}</td>
                <td> {{ patient.gender }}</td>
                <td> {{ patient.birthday }}</td>
                <td> {{ patient.isPrivate }}</td>
                <td> {{ patient.phoneNumber }}</td>
                <td> {{ patient.city }}</td>
                <td> {{ patient.patientRecord }}</td>
                <td><button v-on:click="deleteApi(patient.id)">Delete</button></td>
            </tr>
        </table>
    </body>
    <footer>

    </footer>
</template>
<script>
const axios = require("axios");
const baseUrl = "http://localhost:3000/patient/";

export default {
    data() {
        return {
            patients: [],
            search: '',
            debounce: null,
            openForumlar: true,
            newPatient:
            {
                id:'',
                firstName: '',
                lastName: '',
                gender: '',
                birthday: '',
                isPrivate: '',
                phoneNumber: '',
                city: '',
                patientRecord: ''
            },

        }
    },

    methods: {
        handleInput(val) {
            clearTimeout(this.debounce);
            console.log(val.target.value);
            this.debounce = setTimeout(() => {
                this.search = val.target.value;
            }, 1000);
        },

        async getApi() {
            let respon = await axios.get(baseUrl);
            this.patients = respon.data;
        },
        async postApi() {
            await axios.post(baseUrl, {
               id: this.newPatient.id , firstName: this.newPatient.firstName, lastName: this.newPatient.lastName, gender: this.newPatient.gender, birthday: this.newPatient.birthday, isPrivate: this.newPatient.isPrivate, phoneNumber: this.newPatient.phoneNumber, city: this.newPatient.city, patientRecord: this.newPatient.patientRecord 
            }).then((respon) => { console.log(respon); this.getApi(); })
        },
        async deleteApi(id) {
            await axios.delete(baseUrl + id).then((respon) => { console.log(respon); this.getApi(); })
        },
        async updateApi(id, firstName, lastName, gender, position, area) {
            console.log("hallo ich bin da")
            await axios.put(baseUrl + id, { firstName: firstName, lastName: lastName, gender: gender, position: position, area: area }).then((respon) => { console.log(respon); this.getApi(); })
        },

    },

    mounted() {
        this.getApi();
    },

    computed: {
        filterpatienttor() {
            if (this.search.trim().length > 0) {
                return this.dummyData.filter((patienttor) => patienttor.firstName.toLowerCase().includes(this.search.trim().toLowerCase()))
            }
            return this.dummyData
        }
    },
}
</script>


<style>
.header {
    background-color: aqua;
    text-align: center;
}

.btnSection {
    background-color: grey;
    text-align: center;
    width: 500px;

}

.footer {
    text-align: center;
}

.searchList {}
</style>