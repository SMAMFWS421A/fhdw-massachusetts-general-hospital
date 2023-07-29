
<template>
  <div class="app-container" :style="backgroundImageStyle">

    <header class="header">
      <p class="header-subtitle">{{currentTime}}</p>
      <h1 class="header-title"> Massachusetts-General-Hospital</h1>
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
    setInterval(this.updateTime, 1000);
  },
  methods: {
    updateTime(){

      const now = new Date();
      const hours = now.getHours().toString().padStart(2, '0');
      const minutes = now.getMinutes().toString().padStart(2, '0');
      const seconds = now.getSeconds().toString().padStart(2, '0');
      this.currentTime= `${hours}:${minutes}:${seconds}`;
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
  display: flex;
  align-items: center;
  justify-content: center;
  height: 80px;

}
.header-subtitle{
  font-size: 24px;
  font-family: sans-serif;
  font-weight: bold;
  z-index: 1;
  margin-right: 1200px;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
}

.header-title{
  font-size: 24px;
  font-family: sans-serif;
  font-weight: bold;
  z-index: 1;
  position: fixed;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
}
.home-view{

}

.footer{

}

</style>