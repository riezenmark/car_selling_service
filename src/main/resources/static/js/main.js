import Vue from 'vue'
import router from './router/router'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'

//todo ресурс на всё приложение вместо userApi (см. UserList метод deleteUser и UserForm)
//var userApi = Vue.resource('/users{/id}');

Vue.use(VueResource)

new Vue({
    el: '#app',
    router,
    render: a => a(App)
})

