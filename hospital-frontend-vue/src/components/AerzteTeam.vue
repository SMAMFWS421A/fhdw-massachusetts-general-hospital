<template>
    <header class="header">
        <h1>Ärzte Team</h1>
    </header>

    <body class="body">
        <br />
        <div class="btnSection">
            <br />
            <div v-if="openForumlar" v-once>
                <form>
                    <fieldset>
                        <legend>Formular</legend>
                        <p>ID: <input type="text" id="id" name="id" placeholder="ID" v-model="newDoctor.id"></p>
                        <p>Vorname: <input type="text" id="fname" name="fname" placeholder="Vorname" required
                                v-model="newDoctor.firstName"></p>
                        <p>Nachname: <input type="text" id="lname" name="lname" placeholder="Nachname"
                                v-model="newDoctor.lastName"></p>
                        <p>Geschlecht: <select name="gender" id="gender" v-model="newDoctor.gender">
                                <option value="male">Male</option>
                                <option value="female">Female</option>
                            </select></p>
                        <p>Position: <select name="position" v-model="newDoctor.position">
                                <option>GENERAL</option>
                                <option>SURGERY</option>
                                <option>NEUROLOGY</option>
                                <option>DERMATOLOGY</option>
                                <option>OPHTHALMOLOGY</option>
                                <option>GYNECOLOGY</option>
                                <option>UROLOGY</option>
                            </select></p>
                        <p>Arbeitsbereich: <select name="workArea" id="workArea" v-model="newDoctor.area">
                                <option value="ASSISTANT_DOCTOR">ASSISTANT_DOCTOR</option>
                                <option value="SPECIALIST">SPECIALIST</option>
                                <option value="SENIOR_DOCTOR">SENIOR_DOCTOR</option>
                                <option value="CHIEF_DOCTOR">CHIEF_DOCTOR</option>
                            </select></p>
                    </fieldset>
                    <button @click="postApi()">Doktor Erstellen</button><button
                        v-on:click="updateApi(newDoctor.id, newDoctor.firstName, newDoctor.lastName, newDoctor.gender, newDoctor.position, newDoctor.area)">Doktor
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
                <td>Position</td>
                <td>Area</td>
                <td>Löschen</td>
            </tr>
            <tr v-for="doc in docotors" v-bind:key="doc.id">
                <p> {{ doc.id }} </p>
                <td> {{ doc.firstName }}</td>
                <td> {{ doc.lastName }}</td>
                <td> {{ doc.gender }}</td>
                <td> {{ doc.position }}</td>
                <td> {{ doc.area }}</td>
                <td><button v-on:click="deleteApi(doc.id)">Delete</button></td>
            </tr>
        </table>
    </body>
    <footer>

    </footer>
</template>

<script>

const axios = require("axios");
const baseUrl = "http://localhost:3000/doctor/";

export default {
    data() {
        return {
            docotors: [],
            search: '',
            debounce: null,
            openForumlar: true,
            newDoctor:
            {
                id: null,
                firstName: '',
                lastName: '',
                gender: '',
                position: '',
                area: ''
            },

        }
    },

    methods: {
        async getApi() {
            let respon = await axios.get(baseUrl);
            this.docotors = respon.data;
        },
        async postApi() {
            await axios.post(baseUrl, {
                id: this.newDoctor.id, firstName: this.newDoctor.firstName, lastName: this.newDoctor.lastName, gender: this.newDoctor.gender, position: this.newDoctor.position, area: this.newDoctor.area
            }).then((respon) => { console.log(respon); this.getApi(); }).catch((err) => {console.log(err)});
        },
        async deleteApi(id) {
            await axios.delete(baseUrl + id).then((respon) => { console.log(respon); this.getApi();}).catch((err) => {console.log(err)});
        },
        async updateApi(id, firstName, lastName, gender, position, area) {
            await axios.put(baseUrl + id, { firstName: firstName, lastName: lastName, gender: gender, position: position, area: area }).then((respon) => { console.log(respon); this.getApi();}).catch((err) => {console.log(err)});
        },

        handleInput(val) {
            clearTimeout(this.debounce);
            console.log(val.target.value);
            this.debounce = setTimeout(() => {
                this.search = val.target.value;
                if (this.search.trim().length > 0) {
                    return this.docotors.filter((doctor) => doctor.firstName.toLowerCase().includes(this.search.trim().toLowerCase()))
                }
                return this.docotors;
            }, 1000);
        },
    },
    mounted() {
        this.getApi();
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

</style>

