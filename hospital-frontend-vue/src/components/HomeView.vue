
<template>
  <div class="app-container" :style="backgroundImageStyle">

    <header class="header">

        <div class="title-container">
          <h1 class="header-title"> Massachusetts-General-Hospital</h1>
          <h2 class="header-subtitle"> zusammen gutes bewirken</h2>
        </div>

        <div class="time-container">
          <p class="current-time">{{currentTime}}</p>
          <p class="current-date">{{currentDate}}</p>
        </div>
    </header>


  <div class="home-view">
    <h1>Test</h1>
  </div>

    <div class="footer">
      <h1>zrgrr</h1>
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

.header{
  display: grid;
  grid-template-columns: 1fr auto;
  align-items: center;
  justify-content: space-between;
  height: 120px;
  z-index: 1;
  position: relative;
}

.title-container{
  display: flex;
  flex-direction: column;
  align-items: center;
}


.time-container{
  justify-self: start;
  display: flex;
  flex-direction: column;
  align-self: center;
}


.current-time{
  font-size: 24px;
  font-family: sans-serif;
  font-weight: bold;
  z-index: 1;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
  margin: 0;
}

.current-date{
  font-size: 18px;
  font-family: sans-serif;
  z-index: 1;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
  margin: 0;
}

.header-subtitle{
  font-size: 18px;
  font-family: sans-serif;
  z-index: 1;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
  margin: 0;
}

.header-title{
  font-size: 24px;
  font-family: sans-serif;
  font-weight: bold;
  z-index: 1;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
  margin: 0;
}


.home-view{

}

.footer{

}

</style>