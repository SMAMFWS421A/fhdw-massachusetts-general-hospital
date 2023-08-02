
<template>
  <div class="app-container" :style="backgroundImageStyle">

    <header class="header">
        <div class="first-element">
          <div class="header-title"> Massachusetts-General-Hospital</div>
        </div>

        <div class="second-element">
          <div class="current-time">{{currentTime}}</div>
          </div>

         <div class="third-element">
           <div class="header-subtitle"> zusammen gutes bewirken</div>
          </div>

          <div class="fourth-element">
           <div class="current-date">{{currentDate}}</div>
          </div>
    </header>





  <div class="home-view">

    <div class="first-element-mid">
       <div class="first-paragraph"> "Nur wer das Ziel kennt, findet den Weg." Laotse</div>
    </div>

    <div class="second-element-mid">
      <div class="second-paragraph"> "Erfolg besteht aus drei Buchstaben: TUN." Johann Wolfgang von Goethe</div>
    </div>

    <div class="third-element-mid">
      <div class="third-paragraph"> "Das Schönste an Teamarbeit ist, dass du immer andere an deiner Seite hast." Margaret Carty</div>
    </div>

    <div class="fourth-element-mid">
      <button class="first-button" @click="handleClick">Klick mich!</button>
    </div>

    <div class="fifth-element-mid">
      <button class="second-button" @click="handleClick">Klick mich!</button>
    </div>

    <div class="sixth-element-mid">
      <button class="third-button" @click="handleClick">Klick mich!</button>
    </div>

  </div>










    <div class="footer">

      <div class="first-element-low">
        <button class="first-button-low">Impressum</button>
      </div>

      <div class="second-element-low">
        <button class="second-button-low">Datenschutz</button>
      </div>

      <div class="third-element-low">
        <div class="first-paragraph-low">
          @Massachusetts-General-Hospital</div>
      </div>

      <div class="fourth-element-low">
        <div class="second-paragraph-low">
          40822 Mettmann
          Marie-Curie-Straße 6
          02104 1775602
        </div>
      </div>

    </div>


  </div>
</template>



<script>
export default {
  data() {
    return {
      currentTime: '',
      currentDate: '',
    };
  },

  computed: {
    backgroundImagePath() {
      return require('@/assets/StartseiteBild.jpg')
    },
    backgroundImageStyle(){
      return{
        backgroundImage: `url(${this.backgroundImagePath})`,
        backgroundSize: '100% 100%',
        backgroundRepeat: 'no-repeat',
        backgroundPosition: 'center',
        position: 'relative'

      };
    },
  },

  mounted() {
    this.updateTime();
    setInterval(() => {
      this.updateTime();
    }, 1000);
  },
  methods: {
    updateTime() {
      const now = new Date();
      this.currentTime = this.formatTime(now);
      this.currentDate = this.formatDate(now);
    },
    formatTime(date) {
      return `${this.padZero(date.getHours())}:${this.padZero(date.getMinutes())}:${this.padZero(date.getSeconds())}`;
    },

    formatDate(date) {
      return `${this.padZero(date.getDate())}.${this.padZero(date.getMonth() +1 )}.${date.getFullYear()}`;
    },

    padZero(num) {
      return num.toString().padStart(2, "0");
    },

    handleClick(){
      console.log("Button wurde geklickt");
    }

  },

};

</script>




<style>

.app-container{
  width: 100%;
  height: 100vh;
  margin: 0;
  padding: 0;
}

.app-container::before{
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(255,255,255,0.5);
}


.first-element{
 left: 40%;
  top: 40px;
position: absolute;
  width: 500px;
}

.second-element{
  position: absolute;
  top: 40px;
  left: 50px;
  width: 500px;
}

.third-element{
  position: absolute;
  top: 70px;
  left: 45%;
  width: 500px;
}

.fourth-element{
  position: absolute;
  top: 70px;
  left: 50px;
  width: 500px;
}


.current-time{
  font-size: 24px;
  font-family: sans-serif;
  font-weight: bold;
  z-index: 1;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
}

.current-date{
  font-size: 18px;
  font-family: sans-serif;
  z-index: 1;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);

}

.header-subtitle{
  font-size: 18px;
  font-family: sans-serif;
  z-index: 1;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);

}

.header-title{
  font-size: 24px;
  font-family: sans-serif;
  font-weight: bold;
  z-index: 1;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
}





.first-element-mid {
  position: absolute;
  top: 140px;
  left: 925px;
  width: 350px;
}

.second-element-mid{
  position: absolute;
  top: 300px;
  left: 1100px;
  width: 400px;
}

.third-element-mid{
  position: absolute;
  top: 580px;
  left: 700px;
  width: 700px;
}

.first-paragraph{
  font-size: 18px;
  font-family: sans-serif;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
  position: absolute;
}

.second-paragraph{
  font-size: 18px;
  font-family: sans-serif;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
  position: absolute;
}

.third-paragraph{
  font-size: 18px;
  font-family: sans-serif;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
  position: absolute;
}

.fourth-element-mid{
  position: absolute;
  top: 180px;
  left: 180px;
  width: 700px;
}

.fifth-element-mid{
  position: absolute;
  top: 365px;
  left: 400px;
  width: 700px;
}

.sixth-element-mid{
  position: absolute;
  top: 550px;
  left: 180px;
  width: 700px;
}



.first-button{
  font-size: 12px;
  font-family: sans-serif;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
  background: none;
  border: none;
  cursor: pointer;
}

.second-button{
  font-size: 12px;
  font-family: sans-serif;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
  background: none;
  border: none;
  cursor: pointer;
}

.third-button{
  font-size: 12px;
  font-family: sans-serif;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
  background: none;
  border: none;
  cursor: pointer;
}









.first-element-low{
  position: absolute;
  top: 680px;
  left: 50px;
  width: 700px;
}

.second-element-low{
  position: absolute;
  top: 700px;
  left: 50px;
  width: 700px;
}

.third-element-low{
  position: absolute;
  top: 680px;
  left: 1280px;
  width: 700px
}

.fourth-element-low{
  position: absolute;
  top: 700px;
  left: 1280px;
  width: 120px;
}




.first-button-low{
  font-size: 12px;
  font-family: sans-serif;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
  background: none;
  border: none;
  cursor: pointer;
}

.second-button-low{
  font-size: 12px;
  font-family: sans-serif;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
  background: none;
  border: none;
  cursor: pointer;
}

.first-paragraph-low{
  font-size: 12px;
  font-family: sans-serif;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
}

.second-paragraph-low{
  font-size: 12px;
  font-family: sans-serif;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
}
</style>