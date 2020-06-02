<template>
  <div id="app">
    <div id="nav">
      <router-link to="/home">Home</router-link> |
      <router-link to="/cats">Cats</router-link> |
      <router-link to="/dogs">Dogs</router-link> |
      <router-link to="/">blog</router-link>
    </div>

    <b-container>
      <router-view/>
    </b-container>
  </div>
</template>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
<script>
    import axios from 'axios'
    export default {
        name: 'App',
        data () {
            return {
                itemList: []
            }
        },
        mounted () {
            this.getData()
        },
        methods: {
            getData () {
                axios.get('/data/blog.json').then(response => {
                    console.log(response.data)
                }, response => {
                    console.log('error')
                })
            }
        }
    }
</script>
