
<template>
  <div class="home-view" :style="backgroundImageStyle">
    <h1>Test</h1>
  </div>

</template>



<script>
export default {

  computed: {
    backgroundImagePath() {
      return require('@/assets/StartseiteBild.jpg')
    },
    backgroundImageStyle(){
      return{
        backgroundImage: `url(${this.backgroundImagePath})`,
        backgroundSize: 'cover',
        backgroundRepeat: 'no-repeat',
        backgroundPosition: 'center',
        height: '100vh'

      };
    }
  }
};

</script>




<style>
.home-view{
  width: 100%;
  height: 100vh;
}

</style>