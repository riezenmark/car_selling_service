import Vue from 'vue'
import '@babel/polyfill'
import router from './router/router'
import VueResource from 'vue-resource'
import App from 'static/js/pages/App.vue'
import store from "static/js/store/store";
import vuetify from 'static/js/plugin/vuetify'
import 'vuetify/dist/vuetify.min.css'

Vue.use(VueResource)

new Vue({
    el: '#app',
    router,
    store,
    vuetify,
    render: a => a(App)
})

