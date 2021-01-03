import Vue from 'vue'
import Vuex from 'vuex'
import state from './state'
import mutations from './mutations'
import actions from './actions'
import getters from './getters'
import blog from './modules/blog'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    blog
  },
  state,
  mutations,
  actions,
  getters
})
