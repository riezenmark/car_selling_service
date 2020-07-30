import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'

//todo ресурс на всё приложение вместо userApi (см. UserList метод deleteUser и UserForm)
//var userApi = Vue.resource('/users{/id}');

Vue.use(VueResource)

new Vue({
    el: '#app',
    render: a => a(App)
})

//todo ?
/*created: function () {
    userApi.get().then(result =>
        result.json().then(data =>
            data.forEach(user => this.users.push(user))
        )
    )
}*/
